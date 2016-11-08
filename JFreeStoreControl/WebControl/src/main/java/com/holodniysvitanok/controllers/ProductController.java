package com.holodniysvitanok.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.dao.CategoryDAO;
import com.holodniysvitanok.dao.ManufacturerDAO;
import com.holodniysvitanok.dao.ProductDAO;
import com.holodniysvitanok.entity.Category;
import com.holodniysvitanok.entity.Manufacturer;
import com.holodniysvitanok.entity.Product;
import com.holodniysvitanok.service.ExchangerateService;
import com.holodniysvitanok.service.GlobalConfigProject;
import com.holodniysvitanok.service.ProductService;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Controller
@RequestMapping(value = GlobalConfigProject.PRODUCT)
public class ProductController {

	private final static Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ManufacturerDAO manufacturerDAO;

	@Autowired
	private ProductService productService;

	@Autowired
	private ExchangerateService exchangerateService;

	@Autowired
	private ServletContext context;

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Переадрисация при заходе на путь ПРОДУКТА на под страничку продукта
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView product() {
		return new ModelAndView("redirect: " + GlobalConfigProject.PRODUCT + "/" + GlobalConfigProject.SHOW_PRODUCT);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * ADD
	 */
	@RequestMapping(value = GlobalConfigProject.ADD_PRODUCT, method = RequestMethod.GET)
	public ModelAndView addProduct(ModelAndView model, WebRequest request) {

		String parameter = request.getParameter("category");

		model.setViewName(GlobalConfigProject.VIEW_ADD_PRODUCT);

		if (parameter == null) {
			List<Category> allCategory = categoryDAO.getAllCategory(9999);
			model.addObject("categoryList", allCategory);
			model.addObject("showCategory", true);
			return model;
		}

		long id = Long.parseLong(parameter);

		List<Manufacturer> allManufacturer = manufacturerDAO.getAllManufacturer(9999);
		Category category = categoryDAO.getCategory(id);

		double rateCoefficient = exchangerateService.rateCoefficient();
		model.addObject("rateCoefficient", rateCoefficient);
		model.addObject("manufacturerList", allManufacturer);
		model.addObject("category", category);

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Save
	 */
	@RequestMapping(value = GlobalConfigProject.ADD_PRODUCT, method = RequestMethod.POST)
	public ModelAndView saveProduct(ModelAndView model, WebRequest request, @RequestParam("fileImage1") MultipartFile file1, @RequestParam("fileImage2") MultipartFile file2,
			@RequestParam("fileImage3") MultipartFile file3) {

		Product product = new Product();

		try {
			productService.setProductObject(request, product);
			productService.saveImages(product, file1, file2, file3);
			productDAO.saveOrUpdateProduct(product);

		} catch (IOException e) {
			logger.error(e);
		}
		model.setViewName("redirect: " + GlobalConfigProject.ADD_PRODUCT);

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Show
	 */
	@RequestMapping(value = GlobalConfigProject.SHOW_PRODUCT, method = RequestMethod.GET)
	public ModelAndView showProduct(HttpServletRequest request, ModelAndView model) {

		String parameter = request.getParameter("category");

		if (parameter == null || parameter.equals("")) {
			List<Category> allCategory = categoryDAO.getAllCategory(99999);
			model.addObject("categoryList", allCategory);
			model.setViewName("selectCategory");
			return model;
		}

		long idCategory = Long.parseLong(parameter);

		Category category = categoryDAO.getCategory(idCategory);

		List<Product> allProductInCategory = productDAO.getAllProductInCategory(category);

		model.addObject("categoryName", category.getName());
		model.addObject("field1", category.getSupportFieldName1());
		model.addObject("field2", category.getSupportFieldName2());
		model.addObject("productList", allProductInCategory);

		model.setViewName(GlobalConfigProject.VIEW_SHOW_PRODUCT);

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Delete
	 */
	@RequestMapping(value = GlobalConfigProject.ACTION_DEL_PRODUCT, method = RequestMethod.GET)
	public ModelAndView actionDeleteProduct(HttpServletRequest servletRequest, WebRequest request, ModelAndView model) {

		String parameter = request.getParameter("id");
		model.setViewName("redirect: " + GlobalConfigProject.VIEW_SHOW_PRODUCT);

		if (parameter.length() == 0) {
			return model;
		}

		long id = Long.parseLong(parameter);

		Product product = productDAO.getProduct(id);

		productService.deleteImageProduct(productDAO, product);

		productDAO.deleteProduct(product);

		return model;
	}

	@RequestMapping(value = GlobalConfigProject.DEL_PRODUCT, method = RequestMethod.GET)
	public ModelAndView deleteProduct(HttpServletRequest request, ModelAndView model) {

		String parameter = request.getParameter("id");

		if (parameter == null || parameter.equals("")) {
			model.setViewName("redirect: " + request.getServletContext().getContextPath());
			return model;
		}

		Product product = productDAO.getProduct(Long.parseLong(parameter));

		model.addObject("product", product);
		model.setViewName(GlobalConfigProject.VIEW_DEL_PRODUCT);

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Set
	 */
	@RequestMapping(value = GlobalConfigProject.SET_PRODUCT, method = RequestMethod.GET)
	public ModelAndView setProduct(WebRequest request, ModelAndView model) {
		try {

			long id = Long.parseLong(request.getParameter("id"));

			Product product = productDAO.getProduct(id);
			List<Manufacturer> manufacturerList = manufacturerDAO.getAllManufacturer(9999);

			model.addObject("product", product);
			model.addObject("manufacturerList", manufacturerList);
			model.addObject("category", product.getCategory());

			model.setViewName(GlobalConfigProject.VIEW_SET_PRODUCT);

		} catch (Exception e) {
			model.setViewName("redirect: " + context.getContextPath() + "/" + GlobalConfigProject.ROOT_REDIRECT);
			return model;
		}
		return model;
	}

	@RequestMapping(value = GlobalConfigProject.SET_PRODUCT, method = RequestMethod.POST)
	public ModelAndView actionSetProduct(ModelAndView model, WebRequest request, @RequestParam("fileImage1") MultipartFile file1, @RequestParam("fileImage2") MultipartFile file2,
			@RequestParam("fileImage3") MultipartFile file3) {

		try {

			long id = Long.parseLong(request.getParameter("id"));
			model.setViewName(GlobalConfigProject.VIEW_SET_PRODUCT);

			Product product = productDAO.getProduct(id);
			List<Manufacturer> manufacturerList = manufacturerDAO.getAllManufacturer(9999);

			productService.setProductObject(request, product);
			productService.saveImages(product, file1, file2, file3);

			model.addObject("message", "Сохранено");
			model.addObject("product", product);
			model.addObject("manufacturerList", manufacturerList);

			productDAO.saveOrUpdateProduct(product);

		} catch (IOException e) {
			model.addObject("message", "Ошибка");
			logger.error(e);
		}

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Find
	 */
	@RequestMapping(value = GlobalConfigProject.FIND_PRODUCT, method = RequestMethod.GET)
	public ModelAndView findProduct(ModelAndView model) {

		model.setViewName(GlobalConfigProject.VIEW_FIND_PRODUCT);

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * Review
	 */
	@RequestMapping(value = GlobalConfigProject.REVIEW_PRODUCT, method = RequestMethod.GET)
	public ModelAndView reviewProduct(WebRequest request, ModelAndView model) {

		String val = request.getParameter("id");
		try {
			if (val != null && !val.equals("")) {
				model.addObject("product", productDAO.getProduct(Long.parseLong(val)));
			}

			model.setViewName(GlobalConfigProject.VIEW_REVIEW_PRODUCT);
		} catch (Exception e) {
			model.setViewName("errorPage");
			model.addObject("errorMessage", GlobalConfigProject.ERROR_PARAM);
			logger.error(e);
		}
		return model;
	}

	/*
	 * 
	 * Get actual Exchangerate
	 */
	@ResponseBody
	@RequestMapping(value = GlobalConfigProject.ACTUAL_EXCHANGE_RATE, method = RequestMethod.POST)
	public double actualExchangerate(ModelAndView model) {

		return exchangerateService.rateCoefficient();
	}

}
