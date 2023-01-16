package com.me.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.me.pojo.User;

public class UserDAO extends DAO {

	public void save(User user) {
		try {
			begin();
			getSession().saveOrUpdate(user);
			commit();
		} catch(HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public User getByEmail(String email) {
		User user = null;
		System.out.println("email in dao "+email);
		try {
			begin();
			Query q = getSession().createQuery("from User where Email= :Email");
			q.setParameter("Email", email);
			user = (User) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return user;
	}
	
	public void deleteByEmail(String email) {
		System.out.println("email in dao "+email);
		try {
			begin();
			Query q = getSession().createQuery("delete from User where Email= :Email");
			q.setParameter("Email", email);
			q.executeUpdate();
			System.out.println("user also deleted");
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
}
	