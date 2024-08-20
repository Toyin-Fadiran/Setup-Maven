package com.example.estore.enduser.service;

import com.example.estore.admin.model.Orders;
import com.example.estore.admin.model.Users;
import com.example.estore.enduser.dao.OrderDAO;

public class OrderService implements IOrderService {

	
	//bringing in the DAO (Data Access Object as an attribute
	//or field of teh OrderService Class
	private  OrderDAO dao;
	
	
	
	//constructor
	public OrderService() {
		
		dao = new OrderDAO();
	}


	@Override
	public Boolean placeOrder(Users user, Orders order) {
		// TODO Auto-generated method stub
		
		//Some more validation methods before the order is placed
		//goes here.
		
		return dao.placeOrder(user, order);
	}
}
