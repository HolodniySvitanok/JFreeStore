package com.holodniysvitanok.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.holodniysvitanok.service.impl.HistoryUrl;

public class ServiceInterceptor extends HandlerInterceptorAdapter {
	

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		HistoryUrl historyUrl = (HistoryUrl)session.getAttribute("historyUrl");
		
		if(historyUrl == null){
			historyUrl = new HistoryUrl(50);
			session.setAttribute("historyUrl", historyUrl);
		}
		
		Boolean back =(Boolean)session.getAttribute("back");
		
		if(back == null || !back){
			historyUrl.addToHistory(request);
		}
		session.setAttribute("back", false);
		
		return super.preHandle(request, response, handler);
	}
}
