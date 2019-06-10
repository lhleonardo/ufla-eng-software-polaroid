package com.polaroid.chatweb.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.polaroid.chatweb.model.User;

public interface UserRepository extends CrudRepository<User, Long>  {
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	
}
