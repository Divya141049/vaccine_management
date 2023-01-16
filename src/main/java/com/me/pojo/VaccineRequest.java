package com.me.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "VaccineRequest")
@Table(name = "vaccinerequest")
public class VaccineRequest {

	@Id
	@Column(name = "idvaccinerequest")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idvaccinerequest;
	
	private String hospitalEmail;
	
	private String patientEmail;
	
	private String dose;
	
	private Date date;
	
	private String status;
	
	private String pharmacyEmail;
	
	private String vaccine;

	public int getIdvaccinerequest() {
		return idvaccinerequest;
	}

	public void setIdvaccinerequest(int idvaccinerequest) {
		this.idvaccinerequest = idvaccinerequest;
	}

	public String getHospitalEmail() {
		return hospitalEmail;
	}

	public void setHospitalEmail(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPharmacyEmail() {
		return pharmacyEmail;
	}

	public void setPharmacyEmail(String pharmacyEmail) {
		this.pharmacyEmail = pharmacyEmail;
	}

	public String getVaccine() {
		return vaccine;
	}

	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}
	
}
