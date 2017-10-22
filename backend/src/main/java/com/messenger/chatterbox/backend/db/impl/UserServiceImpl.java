package com.messenger.chatterbox.backend.db.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.messenger.chatterbox.backend.db.dao.UserDao;
import com.messenger.chatterbox.backend.db.dao.UserService;
import com.messenger.chatterbox.backend.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public UserServiceImpl(){
		userDao = new UserDaoImpl();
	}
	
	public void save(User user) {
		
		userDao.save(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userDao.getAll();
		
	}

	
}
