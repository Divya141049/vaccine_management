package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Hospital;
import com.me.pojo.Nurse;
import com.me.pojo.Patient;
import com.me.pojo.Pharmacy;
import com.me.pojo.Vaccine;

public class PatientDAO extends DAO {
	
	public void save(Patient patient) {
		try {
			begin();
			getSession().saveOrUpdate(patient);
//			getSession().up
			commit();
		} catch(HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public Patient getPatientByEmail(String email) {
		Patient patient = null;
		try {
			begin();
			Query q = getSession().createQuery("from Patient where email= :email");
			q.setParameter("email", email);
			patient = (Patient) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return patient;
	}

}
	