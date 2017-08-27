package com.messenger.chatterbox.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User extends BaseModel{

	@Id
	private long id;
	
	private String username;
	
	private String emailId;
	
	private String password;
	
	private long mobileNo;

	
	public User(long id, String username, String emailId, String password, long mobileNo) {
		super();
		this.id = id;
		this.username = username;
		this.emailId = emailId;
		this.password = password;
		this.mobileNo = mobileNo;
	}
	
	public long getId() {
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

	public long getMobileNo() {
		return mobileNo;
	}
	
	
	
}
