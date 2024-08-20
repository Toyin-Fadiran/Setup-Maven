package com.example.estore.dao;

import java.util.List;

//generic interface for specific DAO Classes
public interface DAO<T> {
	
	T get (long id); //REtrieve operation using PK ID (from the DB)
	List<T> getAll();  //Retrieve All operation 
	void save (T object); //Create new record in DB
	void update (T object);//Update existing record in DB
	void delete(long id); //Delete existing record using PK from DB

}
