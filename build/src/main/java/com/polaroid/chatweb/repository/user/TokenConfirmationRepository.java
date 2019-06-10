package com.polaroid.chatweb.repository.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.polaroid.chatweb.model.user.ConfirmationToken;

public interface TokenConfirmationRepository extends CrudRepository<ConfirmationToken, Long> {
	Optional<ConfirmationToken> findByToken(String token);	
}
