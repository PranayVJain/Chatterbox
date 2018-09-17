package com.messenger.chatterbox.backend.api;

import java.util.List;

import com.messenger.chatterbox.backend.model.BaseModel;

public interface IDao<T extends BaseModel>{

	
	public void save(T entity);
	public void update(T entity);
	public List<T> getAll();
	
}
