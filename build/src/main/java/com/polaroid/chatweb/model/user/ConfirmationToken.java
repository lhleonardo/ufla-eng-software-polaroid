package com.polaroid.chatweb.model.user;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String token;

	@Column
	private LocalDateTime createdAt;

	@OneToOne
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	public ConfirmationToken(User user) {
		this.user = user;
		this.createdAt = LocalDateTime.now();
		this.token = UUID.randomUUID().toString();
	}

	public Long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public User getUser() {
		return user;
	}

}
