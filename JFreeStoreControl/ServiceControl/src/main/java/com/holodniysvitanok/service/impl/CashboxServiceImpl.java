package com.holodniysvitanok.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.dao.ActionCashLogDAO;
import com.holodniysvitanok.dao.CashboxDAO;
import com.holodniysvitanok.entity.ActionCashLog;
import com.holodniysvitanok.entity.Cashbox;
import com.holodniysvitanok.entity.Order;
import com.holodniysvitanok.entity.User;
import com.holodniysvitanok.service.ActionCashLogService;
import com.holodniysvitanok.service.CashboxService;

@Service
public class CashboxServiceImpl implements CashboxService {

	@Autowired
	private CashboxDAO cashboxDAO;

	@Autowired
	private ActionCashLogDAO actionCashLogDAO;

	@Override
	@PostConstruct
	public void createMainCashbox() {

		if (cashboxDAO.getMainCashbox() != null) {
			return;
		}

		Cashbox mainCahbox = new Cashbox();
		mainCahbox.setMainCashbox(true);
		cashboxDAO.saveOrUpdate(mainCahbox);
	}

	@Override
	public void saveOrUpdate(Cashbox cashbox) {
		cashboxDAO.saveOrUpdate(cashbox);
	}

	@Override
	public void profit(Order order) {

		BigDecimal differenceSumOrder = order.getDifferenceSumOrder(); // заработок
		BigDecimal sumOrder = order.getSumOrder();
		BigDecimal costPrice = sumOrder.subtract(differenceSumOrder);

		Cashbox mainCashbox = cashboxDAO.getMainCashbox();
		
		BigDecimal mainCash = mainCashbox.getCash().add(costPrice);
		mainCashbox.setCash(mainCash);

		saveActionCashLogInOrder(mainCashbox, null, order, mainCash, ActionCashLogService.ADD_CASH_FROM_ORDER);
		cashboxDAO.saveOrUpdate(mainCashbox);

		List<Cashbox> allCashbox = cashboxDAO.getAllCashbox();

		for (Cashbox cashbox : allCashbox) {
			BigDecimal share = share(differenceSumOrder, cashbox.getPercent());
			cashbox.setCash(cashbox.getCash().add(share));
			cashboxDAO.saveOrUpdate(cashbox);

			saveActionCashLogInOrder(cashbox, null, order, share, ActionCashLogService.ADD_CASH_FROM_ORDER);

		}
	}

	private void saveActionCashLogInOrder(Cashbox cashbox, Cashbox fromCashbox, Order fromOrder, BigDecimal sum, String description) {
		
		ActionCashLog actionCashLog = new ActionCashLog();
		actionCashLog.setCashbox(cashbox);
		actionCashLog.setFromCashbox(fromCashbox);
		actionCashLog.setFromOrder(fromOrder);
		actionCashLog.setSum(sum);
		actionCashLog.setDescription(description);
		actionCashLogDAO.addLog(actionCashLog);
		
	}

	private BigDecimal share(BigDecimal amountOfMoney, int percent) {
		return amountOfMoney.multiply(new BigDecimal(percent)).divide(new BigDecimal(100));
	}

	@Override
	public Cashbox getMainCashbox() {
		return cashboxDAO.getMainCashbox();
	}

	@Override
	public Cashbox getUserCashbox(User user) {
		return cashboxDAO.getUserCashbox(user);
	}

	@Override
	public Cashbox getSumCashbox() {

		List<Cashbox> allCashbox = cashboxDAO.getAllCashbox();
		Cashbox sumCashbox = new Cashbox();

		for (Cashbox cashbox : allCashbox) {
			sumCashbox.setCash(sumCashbox.getCash().add(cashbox.getCash()));
		}

		return sumCashbox;
	}

	@Override
	public void returnCashbox(Order order) {

		BigDecimal differenceSumOrder = order.getDifferenceSumOrder(); // заработок
		BigDecimal sumOrder = order.getSumOrder();
		BigDecimal costPrice = sumOrder.subtract(differenceSumOrder); // себестоймость

		Cashbox mainCashbox = cashboxDAO.getMainCashbox();
		
		BigDecimal mainCash = mainCashbox.getCash().subtract(costPrice);
		mainCashbox.setCash(mainCash);
		
		cashboxDAO.saveOrUpdate(mainCashbox);

		List<Cashbox> allCashbox = cashboxDAO.getAllCashbox();

		for (Cashbox cashbox : allCashbox) {
			BigDecimal returnPrice = share(differenceSumOrder, cashbox.getPercent());
			cashbox.setCash(cashbox.getCash().subtract(returnPrice));
			cashboxDAO.saveOrUpdate(cashbox);
		}
	}

	@Override
	public Cashbox takeCashbox(String type, User user) {

		if (type.equalsIgnoreCase("main")) {
			return cashboxDAO.getMainCashbox();
		}

		if (type.equalsIgnoreCase("user")) {
			return cashboxDAO.getUserCashbox(user);
		}

		return null;
	}

	@Override
	public boolean takeCash(long idCashbox, BigDecimal sum) {
		
		Cashbox cashboxById = cashboxDAO.getCashboxById(idCashbox);
		boolean takeCash = cashboxDAO.takeCash(cashboxById, sum);
		
		if(takeCash){
//			saveActionCashLogInOrder(cashboxById, null, null, sum *(-1), ActionCashLogService.DEBIT);
		}
		
		return takeCash;
	}

	@Override
	public boolean takeCash(User user, BigDecimal sum) {
		
		boolean takeCash = cashboxDAO.takeCash(user, sum);
		
		if(takeCash){
//			saveActionCashLogInOrder(user.getCashbox(), null, null, sum *(-1), ActionCashLogService.DEBIT);
		}
		
		return takeCash;
	}
}
