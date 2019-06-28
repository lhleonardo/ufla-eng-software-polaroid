package com.polaroid.chatweb.model.chat;

import java.time.LocalDateTime;
import java.util.HashSet;
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
	
	
	public Chat() {
		this.messages = new HashSet<>();
	}
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
		this.participant = participant;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addMessage(Message m) {
		this.messages.add(m);
	}
	
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
