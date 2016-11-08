package com.holodniysvitanok.service;

import javax.servlet.http.HttpServletRequest;

import com.holodniysvitanok.entity.User;

public interface UserService {

	void createRootInDB();

	boolean setUser(HttpServletRequest request, User user);

	User authorization(String login, String password);

	boolean authentication(HttpServletRequest request, int minAccessLevel);

	boolean authentication(User user, int minAccessLevel);
}
