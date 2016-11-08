package com.holodniysvitanok.dao;

import java.util.List;

import com.holodniysvitanok.entity.Order;
import com.holodniysvitanok.entity.User;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

public interface OrderDAO {

	long getLastID();

	boolean saveOrUpdate(Order order);
	
	List<Order> getAllActiveOrdersUser(User user);
	
	List<Order> getAllActiveOrders();
	
	List<Order> getAllNewOrders();
	
	List<Order> getCompleteOrders(int start, int maxRows);
	
	Order getOrderById(long id);
	
	void delete(Order order);
	
	Order userSelectOrder(User user);
}
