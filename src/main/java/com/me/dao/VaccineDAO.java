package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Nurse;
import com.me.pojo.Pharmacy;
import com.me.pojo.Vaccine;

public class VaccineDAO extends DAO {

	public List<Vaccine> list(int hospitalId) {
		 try {
	           begin();
	           Query query = getSession().createQuery("From Vaccine where hospitalId= :hospitalId");
	           query.setParameter("hospitalId", hospitalId);
	           List<Vaccine> list = query.list();
	           commit();
	           return list;
	        } catch (HibernateException e) {
	            rollback();
	            e.printStackTrace();
	            throw new HibernateException(e);
	        }
	
	}
	
	public List<Vaccine> getAvailableVaccines(int hospitalId) {
		 try {
	           begin();
	           String inStock = "Available";
	           Query query = getSession().createQuery("From Vaccine where hospitalId= :hospitalId and inStock= :inStock");
	           query.setParameter("hospitalId", hospitalId);
	           query.setParameter("inStock", inStock);
//	           Query query = sessionFactory.getCurrentSession().createQuery(hql);
//	           System.out.println(query);
//	           query.setString(0, u);
//	           query.setString(1, p);
	           List<Vaccine> list = query.list();
	           commit();
	           return list;
	        } catch (HibernateException e) {
	            rollback();
	            e.printStackTrace();
	            throw new HibernateException(e);
	        }
	
	}
	
	public void save(Vaccine vaccine) {
		try {
			begin();
			getSession().save(vaccine);
			commit();
		} catch(HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public Vaccine getVaccine(int id) {
		Vaccine vaccine = null;
		try {
			begin();
			vaccine = (Vaccine) getSession().get(Vaccine.class, id);
			commit();
		} catch(HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return vaccine;
	}

}
	