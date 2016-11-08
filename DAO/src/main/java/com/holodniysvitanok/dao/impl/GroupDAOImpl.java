package com.holodniysvitanok.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.holodniysvitanok.dao.GroupDAO;
import com.holodniysvitanok.entity.Category;
import com.holodniysvitanok.entity.Group;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class GroupDAOImpl implements GroupDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveOrUpdate(Group group) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(group);
	}

	@Override
	public List<Group> getAllGroups() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Group> query = currentSession.createQuery("from Group");
		return query.getResultList();
	}

	@Override
	public void delete(Group group) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Group loadGroup = currentSession.load(Group.class, group.getId());
		currentSession.delete(loadGroup);
		for(Category category : loadGroup.getCategory()){
			category.setGroup(null);
			currentSession.saveOrUpdate(category);
		}
		
	}

}
