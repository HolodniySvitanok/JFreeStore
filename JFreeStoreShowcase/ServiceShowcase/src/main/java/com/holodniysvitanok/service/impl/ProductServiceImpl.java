package com.holodniysvitanok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.dao.ProductDAO;
import com.holodniysvitanok.entity.Product;
import com.holodniysvitanok.models.PageItems;
import com.holodniysvitanok.service.CachedData;
import com.holodniysvitanok.service.PageItemsBuilder;
import com.holodniysvitanok.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	private CachedData<Product> cachedDate;

	private CachedData<Product> cachedRandomDate;

	@Autowired
	private PageItemsBuilder<Product> pageItemsBuilder;
		
	private final long cachedTime = 120000;

	
	
	
	
	@Override
	public List<Product> getCachedCarouselProducts() {
		if (cachedDate == null || (System.currentTimeMillis() - cachedDate.getTimeQuery()) >= cachedTime) {
			List<Product> products = productDAO.getCarouselProducts();
			cachedDate = new CachedData<Product>(products);
			return products;
		}
		return cachedDate.getData();
	}



	@Override
	public List<Product> getCachedRandomProducts(int i) {
		if (cachedRandomDate == null || (System.currentTimeMillis() - cachedRandomDate.getTimeQuery()) >= cachedTime) {
			List<Product> products = productDAO.getRandProducts(i);
			cachedRandomDate = new CachedData<Product>(products);
			return products;
		}
		return cachedRandomDate.getData();
	}

	@Override
	public PageItems<Product> getProductsByCriteria(String manufacturer, String category, String start, String end, String sort, String order, int i) {

		PageItems<Product> product = null;		
		
		
		try{
			
			List<Product> productList =  productDAO.getProductsByCriteria( manufacturer,  category,  start,  end,  sort,  order);

			int startVal = Integer.parseInt(start);
			int endVal = Integer.parseInt(end);
			long cachedCountAllProducts = productDAO.getCountAllProducts(manufacturer, category);
			product = pageItemsBuilder.getPageItems((int)cachedCountAllProducts, productList, startVal, endVal, i);
			
		} catch (Exception ex) {
			//TODO FIX THIS
			ex.printStackTrace();
			
			long cachedCountAllProducts = productDAO.getCountAllProducts(manufacturer, category);
			product = pageItemsBuilder.getPageItems((int)cachedCountAllProducts, productDAO.getAllProducts(999999), 0, 11, i);
		}

		return product;
	}



	@Override
	public Product getProductById(long productId) {
		return productDAO.getProduct(productId);
	}

	
	
}
