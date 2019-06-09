package com.polaroid.chatweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@NotNull
	private String nickname;

	@Column(unique = true)
	@Email
	@NotNull
	private String email;

	@Column
	@NotNull
	private String ownerName;

	@Column
	private String password;

	User() {

	}

	public User(Long id, @NotNull String nickname, @Email @NotNull String email, @NotNull String ownerName,
			String password) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.ownerName = ownerName;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getPassword() {
		return password;
	}

}
