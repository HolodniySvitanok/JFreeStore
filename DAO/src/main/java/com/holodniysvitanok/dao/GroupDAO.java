package com.holodniysvitanok.dao;

import java.util.List;

import com.holodniysvitanok.entity.Group;

public interface GroupDAO {

	void saveOrUpdate(Group group);
	
	List<Group> getAllGroups();
	
	void delete(Group group);
}
