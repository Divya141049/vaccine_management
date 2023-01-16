package com.me.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Patient")
@Table(name = "patient")
public class Patient {

	@Id
	@Column(name = "idpatient")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idpatient;
	
	private String email;
	
	private Date dob;
	
	private String address;
	
	private Date firstDoseDate;
	
	private String firstDoseStatus;
	
	private String firstDoseVaccine;
	
	private Date secondDoseDate;
	
	private String secondDoseStatus;
	
	private String secondDoseVaccine;
	
	private Date boosterDate;
	
	private String boosterStatus;
	
	private String boosterVaccine;

	public int getIdpatient() {
		return idpatient;
	}

	public void setIdpatient(int idpatient) {
		this.idpatient = idpatient;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getFirstDoseDate() {
		return firstDoseDate;
	}

	public void setFirstDoseDate(Date firstDoseDate) {
		this.firstDoseDate = firstDoseDate;
	}

	public String getFirstDoseStatus() {
		return firstDoseStatus;
	}

	public void setFirstDoseStatus(String firstDoseStatus) {
		this.firstDoseStatus = firstDoseStatus;
	}

	public Date getSecondDoseDate() {
		return secondDoseDate;
	}

	public void setSecondDoseDate(Date secondDoseDate) {
		this.secondDoseDate = secondDoseDate;
	}

	public String getSecondDoseStatus() {
		return secondDoseStatus;
	}

	public void setSecondDoseStatus(String secondDoseStatus) {
		this.secondDoseStatus = secondDoseStatus;
	}

	public Date getBoosterDate() {
		return boosterDate;
	}

	public void setBoosterDate(Date boosterDate) {
		this.boosterDate = boosterDate;
	}

	public String getBoosterStatus() {
		return boosterStatus;
	}

	public void setBoosterStatus(String boosterStatus) {
		this.boosterStatus = boosterStatus;
	}

	public String getFirstDoseVaccine() {
		return firstDoseVaccine;
	}

	public void setFirstDoseVaccine(String firstDoseVaccine) {
		this.firstDoseVaccine = firstDoseVaccine;
	}

	public String getSecondDoseVaccine() {
		return secondDoseVaccine;
	}

	public void setSecondDoseVaccine(String secondDoseVaccine) {
		this.secondDoseVaccine = secondDoseVaccine;
	}

	public String getBoosterVaccine() {
		return boosterVaccine;
	}

	public void setBoosterVaccine(String boosterVaccine) {
		this.boosterVaccine = boosterVaccine;
	}
	
}
