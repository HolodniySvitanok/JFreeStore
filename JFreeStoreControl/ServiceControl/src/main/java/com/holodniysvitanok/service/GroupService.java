package com.holodniysvitanok.service;

import java.util.List;

import com.holodniysvitanok.entity.Group;

public interface GroupService {

	List<Group> getGroupList();
	
	void saveOrUpdate(Group group);
	
	void delete(Group group);
}
