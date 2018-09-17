package com.messenger.chatterbox.backend;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBContext {

	private static DBContext dbContext;
	private SessionFactory sessionFactory;

	private DBContext() {
		Configuration config = new Configuration();
		config.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
				.build();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
	}

	public static DBContext getInstance() {
		if (dbContext == null) {
			dbContext = new DBContext();
		}
		return dbContext;
	}
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
}
