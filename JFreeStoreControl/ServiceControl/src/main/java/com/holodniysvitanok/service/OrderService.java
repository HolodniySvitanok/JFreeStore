package com.holodniysvitanok.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.entity.Order;
import com.holodniysvitanok.entity.User;

public interface OrderService {
	
	void selectOrder(HttpSession session);
	
	void completeOrderForUser(HttpServletRequest request);

	ModelAndView setComplete(Order order, ModelAndView model, HttpServletRequest request);

	void setNotComplete(Order order);
	
	/*
	 * 
	 * 
	 * DAO
	 * */
	
	long getLastID();
	
	boolean saveOrUpdate(Order order);
	
	List<Order> getAllActiveOrders();
	
	List<Order> getAllActiveOrdersUser(User user);
	
	List<Order> getAllNewOrders();
	
	List<Order> getCompleteOrders(int start, int maxRows);
	
	Order getOrderById(long id);
	
	void delete(Order order);
	
	Order userSelectOrder(User user);
}
