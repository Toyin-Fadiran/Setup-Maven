package com.example.estore.enduser.service;

import com.example.estore.admin.model.Orders;
import com.example.estore.admin.model.Users;

public interface IOrderService {
	
	public Boolean placeOrder(Users users, Orders orders);

}
