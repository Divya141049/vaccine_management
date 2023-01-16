package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Nurse;
import com.me.pojo.Pharmacy;

public class NurseDAO extends DAO {

	public List<Nurse> list() {
		 try {
	           begin();
	           Query query = getSession().createQuery("From Nurse");
	           List<Nurse> list = query.list();
	           commit();
	           return list;
	        } catch (HibernateException e) {
	            rollback();
	            e.printStackTrace();
	            throw new HibernateException(e);
	        }
	
	}
	
	public void save(Nurse nurse) {
		try {
			begin();
			getSession().save(nurse);
			commit();
		} catch(HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public Nurse getNurseByEmail(String email) {
		Nurse nurse = null;
		try {
			begin();
			Query q = getSession().createQuery("from Nurse where email= :email");
			q.setParameter("email", email);
			nurse = (Nurse) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return nurse;
	}
	
	public Nurse get(int id) {
		Nurse nurse = null;
		try {
			begin();
			nurse = (Nurse) getSession().get(Nurse.class, id);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return nurse;
	}
	
	public void delete(Nurse nurse) {
		try {
			begin();
			getSession().delete(nurse);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
}
	