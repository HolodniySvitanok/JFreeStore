package com.holodniysvitanok.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.entity.User;
import com.holodniysvitanok.service.AuthorizationService;
import com.holodniysvitanok.service.GlobalConfigProject;
import com.holodniysvitanok.service.OrderService;
import com.holodniysvitanok.service.SessionBank;
import com.holodniysvitanok.service.UserService;


@Service
public class AuthorizationServiceImpl implements AuthorizationService{

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SessionBank sessionBank;
	

	
	@Override
	public boolean authorizationUser(User user, HttpSession session) {
		
		User resultuser = userService.authorization(user.getLogin(), user.getPassword());
		
		if(resultuser == null || !userService.authentication(resultuser, GlobalConfigProject.ACCESS_LEVEL_FOR_AUTHORIZATION)){
			return false;
		}
		
		preparationForEntry(user,session);
		
		return true;
	}



	
	private void preparationForEntry(User user, HttpSession session) {
		
		User resultuser = userService.authorization(user.getLogin(), user.getPassword());
		session.setAttribute("user", resultuser);
		orderService.selectOrder(session);
		sessionBank.addUserToBank(session, resultuser); // юзера в сессию
		
	}

}
