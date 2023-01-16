package com.me.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "User")
@Table(name = "register")
public class User {

	@Id
	@Column(name = "idregister")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idregister;

	private String FirstName;
	
	private String LastName;
	
	private String Email;
	
	private String Password;
	
	private int Phone;
	
	private String Role;
	
	public int getIdregister() {
		return idregister;
	}
	public void setIdregister(int idregister) {
		this.idregister = idregister;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getPhone() {
		return Phone;
	}
	public void setPhone(int phone) {
		Phone = phone;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
}
