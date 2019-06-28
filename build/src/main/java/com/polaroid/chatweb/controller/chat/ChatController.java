package com.polaroid.chatweb.controller.chat;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polaroid.chatweb.model.chat.Chat;
import com.polaroid.chatweb.model.chat.Message;
import com.polaroid.chatweb.repository.chat.ChatRepository;

@Controller
public class ChatController {

	@Autowired
	private ChatRepository repository;

	@GetMapping(path = "/chat/{chatId}", produces = "application/json")
	@ResponseBody
	public List<Message> getMessagesFromChat(@PathParam("chatId") Long chatId) {
		Optional<Chat> result = repository.findById(chatId);

		if (result.isEmpty())
			return Collections.emptyList();

		return result.get().getMessages();
	}

}
