package com.polaroid.chatweb.model.chat;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User author;

	public Message() {
		this.createdAt = LocalDateTime.now();
	}

	public void visualize() {
		if (visualizedAt != null) {
			this.visualizedAt = LocalDateTime.now();
		}
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getVisualizedAt() {
		return visualizedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

}
