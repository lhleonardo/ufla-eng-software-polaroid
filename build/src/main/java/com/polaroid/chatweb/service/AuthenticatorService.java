package com.polaroid.chatweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.polaroid.chatweb.model.User;
import com.polaroid.chatweb.repository.UserRepository;

@Service
public class AuthenticatorService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> result = repository.findByUsername(username);

		if (result.isEmpty()) {
			throw new UsernameNotFoundException(String.format("Usuário % não existe.", username));
		}

		return result.get();
	}

}
