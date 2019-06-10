package com.polaroid.chatweb.repository.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.polaroid.chatweb.model.user.User;

public interface UserRepository extends CrudRepository<User, String>  {
	Optional<User> findByUsernameOrEmail(String username, String email);
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
}
