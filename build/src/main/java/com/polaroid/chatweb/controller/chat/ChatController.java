package com.polaroid.chatweb.controller.chat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polaroid.chatweb.model.chat.Chat;
import com.polaroid.chatweb.model.chat.Message;
import com.polaroid.chatweb.model.user.User;
import com.polaroid.chatweb.repository.chat.ChatRepository;
import com.polaroid.chatweb.repository.user.UserRepository;

@Controller
public class ChatController {

	@Autowired
	private ChatRepository repository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SimpMessagingTemplate sender;

	@GetMapping(path = "/chat/{chatId}", produces = "application/json")
	@ResponseBody
	public List<Message> getMessagesFromChat(@PathParam("chatId") Long chatId) {
		Optional<Chat> result = repository.findById(chatId);

		if (result.isEmpty())
			return Collections.emptyList();

		return result.get().getMessages();
	}

	@MessageMapping("/chat")
	public void receiveDirectMessage(MessageHolder content) {
		User from = userRepository.findByUsernameOrEmail(content.from, content.from).get();
		User to = userRepository.findByUsernameOrEmail(content.to, content.to).get();

		Message m = new Message();
		m.setContent(content.content);
		m.setAuthor(from);

		addMessage(m, from, to);
		addMessage(m, to, from);
		
		sender.convertAndSendToUser(content.to, "/msg", content);
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
	}

	private class MessageHolder {
		private String from;
		private String to;

		private String content;
	}

}
