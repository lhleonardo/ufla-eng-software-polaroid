package com.polaroid.chatweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.polaroid.chatweb.model.User;
import com.polaroid.chatweb.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository rep;
	
	@RequestMapping(value ="/register", method = RequestMethod.GET)
	public String showForm(){
		return "usuario/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String form(User user) {
		
		rep.save(user);
		return "redirect:/login";
	}

}
