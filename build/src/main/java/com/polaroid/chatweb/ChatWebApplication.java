package com.polaroid.chatweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ChatWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatWebApplication.class, args);
	}

}
