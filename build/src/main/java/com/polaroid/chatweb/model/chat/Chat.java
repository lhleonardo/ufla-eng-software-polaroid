package com.polaroid.chatweb.model.chat;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.polaroid.chatweb.model.user.User;

@Entity
public class Chat {

	@Id
	private Long id;

	@ManyToOne
	private User owner;

	@ManyToOne
	private User participant;

	private LocalDateTime createdAt;

	@ManyToMany()
	@JoinTable(name = "chat_messages", joinColumns = {
			@JoinColumn(name = "chat_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "message_id", nullable = false) })
	private Set<Message> messages;
	
	
	@Override
	public String toString() {
		return participant.getOwnerName();
	}
	
	public Long getId() {
		return id;
	}
	
	public Set<Message> getMessages() {
		return messages;
	}

}
