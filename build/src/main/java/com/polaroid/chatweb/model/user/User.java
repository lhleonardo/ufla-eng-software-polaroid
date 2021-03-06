package com.polaroid.chatweb.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements UserDetails {

	// need this constant for serialize within session's
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@NotNull
	private String username;

	@Column(unique = true)
	@Email
	@NotNull
	private String email;

	@Column
	@NotNull
	private String ownerName;

	@Column
	private String password;

	@Column(nullable = false)
	private boolean isValidated;

	@JsonIgnore
	@ManyToMany
	private List<User> friends;
	
	@JsonIgnore
	@ManyToMany
	private List<User> requests;

	User() {
		this.requests = new ArrayList<>();
		this.friends = new ArrayList<>();
	}

	public User(Long id, @NotNull String username, @Email @NotNull String email, @NotNull String ownerName,
			@NotNull String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.ownerName = ownerName;
		this.password = password;
		this.isValidated = false;
	}

	public Long getId() {
		return id;
	}

	public void acceptFriend(User user) {
		requests.remove(user);
		friends.add(user);
	}

	public void rejectFriend(User user) {
		requests.remove(user);
	}

	public List<User> getFriends() {
		return this.friends;
	}

	public List<User> getRequisicoes() {
		return this.requests;
	}

	public void sendFriendRequest(User user) {
		this.requests.add(user);
	}

	public String getEmail() {
		return email;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getPassword() {
		return password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void validate() {
		this.isValidated = true;
	}

	public boolean isEmailVerified() {
		return this.isValidated;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.isValidated;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
