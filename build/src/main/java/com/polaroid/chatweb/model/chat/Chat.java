package com.polaroid.chatweb.model.chat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.polaroid.chatweb.model.user.User;

@Entity
public class Chat implements Comparable<Chat> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User owner;

	@ManyToOne
	private User participant;

	private LocalDateTime createdAt;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "chat_messages", joinColumns = {
			@JoinColumn(name = "chat_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "message_id", nullable = false) })
	private Set<Message> messages;

	public Chat() {
		this.messages = new HashSet<Message>();
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


	public List<Message> getMessages() {
		ArrayList<Message> list = new ArrayList<>(this.messages);
		Collections.sort(list);
		return list;
	}

	public String getNewestMessage() {
		return getMessages().get(0).getContent();
	}

	public LocalDateTime getDate() {
		return this.getMessages().get(0).getCreateAt();
	}

	@Override
	public int compareTo(Chat o) {
		if (this.getMessages().size() == 0) {
			return -1;
		}
		if (this.getMessages().get(0).getCreateAt().isAfter(o.getMessages().get(0).getCreateAt())) {
			return 1;
		} else if (this.getMessages().get(0).getCreateAt().isBefore(o.getMessages().get(0).getCreateAt())) {
			return -1;
		}
		return 0;
	}

}
