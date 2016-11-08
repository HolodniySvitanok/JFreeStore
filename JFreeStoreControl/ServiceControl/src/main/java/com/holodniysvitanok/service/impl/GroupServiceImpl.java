package com.holodniysvitanok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.dao.GroupDAO;
import com.holodniysvitanok.entity.Group;
import com.holodniysvitanok.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDAO groupDAO;
	
	@Override
	public List<Group> getGroupList() {
		return groupDAO.getAllGroups();
	}

	@Override
	public void saveOrUpdate(Group group) {
		groupDAO.saveOrUpdate(group);
	}

	@Override
	public void delete(Group group) {
		groupDAO.delete(group);
	}

}
