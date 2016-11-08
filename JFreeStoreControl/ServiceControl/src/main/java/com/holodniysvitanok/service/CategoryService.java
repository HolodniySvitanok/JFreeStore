package com.holodniysvitanok.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.holodniysvitanok.dao.FileDublicate;
import com.holodniysvitanok.entity.Category;

public interface CategoryService extends FileDublicate{

	Category setCategory(Category category, HttpServletRequest request);
	
	List<Category> getAllCategory(int count);

	Category getCategory(long id);

	void saveOrUpdateCategory(Category category);

	void deleteCategory(Category category);

	
}
