package com.holodniysvitanok.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.entity.User;
import com.holodniysvitanok.service.AuthorizationService;
import com.holodniysvitanok.service.GlobalConfigProject;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Controller
@RequestMapping(value = GlobalConfigProject.AUTHORIZATION)
public class AuthorizationController {

	
	
	@Autowired
	private AuthorizationService authorizationService;

	/*
	 * 
	 * 
	 * 
	 * Страница с формой авторизации
	 * */
	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView authorization(ModelAndView model) {

		model.setViewName(GlobalConfigProject.VIEW_AUTHORIZATION);
		
		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 *Rest метод авторизации 
	 * */
	@RequestMapping( method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String authorizationUser(@RequestBody User user, HttpServletRequest request, ModelAndView model) {

		HttpSession session = request.getSession(true);

		if (authorizationService.authorizationUser(user, session)) {
			return "ok";
		}

		return GlobalConfigProject.ERROR_AUTHORIZATION_MESSAGE;
	}

}
