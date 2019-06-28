package com.polaroid.chatweb.repository.chat;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.polaroid.chatweb.model.chat.Chat;
import com.polaroid.chatweb.model.user.User;

public interface ChatRepository extends CrudRepository<Chat, Long> {

	List<Chat> findByOwner(User owner);

}
