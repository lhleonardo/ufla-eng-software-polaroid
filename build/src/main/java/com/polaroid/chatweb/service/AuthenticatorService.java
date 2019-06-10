package com.polaroid.chatweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.polaroid.chatweb.model.user.User;
import com.polaroid.chatweb.repository.user.UserRepository;

@Service
public class AuthenticatorService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String loginInformed) throws UsernameNotFoundException {
		Optional<User> result = repository.findByUsernameOrEmail(loginInformed, loginInformed);
		if (result.isEmpty()) {
			throw new UsernameNotFoundException(String.format("Usuário % não existe.", loginInformed));
		}

		return result.get();
	}

}
 