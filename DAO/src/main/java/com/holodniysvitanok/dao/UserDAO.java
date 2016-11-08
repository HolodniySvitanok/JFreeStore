package com.holodniysvitanok.dao;

import java.util.List;

import com.holodniysvitanok.entity.Order;
import com.holodniysvitanok.entity.User;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

public interface UserDAO {

	User getUserByLoginAndPassword(String login, String password);
	
	boolean saveUser(User user);
	
	User getUserById(long id);
	
	void deleteUser(User user);
	
	List<User> getAllUsers(int start, int maxRows);
	
	List<User> getAllUsers(int count);
	
	List<User> getUserWhoHaveSelectOrder(Order order);
	
	
	
}
