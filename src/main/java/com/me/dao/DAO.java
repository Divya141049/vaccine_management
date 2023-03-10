package com.me.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class DAO {
	
	private static final Logger log = Logger.getAnonymousLogger();
	private static final ThreadLocal sessionThread = new ThreadLocal();
	private static final SessionFactory sessionFactory =  new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public static Session getSession() {	
		Session session = (Session) DAO.sessionThread.get();
		if(session == null) {
			session = sessionFactory.openSession();
			DAO.sessionThread.set(session);
		}
		return session;
	}
	
	public void begin() {
		getSession().beginTransaction();
	}
	
	public void commit() {
		getSession().getTransaction().commit();
	}
	
	public void rollback() {
		try {
			getSession().getTransaction().rollback();
		}
		catch (HibernateException e) {
			log.log(Level.WARNING, "Cannot rollback", e);
		}
		try {
			getSession().close();
		}
		catch (HibernateException e) {
			log.log(Level.WARNING, "Cannot close", e);
		}
		DAO.sessionThread.set(null);
	}
	
	public static void close() {
		getSession().close();
		DAO.sessionThread.set(null);
	}

}
