package com.example.estore.enduser.dao;

import com.example.estore.admin.model.Orders;
import com.example.estore.admin.model.Users;

public interface IOrderDAO {
	
	
	public Boolean placeOrder(Users users, Orders orders);
	
	public Orders getOrderDetails(int orderId);

}
