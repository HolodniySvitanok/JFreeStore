package com.holodniysvitanok.dao;

import java.util.List;

import com.holodniysvitanok.entity.Category;
import com.holodniysvitanok.entity.Product;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

public interface ProductDAO{

	List<Product> getAllProducts(int count);
	
	Product getProduct(long id);
	
	void saveOrUpdateProduct(Product product);

	void deleteProduct(Product product);
	
	long getCountDuplicateFiles(String fileName, int numberUrlImageField);
	
	List<Product> getAllProductInCategory(Category category);
	
	List<Product> getAllProductInCategory(Category category, int startRow, int maxRows);
	
	int getCountPageFromCategory(Category category);
	
	List<Product> getCarouselProducts();
	
	List<Product> getRandProducts(int maxResult);

	List<Product> getProductsByCriteria(String manufacturer, String category, String start, String end, String sort, String order);
	
	long getCountAllProducts(String manufacturer, String category);

    List<Product> getProductsByIdList(List<Long> idProductWithBasket);
}
