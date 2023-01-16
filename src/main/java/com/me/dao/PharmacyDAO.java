package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Pharmacy;

public class PharmacyDAO extends DAO {

	public List<Pharmacy> list() {
		 try {
	           begin();
	           Query query = getSession().createQuery("From Pharmacy");
	           List<Pharmacy> list = query.list();
	           commit();
	           return list;
	        } catch (HibernateException e) {
	            rollback();
	            e.printStackTrace();
	            throw new HibernateException(e);
	        }
	
	}
	
	public void save(Pharmacy pharmacy) {
		try {
			begin();
			getSession().save(pharmacy);
			commit();
		} catch(HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public Pharmacy getPharmacyByEmail(String email) {
		Pharmacy pharmacy = null;
		try {
			begin();
			Query q = getSession().createQuery("from Pharmacy where email= :email");
			q.setParameter("email", email);
			pharmacy = (Pharmacy) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return pharmacy;
	}
	
	public Pharmacy get(int id) {
		Pharmacy pharmacy = null;
		try {
			begin();
			pharmacy = (Pharmacy) getSession().get(Pharmacy.class, id);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return pharmacy;
	}
	
	public void delete(Pharmacy pharmacy) {
		try {
			begin();
			getSession().delete(pharmacy);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
}
	