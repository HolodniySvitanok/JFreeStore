package com.holodniysvitanok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.dao.GroupDAO;
import com.holodniysvitanok.entity.Group;
import com.holodniysvitanok.service.CachedData;
import com.holodniysvitanok.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDAO groupDAO;
	
	private CachedData<Group> cachedData;

	private final long cachedTime = 120000;
	
	
	@Override
	public List<Group> getCachedAllGroups() {
		return cachedListData();
	}

	private List<Group> cachedListData() {
		if (cachedData == null || (System.currentTimeMillis() - cachedData.getTimeQuery()) >= cachedTime) {
			List<Group> group =groupDAO.getAllGroups();
			cachedData = new CachedData<Group>(group);
			return group;
		}
		return cachedData.getData();
	}
	
}
