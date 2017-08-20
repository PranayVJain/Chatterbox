package com.messenger.chatterbox.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	private int id;
	
	private String username;
	
	private String emailId;
	
	private String password;
	
	private int mobileNo;

	
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPassword() {
		return password;
	}

	public int getMobileNo() {
		return mobileNo;
	}
	
	
	
}
