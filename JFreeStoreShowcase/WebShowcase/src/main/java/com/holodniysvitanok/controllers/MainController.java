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
import com.holodniysvitanok.service.ManufacturerService;
import com.holodniysvitanok.service.ProductService;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class MainController {

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
	 * Root page
	 * */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView root(ModelAndView model) {
		model.setViewName("redirect: " + context.getContextPath() + "/" + WebModuleConfig.MAIN);
		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * Main page
	 * */
	@RequestMapping(value = WebModuleConfig.MAIN, method = RequestMethod.GET)
	public ModelAndView main(ModelAndView model, 
			@RequestParam(required = false, defaultValue = "0") String scat,
			@RequestParam(required = false, defaultValue = "5") String ecat,
			@RequestParam(required = false, defaultValue = "0") String sman,
			@RequestParam(required = false, defaultValue = "5") String eman) {

		List<Product> cachedCarouselProducts = productService.getCachedCarouselProducts();
		List<Product> cachedRandomProducts = productService.getCachedRandomProducts(6);
		
		PageItems<Category> cachedCategory = categoryService.getCachedCategoryPageItems(scat, ecat, 6);
		PageItems<Manufacturer> cachedManufacturers = manufacturerService.getCachedManufacturersPageItems(sman, eman, 6);

		model.addObject("cachedCarouselProducts",cachedCarouselProducts);
		model.addObject("cachedRandomProducts",cachedRandomProducts);
		model.addObject("cachedCategory",cachedCategory);
		model.addObject("cachedManufacturers",cachedManufacturers);
		
		model.addObject("scat", scat);
		model.addObject("ecat", ecat);
		model.addObject("sman", sman);
		model.addObject("eman", eman);
		
		model.setViewName(WebModuleConfig.VIEW_MAIN);
		
		return model;
	}



}
