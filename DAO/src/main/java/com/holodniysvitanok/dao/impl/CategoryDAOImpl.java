package com.holodniysvitanok.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.holodniysvitanok.dao.CategoryDAO;
import com.holodniysvitanok.entity.Category;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/
@SuppressWarnings({"unchecked","deprecation"})
@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Category> getAllCategory(int count) {
		Session session = sessionFactory.getCurrentSession();
		List<Category> list = session.createCriteria(Category.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setMaxResults(count).list();
		return list;
	}

	@Override
	public Category getCategory(long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Category category = (Category) currentSession.get(Category.class, id);
		return category;
	}

	@Override
	public void saveOrUpdateCategory(Category category) {
		Session session  = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
		
	}

	@Override
	public void deleteCategory(Category category) {
		Session session  = sessionFactory.getCurrentSession();
		session.remove(category);
		
	}

	@Override
	public long getCountDuplicateFiles(String fileName) {
		Session session  = sessionFactory.getCurrentSession();
		Query<Object> query = session.createQuery("select count(*) from Category where urlImage = :fileName ");
		query.setParameter("fileName", fileName);
		return ((Long)query.uniqueResult()).intValue();
	}

	@Override
	public Category getCategoryByUrl(String urlCategory) {
		Session session  = sessionFactory.getCurrentSession();
		Query<Category> createQuery = session.createQuery("from Category where url = :varUrl");
		createQuery.setParameter("varUrl", urlCategory);
		return createQuery.getSingleResult();
	}

	@Override
	public List<Category> getCategoryWithoutGroup() {
		Session session  = sessionFactory.getCurrentSession();
		Query<Category> query = session.createQuery("from Category where group = null");
		return query.getResultList();
	}


}
