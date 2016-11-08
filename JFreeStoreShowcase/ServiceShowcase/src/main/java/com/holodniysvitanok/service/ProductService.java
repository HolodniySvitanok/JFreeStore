package com.holodniysvitanok.service;

import java.util.List;

import com.holodniysvitanok.entity.Product;
import com.holodniysvitanok.models.PageItems;

public interface ProductService {

	List<Product> getCachedCarouselProducts();

	List<Product> getCachedRandomProducts(int i);

	PageItems<Product> getProductsByCriteria(String manufacturer, String category, String start, String end, String sort, String order, int i);

	Product getProductById(long productId);

}
