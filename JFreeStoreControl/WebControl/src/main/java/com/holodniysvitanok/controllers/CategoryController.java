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

import com.holodniysvitanok.entity.Category;
import com.holodniysvitanok.entity.Group;
import com.holodniysvitanok.service.CategoryService;
import com.holodniysvitanok.service.GlobalConfigProject;
import com.holodniysvitanok.service.GroupService;
import com.holodniysvitanok.service.ProductService;
import com.holodniysvitanok.service.impl.ImageBox;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Controller
@RequestMapping(value = GlobalConfigProject.CATEGORY)
public class CategoryController {

	private final static Logger logger = Logger.getLogger(CategoryController.class);
	
	@Autowired
	private ImageBox box;

	@Autowired
	private ProductService cams;

	@Autowired
	private ServletContext context;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private GroupService groupService;
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView category() {
		return new ModelAndView("redirect: " + GlobalConfigProject.CATEGORY + "/" + GlobalConfigProject.SHOW_CATEGORY);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(value = GlobalConfigProject.ADD_CATEGORY, method = RequestMethod.GET)
	public ModelAndView addCategory(ModelAndView model) {
		
		List<Group> groupList = groupService.getGroupList();
		model.addObject("groupList", groupList);
		model.setViewName(GlobalConfigProject.VIEW_ADD_CATEGORY);
		
		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Save
	 */

	@RequestMapping(value = GlobalConfigProject.ADD_CATEGORY, method = RequestMethod.POST)
	public ModelAndView saveCategory(ModelAndView model, HttpServletRequest request, @RequestParam("fileImage") MultipartFile file) throws IOException {

		try {

			model.setViewName(GlobalConfigProject.VIEW_ADD_CATEGORY);

			Category category = categoryService.setCategory(new Category(), request);

			if (file.getBytes().length != 0) {
				category.setUrlImage(file.getOriginalFilename());
				box.saveImage(file.getBytes(), file.getOriginalFilename(), context.getInitParameter("imageBox"));
			}

			categoryService.saveOrUpdateCategory(category);

			model.addObject("message", "сохранено");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("message", "не удалось добавить категорию");
			
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
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(value = GlobalConfigProject.SHOW_CATEGORY, method = RequestMethod.GET)
	public ModelAndView showCategory(ModelAndView model) {

		List<Category> allCategory = categoryService.getAllCategory(9999);

		model.addObject("categoryList", allCategory);
		model.setViewName(GlobalConfigProject.VIEW_SHOW_CATEGORY);

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
	 * */
	@RequestMapping(value = GlobalConfigProject.ACTION_DEL_CATEGORY, method = RequestMethod.GET)
	public ModelAndView actionDeleteCategory(WebRequest request, ModelAndView model) {

		String parameter = request.getParameter("id");

		model.setViewName("redirect: " + GlobalConfigProject.SHOW_CATEGORY);

		if (parameter.equals("")) {
			return model;
		}

		long id = Long.parseLong(parameter);

		Category category = categoryService.getCategory(id);

		cams.deleteImageCategoryAndManufacturer(category, categoryService);

		categoryService.deleteCategory(category);

		return model;
	}

	@RequestMapping(value = GlobalConfigProject.DEL_CATEGORY, method = RequestMethod.GET)
	public ModelAndView deleteCategory(HttpServletRequest request, ModelAndView model) {

		String parameter = request.getParameter("id");
		if (parameter == null || parameter.equals("")) {
			model.setViewName("redirect: " + request.getServletContext().getContextPath());
			return model;
		}

		Category category = categoryService.getCategory(Long.parseLong(parameter));

		model.addObject("category", category);
		model.setViewName(GlobalConfigProject.VIEW_DEL_CATEGORY);

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
	 * 
	 * 
	 * */
	@RequestMapping(value = GlobalConfigProject.SET_CATEGORY, method = RequestMethod.GET)
	public ModelAndView setCategory(WebRequest request, ModelAndView model) {

		try{
			long id = Long.parseLong(request.getParameter("id"));
			Category category = categoryService.getCategory(id);
			List<Group> groupList = groupService.getGroupList();
			model.addObject("groupList", groupList);
			model.addObject("category", category);
			model.setViewName(GlobalConfigProject.VIEW_SET_CATEGORY);
		}catch(Exception ex){
			model.setViewName("redirect: "+ context.getContextPath()+"/"+GlobalConfigProject.CATEGORY);
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
	 * */
	@RequestMapping(value = GlobalConfigProject.SET_CATEGORY, method = RequestMethod.POST)
	public ModelAndView actionSetCategory(HttpServletRequest request, ModelAndView model, @RequestParam("fileImage") MultipartFile file) {

		String parameter = request.getParameter("id");

		model.setViewName(GlobalConfigProject.VIEW_SET_CATEGORY);

		if (parameter == null || parameter.equals("")) {
			model.addObject("message", "ошибка");
			return model;
		}
		try {
			long id = Long.parseLong(parameter);

			Category category = categoryService.getCategory(id);
			category = categoryService.setCategory(category, request);

			if (file.getBytes().length != 0) {
				box.deleteImage(category.getUrlImage(), context.getInitParameter("imageBox"));
				box.saveImage(file.getBytes(), file.getOriginalFilename(), context.getInitParameter("imageBox"));
				category.setUrlImage(file.getOriginalFilename());
			}

			categoryService.saveOrUpdateCategory(category);
			List<Group> groupList = groupService.getGroupList();
			model.addObject("groupList", groupList);
			model.addObject("message", "сохранено");
			model.addObject("category", category);

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
	 * */
	@RequestMapping(value = GlobalConfigProject.FIND_CATEGORY, method = RequestMethod.GET)
	public ModelAndView findCategory(ModelAndView model) {

		model.setViewName(GlobalConfigProject.VIEW_FIND_CATEGORY);

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Review
	 */
	@RequestMapping(value = GlobalConfigProject.REVIEW_CATEGORY, method = RequestMethod.GET)
	public ModelAndView reviewCategory(WebRequest request, ModelAndView model) {

		String val = request.getParameter("id");

		try {
			if (val != null && !val.equals("")) {
				model.addObject("category", categoryService.getCategory(Long.parseLong(val)));
			}
			model.setViewName(GlobalConfigProject.VIEW_REVIEW_CATEGORY);
		} catch (Exception e) {
			model.setViewName("errorPage");
			model.addObject("errorMessage", GlobalConfigProject.ERROR_PARAM);
			logger.error(e);
		}
		return model;
	}
}
