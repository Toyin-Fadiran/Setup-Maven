package com.example.estore.dao;

import java.util.List;

//Generic interface for CRUD operations
public interface DAO_two<T> {
	//field
	public static final int fakeField = 1;
	
	
	//methods
	T get (long id);
	
	List<T> getAll();
	
	void save (T object);
	
	void update (T object);
	
	void delete(long id);
}