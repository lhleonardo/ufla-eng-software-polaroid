package com.polaroid.chatweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.polaroid.chatweb.exception.UserNotVerifiedException;

@Controller
public class LoginController {

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String formLogin() {
		return "login";
	}
	
	@ExceptionHandler(value = {UserNotVerifiedException.class})
	public String erroEmailNaoVerificado() {
		return "login?unverifiedEmail";
	}
	
}
