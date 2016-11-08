package com.holodniysvitanok.dao;

import com.holodniysvitanok.entity.OrderDetails;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

public interface OrderDetailsDAO {

	boolean saveOrUpdate(OrderDetails orderDetails);
		
	void delete(OrderDetails orderDetails);
	
}
