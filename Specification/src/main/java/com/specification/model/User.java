package com.specification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="user_details")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="user_Id")
	private int userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	/*
	 * @Formula("concat(firstName,lastName )") private String fullName;
	 */
	
	/*
	 * public String getFullName() { return fullName; }
	 * 
	 * public void setFullName(String fullName) { this.fullName = fullName; }
	 */
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
