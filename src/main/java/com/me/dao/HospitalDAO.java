package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Hospital;

public class HospitalDAO extends DAO {

	public List<Hospital> list() {
		 try {
	           begin();
	           Query query = getSession().createQuery("From Hospital");
	           List<Hospital> list = query.list();
	           commit();
	           return list;
	        } catch (HibernateException e) {
	            rollback();
	            e.printStackTrace();
	            throw new HibernateException(e);
	        }
	}
	
	public void save(Hospital hopital) {
		try {
			begin();
			getSession().save(hopital);
			commit();
		} catch(HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public Hospital getHospitalByEmail(String email) {
		Hospital hospital = null;
		try {
			begin();
			Query q = getSession().createQuery("from Hospital where email= :email");
			q.setParameter("email", email);
			hospital = (Hospital) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return hospital;
	}
	
	public Hospital get(int id) {
		Hospital hospital = null;
		try {
			begin();
			hospital = (Hospital) getSession().get(Hospital.class, id);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return hospital;
	}
	
	public void delete(Hospital hospital) {
		try {
			begin();
			getSession().delete(hospital);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
}
	