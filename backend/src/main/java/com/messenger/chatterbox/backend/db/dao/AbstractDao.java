package com.messenger.chatterbox.backend.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.messenger.chatterbox.backend.model.BaseModel;

public class AbstractDao<T extends BaseModel> {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void persist(T entity){
		getSession().persist(entity);
	}
	
	public void updateEntity(T entity){
		getSession().update(entity);
	}
	
	public List<T> getAllEntities(){
		/**
		 * TODO: Need a valid query
		 */
		return null;
	}
}
