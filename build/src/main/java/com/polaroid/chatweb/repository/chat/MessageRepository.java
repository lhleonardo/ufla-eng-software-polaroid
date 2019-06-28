package com.polaroid.chatweb.repository.chat;

import org.springframework.data.repository.CrudRepository;

import com.polaroid.chatweb.model.chat.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{

}
