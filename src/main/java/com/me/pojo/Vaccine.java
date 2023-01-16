package com.me.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Vaccine")
@Table(name = "vaccine")
public class Vaccine {

	@Id
	@Column(name = "idvaccine")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idvaccine;

	private String vaccineName;
	
	private String inStock;
	
	private int hospitalId;

	public int getIdvaccine() {
		return idvaccine;
	}

	public void setIdvaccine(int idvaccine) {
		this.idvaccine = idvaccine;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getInStock() {
		return inStock;
	}

	public void setInStock(String inStock) {
		this.inStock = inStock;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	
}
