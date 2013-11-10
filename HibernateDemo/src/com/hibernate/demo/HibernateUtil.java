package com.hibernate.demo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


@SuppressWarnings("deprecation")
public class HibernateUtil {
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static SessionFactory sessionFactory = null;  ///sessionFactory对象
	static{
		try{
			Configuration cfg = new Configuration().configure();
			ServiceRegistry  serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();     
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			//sessionFactory = cfg.buildSessionFactory();
		}catch(Exception e)
		{
			System.err.println("创建session工厂失败");
			e.printStackTrace();
		}
	}//由于创建sessionFaction会耗费大量资源所以用static创建一次
	/*
	 * 获取去session
	 */
	public static Session getSession() throws HibernateException
	{
		Session session = (Session)threadLocal.get();
		if(session == null || !session.isOpen())
		{
			if(sessionFactory==null)
			{
				rebuildSessionFactory();
			}
			session = (sessionFactory!=null)?sessionFactory.openSession():null;
	        threadLocal.set(session);
		}
		return session;
	}
	/*
	 * 重建回话工厂
	 */
	public static void rebuildSessionFactory()
	{
		try{
			Configuration cfg = new Configuration().configure();
			//sessionFactory = cfg.buildSessionFactory();
			//ServiceRegistryBuilder serviceRegistry = new ServiceRegistryBuilder();
			ServiceRegistry  serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();     
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		}catch(Exception e)
		{
			System.err.println("创建session工厂失败");
			e.printStackTrace();
		}
	}
	/*
	 * 获取回话工厂
	 */
	public static SessionFactory getSessionFacory()
	{
		return sessionFactory;
	}
	/*
	 * 关闭session
	 */
	public static void closeSession() throws HibernateException
	{
		Session session = (Session)threadLocal.get();
		threadLocal.set(null);
		if(session!=null)
		{
			session.close();
		}
	}
}
