package com.holodniysvitanok.service;


import javax.servlet.http.HttpSession;

import com.holodniysvitanok.entity.User;

public interface AuthorizationService {

	boolean authorizationUser(User user, HttpSession session);
	
}
