package com.polaroid.chatweb.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.polaroid.chatweb.model.user.Message;

@Controller
public class ChatController {
	@MessageMapping("/chat.register")
	@SendTo("/topic/public") 
	public Message register(@Payload Message chatMessage,SimpMessageHeaderAccessor headerAccessor)  {
		headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
		return chatMessage;
	}
	
	@MessageMapping("/chat.send")
	@SendTo("/topic/public") 
	public Message sendMessage(@Payload Message chatMessage) { 
		return chatMessage;
	}
}
