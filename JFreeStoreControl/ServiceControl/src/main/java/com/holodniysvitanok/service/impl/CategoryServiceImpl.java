package com.holodniysvitanok.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.dao.CategoryDAO;
import com.holodniysvitanok.entity.Category;
import com.holodniysvitanok.entity.Group;
import com.holodniysvitanok.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public Category setCategory(Category category,HttpServletRequest request) {
		
		category.setName(request.getParameter("name"));
		category.setUrl(request.getParameter("url"));
		category.setDescription(request.getParameter("description"));
		category.setSupportFieldName1(request.getParameter("supportFieldName1"));
		category.setSupportFieldName2(request.getParameter("supportFieldName2"));
		category.setSupportFieldName3(request.getParameter("supportFieldName3"));
		category.setSupportFieldName4(request.getParameter("supportFieldName4"));
		category.setSupportFieldName5(request.getParameter("supportFieldName5"));
		category.setSupportFieldName6(request.getParameter("supportFieldName6"));
		category.setSupportFieldName7(request.getParameter("supportFieldName7"));
		category.setSupportFieldName8(request.getParameter("supportFieldName8"));
		category.setSupportFieldName9(request.getParameter("supportFieldName9"));
		category.setSupportFieldName10(request.getParameter("supportFieldName10"));
		
		long groupId = Long.parseLong(request.getParameter("group"));
		if(groupId != 0){
			category.setGroup(new Group(groupId));
		}
		
		
		
		return category;
	}

	@Override
	public long getCountDuplicateFiles(String fileName) {
		return categoryDAO.getCountDuplicateFiles(fileName);
	}

	@Override
	public List<Category> getAllCategory(int count) {
		return categoryDAO.getAllCategory(count);
	}

	@Override
	public Category getCategory(long id) {
		return categoryDAO.getCategory(id);
	}

	@Override
	public void saveOrUpdateCategory(Category category) {
		categoryDAO.saveOrUpdateCategory(category);
	}

	@Override
	public void deleteCategory(Category category) {
		categoryDAO.deleteCategory(category);
	}

}
