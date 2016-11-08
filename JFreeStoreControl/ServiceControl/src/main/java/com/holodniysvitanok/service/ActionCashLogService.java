package com.holodniysvitanok.service;

import java.util.List;

import com.holodniysvitanok.entity.ActionCashLog;
import com.holodniysvitanok.entity.Cashbox;
import com.holodniysvitanok.entity.User;

public interface ActionCashLogService {

	String ADD_CASH_FROM_ORDER = "Добавление средств от заказа";
	
	String RETURN_CASH_FROM_CANCELED_ORDER = "Возврат средств из-за отмены заказа";
	
	String DEBIT = "Списание (снятие) средств";

	void addLog(ActionCashLog actionCashLog);

	List<ActionCashLog> getLastLog(User user, int maxCount);

	List<ActionCashLog> getLastLog(User user);
	
	List<ActionCashLog> getLastLog(Cashbox cashbox, int maxCount);
	
	List<ActionCashLog> getLastLog(Cashbox cashbox);

	
}
