package com.polaroid.chatweb.controller.chat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polaroid.chatweb.model.chat.Chat;
import com.polaroid.chatweb.model.chat.Message;
import com.polaroid.chatweb.model.reponses.MessageHolder;
import com.polaroid.chatweb.model.user.User;
import com.polaroid.chatweb.repository.chat.ChatRepository;
import com.polaroid.chatweb.repository.chat.MessageRepository;
import com.polaroid.chatweb.repository.user.UserRepository;

@Controller
public class ChatController {

	@Autowired
	private ChatRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private SimpMessagingTemplate sender;

	@GetMapping(path = "/chat/{chatId}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMessagesFromChat(@PathVariable("chatId") Long chatId) {
		Optional<Chat> result = repository.findById(chatId);
		Map<String, Object> valores = new HashMap<>();
		valores.put("participant", result.get().getParticipant().getOwnerName());
		valores.put("messages", result.get().getMessages());
		return valores;
	}

	@MessageMapping("/chat")
	public void receiveDirectMessage(MessageHolder content) {
		System.out.println("Requisição recebida");
		User from = userRepository.findByUsernameOrEmail(content.getFrom(), content.getFrom()).get();
		User to = userRepository.findByUsernameOrEmail(content.getTo(), content.getTo()).get();

		Message m = new Message();
		m.setContent(content.getContent());
		m.setAuthor(from);

		m = messageRepository.save(m);

		addMessage(m, from, to);
		addMessage(m, to, from);

		sender.convertAndSendToUser(content.getTo(), "/msg", content);
	}

	private void addMessage(Message m, User from, User to) {
		Optional<Chat> opChatFrom = repository.findByOwnerAndParticipant(from, to);

		opChatFrom.ifPresent(chat -> {
			chat.addMessage(m);

			repository.save(chat);
		});

		if (opChatFrom.isEmpty()) {
			Chat chatFrom = new Chat();
			chatFrom.setCreatedAt(LocalDateTime.now());
			chatFrom.setOwner(from);
			chatFrom.setParticipant(to);

			chatFrom.addMessage(m);

			repository.save(chatFrom);
		}

		System.out.println("salvando a mensagem " + m.getContent() + " na conversa " + opChatFrom.get().getId());
	}

	@GetMapping(path = "/chat/create/{user1}/{user2}")
	public String createChat(@PathVariable("user1") String user1, @PathVariable("user2") String user2) {
		System.out.printf("Chat entre %s e %s%n", user1, user2);
		User u1 = userRepository.findByUsernameOrEmail(user1, user1).get();
		User u2 = userRepository.findByUsernameOrEmail(user2, user2).get();

		Chat c1 = new Chat();
		c1.setOwner(u1);
		c1.setParticipant(u2);

		Chat c2 = new Chat();
		c2.setOwner(u2);
		c2.setParticipant(u1);

		repository.save(c1);
		repository.save(c2);

		return "redirect:/";
	}

}
