package com.polaroid.chatweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controlador de requisições para domínio principal
 * 
 * @author Guilherme Melo e Leonardo Braz
 * @version 1.0
 */
@Controller
public class HomeControler {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "principal/index";
	}
}
