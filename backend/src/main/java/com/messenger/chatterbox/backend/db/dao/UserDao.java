package com.messenger.chatterbox.backend.db.dao;

import org.hibernate.Session;

import com.messenger.chatterbox.backend.api.IDao;
import com.messenger.chatterbox.backend.model.User;

public interface UserDao extends IDao<User>{

	public Session getSession();
}
