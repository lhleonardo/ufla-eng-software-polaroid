package com.polaroid.chatweb.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.polaroid.chatweb.model.user.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsernameOrEmail(String username, String email);

	/**
	 * Confirma o endereço de e-mail de um usuario
	 * 
	 * @param email a ser confirmado
	 */
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.isValidated = true WHERE u.email = :email")
	void confirmEmail(@Param("email") String email);
}
