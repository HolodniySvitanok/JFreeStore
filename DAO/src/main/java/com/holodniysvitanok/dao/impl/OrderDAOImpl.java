package com.holodniysvitanok.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.holodniysvitanok.dao.OrderDAO;
import com.holodniysvitanok.entity.Order;
import com.holodniysvitanok.entity.User;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/
@SuppressWarnings({"deprecation", "unchecked"})
@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public long getLastID() {
		Session session = sessionFactory.getCurrentSession();               
		BigInteger result = (BigInteger) session.createSQLQuery("select MAX(order_table.id_order) from order_table").uniqueResult();
		if(result == null){
			return 0;
		}
		return result.longValue();
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(Order order) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(order);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	
	@Override
	@Transactional
	public List<Order> getAllActiveOrdersUser(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("from Order where user = :varUser and active = :varActive order by timestamp desc" );
		query.setBoolean("varActive", true);
		query.setParameter("varUser", user);
		return query.list();
	}

	@Override
	@Transactional
	public List<Order> getAllActiveOrders() {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("from Order where active = :varActive order by timestamp desc");
		query.setBoolean("varActive", true);
		return query.list();
	}

	@Override
	@Transactional
	public List<Order> getAllNewOrders() {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("from Order where newOrder = :varNewOrder and active = :varActive order by timestamp desc");
		query.setBoolean("varNewOrder", true);
		query.setBoolean("varActive", true);
		return query.list();
	}

	@Override
	@Transactional
	public List<Order> getCompleteOrders(int start, int maxRows) {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("from Order where active = :varActive order by timestamp desc");
		query.setBoolean("varActive", false);
		return query.list();
	}

	@Override
	@Transactional
	public Order getOrderById(long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Order order = (Order) currentSession.get(Order.class, id);
		return order;
	}

	@Override
	@Transactional
	public void delete(Order order) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(order);
	}

	@Override
	@Transactional
	public Order userSelectOrder(User user) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Order> query = currentSession.createQuery("from Order where id = :selectId");
		query.setParameter("selectId", user.getIdSelectOrder());
		return query.uniqueResult();
	}



}
