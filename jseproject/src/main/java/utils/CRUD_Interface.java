package utils;

import java.util.Collection;

import io.altar.jseproject.model.Entity;


public abstract interface CRUD_Interface<T extends Entity> {

	long addEntity(T e);
	
	T getEntity(long id);
	
	Collection<T> getAll();
	
	void editEntity(T e);
	
	void removeEntity(long id);
}