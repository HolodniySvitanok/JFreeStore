package com.holodniysvitanok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.dao.ActionCashLogDAO;
import com.holodniysvitanok.entity.ActionCashLog;
import com.holodniysvitanok.entity.Cashbox;
import com.holodniysvitanok.entity.User;
import com.holodniysvitanok.service.ActionCashLogService;

@Service
public class ActionCashLogServiceImpl implements ActionCashLogService {

	private final static int MAX_COUNT = 50;
	
	@Autowired
	private ActionCashLogDAO actionCashLogDAO;
	
	
	@Override
	public void addLog(ActionCashLog actionCashLog) {
		actionCashLogDAO.addLog(actionCashLog);

	}

	@Override
	public List<ActionCashLog> getLastLog(User user, int maxCount) {
		return actionCashLogDAO.getLastLog(user, maxCount);
	}

	@Override
	public List<ActionCashLog> getLastLog(User user) {
		return actionCashLogDAO.getLastLog(user, MAX_COUNT);
	}

	@Override
	public List<ActionCashLog> getLastLog(Cashbox cashbox, int maxCount) {
		return actionCashLogDAO.getLastLog(cashbox, maxCount);
	}

	@Override
	public List<ActionCashLog> getLastLog(Cashbox cashbox) {
		return actionCashLogDAO.getLastLog(cashbox, MAX_COUNT);
	}

}
