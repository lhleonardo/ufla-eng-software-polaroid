package com.polaroid.chatweb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.polaroid.chatweb.model.user.ConfirmationToken;
import com.polaroid.chatweb.model.user.User;
import com.polaroid.chatweb.repository.user.ConfirmationTokenRepository;
import com.polaroid.chatweb.repository.user.UserRepository;

/**
 * Controlador de tokens de confirmação 
 * @author Guilherme Melo e Leonardo Braz
 * @version 1.0
 *
 * 
 */
@Controller
public class ConfirmationTokenController {

	@Autowired
	private ConfirmationTokenRepository tokenRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/confirm-account/{token}", method = RequestMethod.GET)
	public ModelAndView confirmRegister(@PathVariable("token") String token) {
		Optional<ConfirmationToken> result = this.tokenRepository.findByToken(token);

		ModelAndView view = new ModelAndView("redirect:/login");
		if (result.isEmpty()) {
			view.addObject("tokenValidation");
			return view;
		}

		User userConfirmed = result.get().getUser();
		userRepository.confirmEmail(userConfirmed.getEmail());

		view.addObject("tokenValidation", true);

		return view;
	}

}
