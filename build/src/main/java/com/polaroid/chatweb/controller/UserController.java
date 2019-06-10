package com.polaroid.chatweb.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.polaroid.chatweb.model.user.ConfirmationToken;
import com.polaroid.chatweb.model.user.User;
import com.polaroid.chatweb.repository.user.TokenConfirmationRepository;
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
	private TokenConfirmationRepository tokenRepository;
	
	@RequestMapping(value ="/register", method = RequestMethod.GET)
	public ModelAndView showForm(User probablyUser){
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
        mail.setFrom("Leonardo Braz <lhleonardo05@gmail.com>");
        mail.setText("Para confirmar seu cadastro, acesse o link: "
        +"http://localhost:8080/confirm-account?token="+token.getToken());
        
        sender.sendEmail(mail);

		return new ModelAndView("redirect:/login?registered");
	}
	
	@RequestMapping(value = "/confirm-account/{token}", method=RequestMethod.GET)
	public ModelAndView confirmRegister(@PathVariable("token") String token) {
		System.out.println(token);
		Optional<ConfirmationToken> result = this.tokenRepository.findByToken(token);
		
		ModelAndView view = new ModelAndView("redirect:/login");
		System.out.println(result.isEmpty());
		if (result.isEmpty()) {
			view.addObject("tokenValidation", false);
			return view;
		} 
		
		User userConfirmed = result.get().getUser();
		userConfirmed.validate();
		
		this.userRepository.save(userConfirmed);
		
		view.addObject("tokenValidation", true);
		
		return view;
	}
	
	@RequestMapping(value = "/{data}", method = RequestMethod.GET)
	public ModelAndView changeAccount(@PathVariable("data") String data) {
		Optional<User> user = userRepository.findByUsername(data);
		if (user.isEmpty()) {
			//TRATAR ERRO
			return new ModelAndView("redirect:/login");
			//throw new UsernameNotFoundException(String.format("Usuário % não existe.",data));
		}
		ModelAndView mv = new ModelAndView("usuario/changeProfile");
		mv.addObject("user",user.get()); 
		return mv;
	}
	
	@RequestMapping(value = "/{data}", method = RequestMethod.POST)
	public String changeAccountPost(User user, @RequestParam("id") Long Id) {
		Optional<User> optUser = userRepository.findById(Id);
		User user1 = optUser.get();
		user1.setId(user.getId());
		user1.setEmail(user.getEmail());
		user1.setOwnerName(user.getOwnerName());
		user1.setPassword(user.getPassword());
		user1.setUsername(user.getUsername());
		userRepository.save(user1);
		
		return "redirect:/profile";
		
	}
	
	

}
