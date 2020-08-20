package com.cg.edu.dto;

import javax.persistence.*;

@Entity
@Table(name="job_user_db")
public class User {
	
	@Id
	@GeneratedValue
	private Long userId;
	private String userName;
	private String userPassword;
	private String userContact;
	private String userEmail;
	private String gender;
	
	

	public User() {
		
	}

	public User(Long userId, String userName, String userPassword, String userContact, String userEmail, String gender) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userContact = userContact;
		this.userEmail = userEmail;
		this.gender = gender;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userContact="
				+ userContact + ", userEmail=" + userEmail + ", gender=" + gender + "]";
	}
	
}
