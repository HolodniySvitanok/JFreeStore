package com.holodniysvitanok.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.holodniysvitanok.dao.CashboxDAO;
import com.holodniysvitanok.entity.Cashbox;
import com.holodniysvitanok.entity.User;

@SuppressWarnings({"unchecked", "deprecation"})
@Repository
@Transactional
public class CashboxDAOImpl implements CashboxDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void saveOrUpdate(Cashbox cashbox) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(cashbox);
	}


	@Override
	public List<Cashbox> getAllCashbox() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Cashbox> query = currentSession.createQuery("from Cashbox where mainCashbox = :varMainCashbox");
		query.setParameter("varMainCashbox", false);
		
		return query.getResultList();
	}


	@Override
	public Cashbox getUserCashbox(User user) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Cashbox> query = currentSession.createQuery("from Cashbox where user = :varUser and mainCashbox = :varMainCashbox");
		query.setParameter("varUser", user);
		query.setParameter("varMainCashbox", false);
		
		return query.uniqueResult();
	}



	@Override
	public Cashbox getMainCashbox() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Cashbox> query = currentSession.createQuery("from Cashbox where mainCashbox = :varMainCashbox");
		query.setParameter("varMainCashbox", true);
		
		return query.uniqueResult();
	}


	@Override
	public boolean takeCash(User user, BigDecimal sum) {
		
		Cashbox userCashbox = getUserCashbox(user);
		
		if(userCashbox.getCash().compareTo(sum) == -1){
			return false;
		}
		
		userCashbox.setCash(userCashbox.getCash().subtract(sum));
		saveOrUpdate(userCashbox);
		
		return true;
	}


	@Override
	public boolean takeCash(Cashbox cashbox, BigDecimal sum) {
		
		if(cashbox.getCash().compareTo(sum) == -1){
			return false;
		}
		
		cashbox.setCash(cashbox.getCash().subtract(sum));
		saveOrUpdate(cashbox);
		
		return true;
	}


	@Override
	public Cashbox getCashboxById(long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Cashbox cashbox = currentSession.get(Cashbox.class, id);
		
		return cashbox;
	}

}
