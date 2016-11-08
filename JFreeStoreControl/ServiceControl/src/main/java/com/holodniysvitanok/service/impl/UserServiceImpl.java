package com.holodniysvitanok.service.impl;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.dao.UserDAO;
import com.holodniysvitanok.entity.User;
import com.holodniysvitanok.service.GlobalConfigProject;
import com.holodniysvitanok.service.UserService;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * */
	@PostConstruct
	public void createRootInDB() {
		User user = userDAO.getUserById(GlobalConfigProject.ROOT_ID);
		if (user == null) {
			user = new User();
			user.setName(GlobalConfigProject.NAME_ROOT);
			user.setLogin(GlobalConfigProject.LOGIN_ROOT);
			user.setPassword(DigestUtils.md5Hex(GlobalConfigProject.PASSWORD_ROOT));
			user.setAccessLevel(GlobalConfigProject.ROOT_ACCESS_LEVEL);
			userDAO.saveUser(user);
		}
	}

	public boolean setUser(HttpServletRequest request, User user) {
		try {

			String name = request.getParameter("name");
			String login = request.getParameter("login");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String email = request.getParameter("email");
			String mobilePhone = request.getParameter("mobilePhone");
			
			int accessLevel = Integer.parseInt(request.getParameter("accessLevel"));

			if(authentication(request, ((User)request.getSession().getAttribute("user")).getAccessLevel())){
				user.setAccessLevel(accessLevel);
			}else{
				return false;
			}
			
			user.setName(name);
			user.setLogin(login);
			user.setEmail(email);
			user.setMobilePhone(mobilePhone);
			
			
			if (!password1.equals("") && !password2.equals("")) {
				if (eqPassword(password1, password2)) {
					user.setPassword(DigestUtils.md5Hex(password1));
				} else {
					return false;
				}
			}
			
			

		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public User authorization(String login, String password) {
		
		if(login == null || password == null){
			return null;
		}
		
		if (!valLogin(login) || !valPassword(password)) {
			return null;
		}

		User user = userDAO.getUserByLoginAndPassword(login, password);
		
		return user;
	}

	private boolean valLogin(String str) {
		if (str.trim().length() >= 4 && str.trim().length() <= 10) {
			return true;
		} else {
			return false;
		}
	}

	private boolean valPassword(String str) {
		if (str.trim().length() == 32) {
			return true;
		} else {
			return false;
		}
	}

	private boolean eqPassword(String password1, String password2) {
		return password1.equals(password2);
	}

	/**
	 * проверка уровня доступа в сессии
	 * */
	public boolean authentication(HttpServletRequest request, int minAccessLevel) {

		User user = (User) request.getSession(false).getAttribute("user");
		if (user.getAccessLevel() <= minAccessLevel) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * проверка уровня доступа в объекте
	 * */
	public boolean authentication(User user, int minAccessLevel) {
				
		if (user.getAccessLevel() <= minAccessLevel) {
			return true;
		} else {
			return false;
		}
	}

}
