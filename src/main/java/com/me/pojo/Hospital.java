package com.me.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Hospital")
@Table(name = "hospital")
public class Hospital {

	@Id
	@Column(name = "idhospital")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idhospital;

	private String name;
	
	private String address;
	
	private int contact;
	
	private String email;

	public int getIdhospital() {
		return idhospital;
	}

	public void setIdhospital(int idhospital) {
		this.idhospital = idhospital;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
