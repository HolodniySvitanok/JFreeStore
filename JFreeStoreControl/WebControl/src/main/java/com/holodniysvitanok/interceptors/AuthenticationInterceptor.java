package com.holodniysvitanok.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.holodniysvitanok.entity.User;
import com.holodniysvitanok.service.GlobalConfigProject;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession(false);
		
		if(session == null || (User)session.getAttribute("user") == null ){
			response.sendRedirect(request.getServletContext().getContextPath()+"/"+GlobalConfigProject.AUTHORIZATION);
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}

	
	
}
