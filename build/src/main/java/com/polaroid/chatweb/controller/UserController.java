package com.polaroid.chatweb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.polaroid.chatweb.model.User;
import com.polaroid.chatweb.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository rep;
	
	@RequestMapping(value ="/register", method = RequestMethod.GET)
	public String showForm(){
		return "usuario/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String form(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		rep.save(user);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/{data}", method = RequestMethod.GET)
	public ModelAndView changeAccount(@PathVariable("data") String data) {
		Optional<User> user = rep.findByUsername(data);
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
		Optional<User> optUser = rep.findById(Id);
		User user1 = optUser.get();
		user1.setId(user.getId());
		user1.setEmail(user.getEmail());
		user1.setOwnerName(user.getOwnerName());
		user1.setPassword(user.getPassword());
		user1.setUsername(user.getUsername());
		rep.save(user1);
		
		return "redirect:/profile";
		
	}
	
	

}
