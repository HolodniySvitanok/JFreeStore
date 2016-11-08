package com.holodniysvitanok.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.service.GlobalConfigProject;
import com.holodniysvitanok.service.impl.HistoryUrl;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Controller
public class MainController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("redirect: "+GlobalConfigProject.ROOT_REDIRECT);
	}
	
	
	@ResponseBody
	@RequestMapping(value = GlobalConfigProject.PREVIOUS_PAGE, method = RequestMethod.POST)
	public String previousPage(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		HistoryUrl historyUrl = (HistoryUrl)session.getAttribute("historyUrl");
		
		String urlOfHistory = historyUrl.getUrlOfHistory();
		
		session.setAttribute("back", true);		
		return urlOfHistory;
	}
}
