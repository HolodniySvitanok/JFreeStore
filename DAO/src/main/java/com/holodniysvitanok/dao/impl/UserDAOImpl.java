package com.holodniysvitanok.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.holodniysvitanok.dao.UserDAO;
import com.holodniysvitanok.entity.Order;
import com.holodniysvitanok.entity.User;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Repository
@SuppressWarnings({ "unchecked", "deprecation" })
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public User getUserByLoginAndPassword(String login, String password) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User where login = :userLogin and password = :userPassword");
		query.setParameter("userLogin", login);
		query.setParameter("userPassword", password);
		return query.uniqueResult();
	}

	@Override
	public boolean saveUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			currentSession.saveOrUpdate(user);
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	@Override
	public User getUserById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User where id = :id");
		query.setParameter("id", id);
	
		return query.uniqueResult();
	}

	
	
	@Override
	public List<User> getAllUsers(int start, int maxRows) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User");
		query.setFirstResult(start);
		query.setMaxResults(maxRows);
		return query.getResultList();
	}

	@Override
	public List<User> getAllUsers(int count) {
		Session session = sessionFactory.getCurrentSession();
		List<User> list = session.createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setMaxResults(count).list();
		
		return list;
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
	}

	
	@Override
	public List<User> getUserWhoHaveSelectOrder(Order order) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User where idSelectOrder = :id");
		query.setParameter("id", order.getId());
		
		return query.list();
	}


}
