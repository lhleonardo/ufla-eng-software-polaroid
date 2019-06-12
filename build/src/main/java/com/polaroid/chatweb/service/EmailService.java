package com.polaroid.chatweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Classe responsável pelo envio de e-mails
 * 
 * @author lhleo
 *
 */
@Service
public class EmailService {

	@Autowired
	private JavaMailSender sender;

	/**
	 * Envia um e-mail a partir de um corpo de mensagem
	 * 
	 * @param corpo de mensagem que deve ser enviado
	 */
	@Async
	public void sendEmail(SimpleMailMessage email) {
		sender.send(email);
	}
}
