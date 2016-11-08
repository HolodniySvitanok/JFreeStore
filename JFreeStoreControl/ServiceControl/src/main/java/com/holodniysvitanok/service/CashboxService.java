package com.holodniysvitanok.service;


import java.math.BigDecimal;

import com.holodniysvitanok.entity.Cashbox;
import com.holodniysvitanok.entity.Order;
import com.holodniysvitanok.entity.User;

public interface CashboxService {

	void createMainCashbox(); 
	
	void saveOrUpdate(Cashbox cashbox);

	void profit(Order order);
	
	boolean takeCash(User user, BigDecimal sum);
	
	boolean takeCash(long idCashbox, BigDecimal sum);
	
	Cashbox getMainCashbox();
	
	Cashbox getUserCashbox(User user);
	
	Cashbox getSumCashbox();
	
	void returnCashbox(Order order);
	
	Cashbox takeCashbox(String type, User user);
}
