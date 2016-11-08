package com.holodniysvitanok.service;


import java.util.List;

import com.holodniysvitanok.entity.Category;
import com.holodniysvitanok.models.PageItems;


public interface CategoryService {


	PageItems<Category> getCachedCategoryPageItems(String scat, String ecat, int countIntemInPage);

	List<Category> getCachedAllCategory();
	
	
}
