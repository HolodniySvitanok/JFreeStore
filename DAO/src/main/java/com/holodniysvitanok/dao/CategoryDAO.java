package com.holodniysvitanok.dao;

import java.util.List;

import com.holodniysvitanok.entity.Category;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

public interface CategoryDAO extends FileDublicate{

	List<Category> getAllCategory(int count);

	Category getCategory(long id);

	void saveOrUpdateCategory(Category category);

	void deleteCategory(Category category);

	Category getCategoryByUrl(String urlCategory);
	
	List<Category> getCategoryWithoutGroup();
}
