package com.polaroid.chatweb.model.chat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.polaroid.chatweb.model.user.User;

@Entity
public class Chat implements Comparable<Chat>{

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
	private List<Message> messages;
	
	
	@Override
	public String toString() {
		return participant.getOwnerName();
	}
	
	public Long getId() {
		return id;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public List<Message> getMessages() {
		Collections.sort(messages);
		return messages;
	}
	
	public String getNewestMessage() {
		return getMessages().get(0).getContent();
	}
	
	public LocalDateTime getDate() {
		return this.getMessages().get(0).getCreateAt();
	}

	@Override
	public int compareTo(Chat o) {
		if(this.getMessages().get(0).getCreateAt().isAfter(o.getMessages().get(0).getCreateAt())) {
			return 1;
		}else if (this.getMessages().get(0).getCreateAt().isBefore(o.getMessages().get(0).getCreateAt())) {
			return -1;
		}
		return 0;
	}

}
