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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.entity.Manufacturer;
import com.holodniysvitanok.service.GlobalConfigProject;
import com.holodniysvitanok.service.ManufacturerService;
import com.holodniysvitanok.service.ProductService;
import com.holodniysvitanok.service.impl.ImageBox;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Controller
@RequestMapping(value = GlobalConfigProject.MANUFACTURER)
public class ManufacturerController {

	private final static Logger logger = Logger.getLogger(ManufacturerController.class);

	@Autowired
	private ImageBox box;

	@Autowired
	private ProductService productService;

	@Autowired
	private ManufacturerService manufacturerService;

	@Autowired
	private ServletContext context;

	/*
	 * 
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView manufacturer() {
		return new ModelAndView("redirect: " + GlobalConfigProject.MANUFACTURER + "/" + GlobalConfigProject.SHOW_MANUFACTURER);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Add
	 */
	@RequestMapping(value = GlobalConfigProject.ADD_MANUFACTURER, method = RequestMethod.GET)
	public String addManufacturer() {
		return GlobalConfigProject.VIEW_ADD_MANUFACTURER;
	}

	@RequestMapping(value = GlobalConfigProject.ADD_MANUFACTURER, method = RequestMethod.POST)
	public ModelAndView saveManufacturer(ModelAndView model, HttpServletRequest request, @RequestParam("fileImage") MultipartFile file) {

		model.setViewName(GlobalConfigProject.VIEW_ADD_MANUFACTURER);

		Manufacturer manufacturer = new Manufacturer();
		manufacturerService.setManufacturer(manufacturer, request);

		try {
			if (file.getBytes().length != 0) {
				manufacturer.setUrlImage(file.getOriginalFilename());
				box.saveImage(file.getBytes(), file.getOriginalFilename(), context.getInitParameter("imageBox"));
			}

			manufacturerService.saveOrUpdateManufacturer(manufacturer);
			model.addObject("message", "сохранено");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("message", "не удалось добавить производителя");
			logger.error(e);
		}

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * Show
	 */
	@RequestMapping(value = GlobalConfigProject.SHOW_MANUFACTURER, method = RequestMethod.GET)
	public ModelAndView showManufacturer(ModelAndView model) {

		List<Manufacturer> manufacturer = manufacturerService.getAllManufacturer(9999);

		model.addObject("manufacturerList", manufacturer);
		model.setViewName(GlobalConfigProject.VIEW_SHOW_MANUFACTURER);

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Del
	 */
	@RequestMapping(value = GlobalConfigProject.ACTION_DEL_MANUFACTURER, method = RequestMethod.GET)
	public ModelAndView actionDeleteManufacturer(WebRequest request, ModelAndView model) {

		String parameter = request.getParameter("id");

		model.setViewName("redirect: " + GlobalConfigProject.VIEW_SHOW_MANUFACTURER);

		if (parameter.equals("")) {
			return model;

		}
		long id = Long.parseLong(parameter);

		Manufacturer manufacturer = manufacturerService.getManufacturer(id);

		productService.deleteImageCategoryAndManufacturer(manufacturer, manufacturerService);

		manufacturerService.deleteManufacturer(manufacturer);

		return model;
	}

	@RequestMapping(value = GlobalConfigProject.DEL_MANUFACTURER, method = RequestMethod.GET)
	public ModelAndView deleteManufacturer(HttpServletRequest request, ModelAndView model) {

		String parameter = request.getParameter("id");

		if (parameter == null || parameter.equals("")) {
			model.setViewName("redirect: " + request.getServletContext().getContextPath());
			return model;
		}

		Manufacturer manufacturer = manufacturerService.getManufacturer(Long.parseLong(parameter));

		model.addObject("manufacturer", manufacturer);

		model.setViewName(GlobalConfigProject.VIEW_DEL_MANUFACTURER);

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Set
	 */
	@RequestMapping(value = GlobalConfigProject.SET_MANUFACTURER, method = RequestMethod.GET)
	public ModelAndView setManufacturer(WebRequest request, ModelAndView model) {

		try {
			long id = Long.parseLong(request.getParameter("id"));
			Manufacturer manufacturer = manufacturerService.getManufacturer(id);
			model.addObject("manufacturer", manufacturer);
			model.setViewName(GlobalConfigProject.VIEW_SET_MANUFACTURER);

		} catch (Exception ex) {
			model.setViewName("redirect: " + context.getContextPath() + "/" + GlobalConfigProject.MANUFACTURER);
		}
		return model;
	}

	@RequestMapping(value = GlobalConfigProject.SET_MANUFACTURER, method = RequestMethod.POST)
	public ModelAndView actionSetManufacturer(HttpServletRequest request, ModelAndView model, @RequestParam("fileImage") MultipartFile file) {

		String parameter = request.getParameter("id");

		model.setViewName(GlobalConfigProject.VIEW_SET_MANUFACTURER);

		if (parameter == null || parameter.equals("")) {
			model.addObject("message", "ошибка");
			return model;
		}

		long id = Long.parseLong(parameter);

		Manufacturer manufacturer = manufacturerService.getManufacturer(id);
		manufacturerService.setManufacturer(manufacturer, request);

		try {

			if (file.getBytes().length != 0) {
				box.deleteImage(manufacturer.getUrlImage(), context.getInitParameter("imageBox"));
				box.saveImage(file.getBytes(), file.getOriginalFilename(), context.getInitParameter("imageBox"));
				manufacturer.setUrlImage(file.getOriginalFilename());
			}

			manufacturerService.saveOrUpdateManufacturer(manufacturer);

			model.addObject("message", "сохранено");
			model.addObject("manufacturer", manufacturer);

		} catch (IOException e) {
			model.addObject("message", "ошибка");
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
	 * 
	 * Find
	 */
	@RequestMapping(value = GlobalConfigProject.FIND_MANUFACTURER, method = RequestMethod.GET)
	public ModelAndView findManufacturer(ModelAndView model) {

		model.setViewName(GlobalConfigProject.VIEW_FIND_MANUFACTURER);

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * Review
	 */
	@RequestMapping(value = GlobalConfigProject.REVIEW_MANUFACTURER, method = RequestMethod.GET)
	public ModelAndView reviewManufacturer(WebRequest request, ModelAndView model) {

		String val = request.getParameter("id");

		try {
			if (val != null && !val.equals("")) {
				model.addObject("manufacturer", manufacturerService.getManufacturer(Long.parseLong(val)));
			}
			model.setViewName(GlobalConfigProject.VIEW_REVIEW_MANUFACTURER);
		} catch (Exception e) {
			model.setViewName("errorPage");
			model.addObject("errorMessage", GlobalConfigProject.ERROR_PARAM);
			logger.error(e);
		}

		return model;
	}

}
