package com.polaroid.chatweb.controller.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.polaroid.chatweb.model.chat.Chat;
import com.polaroid.chatweb.model.user.User;
import com.polaroid.chatweb.repository.chat.ChatRepository;

/**
 * Controlador de requisições para domínio principal
 * 
 * @author Guilherme Melo e Leonardo Braz
 * @version 1.0
 */
@Controller
public class HomeControler {

	@Autowired
	private ChatRepository repository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Authentication userAuthenticated) {
		ModelAndView view = new ModelAndView("principal/chat");
		view.addObject("user", (User) userAuthenticated.getPrincipal());
		List<Chat> conversas = repository.findByOwner((User) userAuthenticated.getPrincipal());
		view.addObject("conversas", conversas);	
		
		System.out.println(conversas);
		return view;
	}
}
