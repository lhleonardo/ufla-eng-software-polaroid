package com.polaroid.chatweb.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.polaroid.chatweb.model.user.ConfirmationToken;
import com.polaroid.chatweb.model.user.User;
import com.polaroid.chatweb.repository.user.ConfirmationTokenRepository;
import com.polaroid.chatweb.repository.user.UserRepository;
import com.polaroid.chatweb.service.EmailService;

/**
 * Controlador de requisições de usuários
 * 
 * @author Guilherme Melo e Leonardo Braz
 * @version 1.0
 *
 */
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

	@RequestMapping(value = "/profile/delete", method = RequestMethod.GET)
	public ModelAndView deleteAccount(Authentication auth) {
		ModelAndView mv = new ModelAndView("usuario/deleteAccount");
		mv.addObject("user", (User) auth.getPrincipal());
		return mv;
	}

	@RequestMapping(value = "/profile/delete", method = RequestMethod.POST)
	public String deleteAccountPost(Authentication auth, @RequestParam("password") String password,
			RedirectAttributes attr) {
		User user = (User) auth.getPrincipal();

		userRepository.deleteById(user.getId());
		return "redirect:/logout";
	}

	@RequestMapping(value = "/profile/friends/search", method = RequestMethod.GET)
	public ModelAndView searchFriends(Authentication auth) {
		ModelAndView mv = new ModelAndView("usuario/sendFriendRequest");
		ArrayList<User> list = (ArrayList<User>) userRepository.findAll();
		User user = userRepository.findById(((User) auth.getPrincipal()).getId()).get();
		list.remove(user);
		list.removeAll(user.getFriends());
		list.removeAll(user.getRequisicoes());
		mv.addObject("users", list);
		return mv;
	}

	@RequestMapping(value = "/profile/friends/add/{newFriendNick}", method = RequestMethod.GET)
	public String sendRequestToFriend(Authentication auth, @PathVariable("newFriendNick") String nickName) {
		User user = (User) auth.getPrincipal();
		User u2 = userRepository.findByUsernameOrEmail(nickName, nickName).get();
		u2.sendFriendRequest(user);
		userRepository.save(u2);

		return "redirect:/profile/friends/search";
	}

	@RequestMapping(value = "/profile/requests/", method = RequestMethod.GET)
	public ModelAndView showFriends(Authentication auth) {
		ModelAndView mv = new ModelAndView("usuario/addFriend");
		User user = (User) auth.getPrincipal();
		user = this.userRepository.findById(user.getId()).get();
		mv.addObject("invitations", user.getRequisicoes());
		return mv;
	}

	@RequestMapping(value = "/profile/requests/{friendNick}/accept", method = RequestMethod.GET)
	public String accepetFriend(@PathVariable("friendNick") String nickName, Authentication auth) {
		User user = (User) auth.getPrincipal();
		User optUser = userRepository.findByUsernameOrEmail(nickName, nickName).get();
		user = userRepository.findById(user.getId()).get();
		user.acceptFriend(optUser);
		User u2 = optUser;
		u2.acceptFriend(user);
		
		userRepository.save(user);
		userRepository.save(u2);

		return "redirect:/profile/requests/";
	}

	@RequestMapping(value = "/profile/requests/{friendNick}/reject", method = RequestMethod.GET)
	public String rejecttFriend(@PathVariable("friendNick") String nickName, Authentication auth) {
		User user = (User) auth.getPrincipal();
		Optional<User> optUser = userRepository.findByUsernameOrEmail(nickName, nickName);
		user.rejectFriend(optUser.get());
		userRepository.save(user);

		return "redirect:/profile/requests/";
	}
	
	
	@GetMapping(path = "/friends/{user}", produces = "application/json")
	@ResponseBody
	public List<User> getFriendsFrom(@PathVariable("user") String username) {
		User user = this.userRepository.findByUsernameOrEmail(username, username).get();
		
		return user.getFriends();
	}

}
