package com.polaroid.chatweb.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controlador de requisições de autenticação
 * @author Guilherme Melo e Leonardo Braz
 * @version 1.0
 *
 */
@Controller
public class LoginController {

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String formLogin() {
		return "login";
	}

}
