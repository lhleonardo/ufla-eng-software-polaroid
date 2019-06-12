package com.polaroid.chatweb.repository.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.polaroid.chatweb.model.user.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
	/**
	 * Busca um registro de token
	 * @param token existente
	 * @return Instância de um token válido, em Optional
	 */
	Optional<ConfirmationToken> findByToken(String token);	
}
