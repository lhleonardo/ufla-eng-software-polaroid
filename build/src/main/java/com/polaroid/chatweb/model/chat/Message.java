package com.polaroid.chatweb.model.chat;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.polaroid.chatweb.model.user.User;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String content;

	@Column
	private LocalDateTime createdAt;
	@Column
	private LocalDateTime visualizedAt;

	@OneToOne
	private User author;

}
