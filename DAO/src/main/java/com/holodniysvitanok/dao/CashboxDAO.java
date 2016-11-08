package com.holodniysvitanok.dao;

import java.math.BigDecimal;
import java.util.List;

import com.holodniysvitanok.entity.Cashbox;
import com.holodniysvitanok.entity.User;

public interface CashboxDAO {

	
	void saveOrUpdate(Cashbox cashbox);
	
	List<Cashbox> getAllCashbox();
	
	Cashbox getUserCashbox(User user);
	
	Cashbox getMainCashbox();
	
	Cashbox getCashboxById(long id);
	
	boolean takeCash(User user, BigDecimal sum);
	
	boolean takeCash(Cashbox cashbox, BigDecimal sum);
}
