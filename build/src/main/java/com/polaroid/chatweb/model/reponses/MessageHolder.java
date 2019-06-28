package com.polaroid.chatweb.model.reponses;

public class MessageHolder {
	private String from;
	private String to;

	private String content;

	public MessageHolder() {

	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
