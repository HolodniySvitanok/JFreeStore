package com.holodniysvitanok.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.conf.WebModuleConfig;
import com.holodniysvitanok.entity.Category;
import com.holodniysvitanok.entity.Manufacturer;
import com.holodniysvitanok.entity.Product;
import com.holodniysvitanok.models.PageItems;
import com.holodniysvitanok.service.CategoryService;
import com.holodniysvitanok.service.FunctionCategory;
import com.holodniysvitanok.service.FunctionManufacturer;
import com.holodniysvitanok.service.ManufacturerService;
import com.holodniysvitanok.service.ProductService;

@Controller
public class ProductController implements FunctionCategory, FunctionManufacturer{

	@Autowired
	private ServletContext context;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ManufacturerService manufacturerService;
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(value = WebModuleConfig.PRODUCTS, method = RequestMethod.GET)
	public ModelAndView showProducts(ModelAndView model,
										@RequestParam(defaultValue="all", required=false) String manufacturer,
										@RequestParam(defaultValue="all", required=false) String category,
										@RequestParam(defaultValue="0", required=false) String start,
										@RequestParam(defaultValue="11", required=false) String end,
										@RequestParam(defaultValue="default", required=false) String sort,
										@RequestParam(defaultValue="asc", required=false) String order) {

		PageItems<Product> products  = productService.getProductsByCriteria(manufacturer, category, start, end, sort, order, 12);
		
		List<Category> categoryList = categoryService.getCachedAllCategory();
		List<Manufacturer> manufacturerList = manufacturerService.getCachedAllManufacturers();
		
		Category selectCategory = getCategory(categoryList,  category);
		Manufacturer selectManufacturer = getManufacturer(manufacturerList, manufacturer);
		
		model.addObject("selectCategory", selectCategory);
		model.addObject("selectManufacturer", selectManufacturer);
		model.addObject("categoryList", categoryList);
		model.addObject("manufacturerList", manufacturerList);
		model.addObject("products", products);
		model.addObject("manufacturer", manufacturer);
		model.addObject("category", category);
		model.addObject("start", start);
		model.addObject("end", end);
		model.addObject("sort", sort);
		model.addObject("order", order);
		
		model.setViewName(WebModuleConfig.VIEW_PRODUCTS);
		
		return model;
	}

	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(value = WebModuleConfig.PRODUCT, method = RequestMethod.GET)
	public ModelAndView showProduct(ModelAndView model, @RequestParam(required = false) String id){
		
		long productId = Long.parseLong(id);
		Product product = productService.getProductById(productId);
		
		model.addObject("product", product);
		model.setViewName(WebModuleConfig.VIEW_PRODUCT);
		
		return model;
	}

}
