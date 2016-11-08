package com.holodniysvitanok.service;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Service;

import com.holodniysvitanok.entity.User;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Service
public class SessionBank implements HttpSessionListener {

	private static Set<UserSession> set = new HashSet<>();


	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		UserSession us = new UserSession(arg0.getSession());
		synchronized (set) {
			set.add(us);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {

		for (UserSession us : set) {
			if (us.getSession().equals(arg0.getSession())) {
				synchronized (set) {
					set.remove(us);
					return;
				}
			}
		}

	}


	public HttpSession getUserSession(User user) {

		for (UserSession us : set) {
			if (us.getUser().equals(user)) {
				return us.getSession();
			}
		}

		return null;
	}


	public void addUserToBank(HttpSession session, User user) {

		for (UserSession us : set) {
			if (us.getSession().equals(session)) {
				us.setUser(user);
				return;
			}
		}

	}

	//
	public  Set<User> users() {
		
		Set<User> users = new HashSet<>();
		
		for(UserSession us : set){
			users.add(us.getUser());
		}
		
		return users;
	}
}

class UserSession {

	private User user;
	private HttpSession session;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public UserSession() {
		user = new User();
		user.setAccessLevel(5);
		user.setName("Гость");
	}

	public UserSession(HttpSession session) {
		this();
		this.session = session;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSession other = (UserSession) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}