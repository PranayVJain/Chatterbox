package com.messenger.chatterbox.backend.db.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.messenger.chatterbox.backend.db.dao.AbstractDao;
import com.messenger.chatterbox.backend.db.dao.UserDao;
import com.messenger.chatterbox.backend.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<User> implements UserDao{

	public void save(User user) {
		super.persist(user);
		
	}

	public void update(User user) {
		super.updateEntity(user);
	}

	public List<User> getAll() {
		return super.getAllEntities();
	}

	

}
