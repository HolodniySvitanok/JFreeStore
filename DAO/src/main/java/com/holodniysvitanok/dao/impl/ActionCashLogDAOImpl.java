package com.holodniysvitanok.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.holodniysvitanok.dao.ActionCashLogDAO;
import com.holodniysvitanok.entity.ActionCashLog;
import com.holodniysvitanok.entity.Cashbox;
import com.holodniysvitanok.entity.User;

@SuppressWarnings({ "deprecation", "unchecked" })
@Repository
@Transactional
public class ActionCashLogDAOImpl implements ActionCashLogDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addLog(ActionCashLog actionCashLog) {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(actionCashLog);
	}

	@Override
	public List<ActionCashLog> getLastLog(User user, int maxCount) {

		return getLastLog(user.getCashbox(), maxCount);
	}

	@Override
	public List<ActionCashLog> getLastLog(Cashbox cashbox, int maxCount) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<ActionCashLog> query = currentSession.createQuery("from ActionCashLog where cashbox = :varCasbox  order by id desc");

		query.setParameter("varCasbox", cashbox);
		query.setMaxResults(maxCount);

		return query.list();

	}

}
