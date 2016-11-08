package com.holodniysvitanok.dao;

import java.util.List;

import com.holodniysvitanok.entity.ActionCashLog;
import com.holodniysvitanok.entity.Cashbox;
import com.holodniysvitanok.entity.User;

public interface ActionCashLogDAO {

	void addLog(ActionCashLog actionCashLog);
	
	List<ActionCashLog> getLastLog(User user, int  maxCount);

	List<ActionCashLog> getLastLog(Cashbox cashbox, int  maxCount);
}
