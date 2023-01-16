package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Nurse;
import com.me.pojo.Pharmacy;
import com.me.pojo.Vaccine;
import com.me.pojo.VaccineRequest;

public class VaccineRequestDAO extends DAO {
	
	public void save(VaccineRequest vaccineRequest) {
		try {
			begin();
			getSession().save(vaccineRequest);
			commit();
		} catch(HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

}
	