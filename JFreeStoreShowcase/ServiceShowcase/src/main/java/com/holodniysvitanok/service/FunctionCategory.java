package com.holodniysvitanok.service;

import java.util.List;

import com.holodniysvitanok.entity.Category;

public interface FunctionCategory {

	default Category getCategory(List<Category> list, String str){
		
		if(str.equals("all")){
			Category category = new Category();
			category.setName("Все категории");
			return category;
		}
		
		long id = Long.parseLong(str);
		for(Category category : list){
			if(category.getId() == id){
				return category;
			}
		}
		return null;
	}
	
}
