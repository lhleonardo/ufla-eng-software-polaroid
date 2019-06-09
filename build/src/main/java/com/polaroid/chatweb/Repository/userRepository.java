package com.polaroid.chatweb.Repository;

import org.springframework.data.repository.CrudRepository;

import com.polaroid.chatweb.model.User;

public interface userRepository extends CrudRepository<User, String>  {

}
