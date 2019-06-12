package com.polaroid.chatweb.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.polaroid.chatweb.model.user.ConfirmationToken;
import com.polaroid.chatweb.model.user.User;
import com.polaroid.chatweb.repository.user.ConfirmationTokenRepository;
import com.polaroid.chatweb.repository.user.UserRepository;
import com.polaroid.chatweb.service.EmailService;

@Controller
public class UserController {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService sender;

	@Autowired
	private ConfirmationTokenRepository tokenRepository;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showForm(User probablyUser) {
		ModelAndView view = new ModelAndView("usuario/register");
		view.addObject("user", probablyUser);
		return view;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView form(@Valid User user, BindingResult binding, RedirectAttributes attributes) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);

		ConfirmationToken token = new ConfirmationToken(user);
		tokenRepository.save(token);

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("[POLAROID] Cadastro concluído! ");
		mail.setFrom("Polaroid <polaroidchat@gmail.com>");
		mail.setText("Para confirmar seu cadastro, acesse o link: " + "http://localhost:8080/confirm-account/"
				+ token.getToken());

		sender.sendEmail(mail);

		return new ModelAndView("redirect:/login?registered");
	}

	@RequestMapping(value = "/profile/change", method = RequestMethod.GET)
	public ModelAndView changeAccount(Authentication auth) {
		ModelAndView mv = new ModelAndView("usuario/changeProfile");
		mv.addObject("user", (User) auth.getPrincipal());
		return mv;
	}

	@RequestMapping(value = "/profile/change", method = RequestMethod.POST)
	public String changeAccountPost(User user, Authentication auth) {
		Optional<User> optUser = userRepository.findById(user.getId());
		
		User user1 = optUser.get();
		user1.setEmail(user.getEmail());
		user1.setOwnerName(user.getOwnerName());
		user1.setUsername(user.getUsername());
		
		userRepository.save(user1);

		User userLogged = (User) auth.getPrincipal();
		userLogged.setUsername(user1.getUsername());
		userLogged.setOwnerName(user1.getOwnerName());
		userLogged.setEmail(user1.getEmail());
		
		return "redirect:/";
	}

}
