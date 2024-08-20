package com.example.estore.enduser.dao;

import java.util.HashMap;
import java.util.Map;

import com.example.estore.admin.model.Orders;
import com.example.estore.admin.model.Users;

public class OrderDAO implements IOrderDAO {

	private Map<Integer, Users> userEntry;
	private Map<Integer, Orders> orderEntry;

	public OrderDAO() {
		userEntry = new HashMap<Integer, Users>();
		orderEntry = new HashMap<Integer, Orders>();
	}

	@Override // user1 //order3
	public Boolean placeOrder(Users users, Orders orders) {
		// TODO Auto-generated method stub
		
		//i still need CODE to connect to my DB and place an order.
		//once that is done, i can return true or false, depending on
		//success of placing order to SQL RDBMS.
		return true;
	}

	@Override
	public Orders getOrderDetails(int orderId) {
	//i'm going to write CODE that pulls orderDetails from my SQL Server
		//Logic would go here
		
		Orders orders = new Orders();
		//write code here to 
		// 1. pull orderdetails from the sql server.
		// 2. populate those details in my Order object created above.
		
		
		//eventually after all that logic is written, return
		//the now-initialized orders object back to the caller
		return orders;
	}
}