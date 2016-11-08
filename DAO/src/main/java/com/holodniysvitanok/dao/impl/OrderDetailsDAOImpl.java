package com.holodniysvitanok.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.holodniysvitanok.dao.OrderDetailsDAO;
import com.holodniysvitanok.entity.OrderDetails;

@Repository
public class OrderDetailsDAOImpl implements OrderDetailsDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public boolean saveOrUpdate(OrderDetails orderDetails) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(orderDetails);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}


	@Override
	@Transactional
	public void delete(OrderDetails orderDetails) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(orderDetails);
	}


}
