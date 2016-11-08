package com.holodniysvitanok.controllers;


import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.entity.ActionCashLog;
import com.holodniysvitanok.entity.Cashbox;
import com.holodniysvitanok.entity.User;
import com.holodniysvitanok.service.ActionCashLogService;
import com.holodniysvitanok.service.CashboxService;
import com.holodniysvitanok.service.GlobalConfigProject;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Controller
@RequestMapping(value= GlobalConfigProject.CASHBOX)
public class CashboxController {

	
	
	@Autowired
	private CashboxService cashboxService;
	
	@Autowired
	private ActionCashLogService actionCashLogService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView cashbox(ModelAndView model){
		
		model.setViewName(GlobalConfigProject.STATUS_CASHBOX);
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
	@RequestMapping(value = GlobalConfigProject.STATUS_CASHBOX, method = RequestMethod.GET)
	public ModelAndView showMainCashbox(ModelAndView model){
		
		
		Cashbox mainCashbox = cashboxService.getMainCashbox();
		Cashbox sumCashbox = cashboxService.getSumCashbox();
		
		model.addObject("mainCashbox", mainCashbox);
		model.addObject("sumCashbox", sumCashbox);
		
		model.addObject("type", "main");
		
		model.setViewName(GlobalConfigProject.VIEW_STATUS_CASHBOX);
		
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
	@RequestMapping(value = GlobalConfigProject.USER_CASHBOX,method = RequestMethod.GET)
	public ModelAndView userCashbox(HttpServletRequest request, ModelAndView model ){
				
		User user = (User)request.getSession().getAttribute("user");
		
		Cashbox userCashbox = cashboxService.getUserCashbox(user);
		
		model.addObject("type", "user");
		model.addObject("userCashbox", userCashbox);
		model.setViewName(GlobalConfigProject.VIEW_USER_CASHBOX);
		
		return model;
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * Снятие денег 
	 * */
	@RequestMapping(value = GlobalConfigProject.TAKE_CASHBOX+"/{type}",method = RequestMethod.GET)
	public ModelAndView takeCash(HttpServletRequest request, @PathVariable String type, ModelAndView model){
		
		User user = (User)request.getSession().getAttribute("user");
		
		Cashbox cashbox = cashboxService.takeCashbox(type, user);
		
		model.addObject("cashbox", cashbox);
		model.addObject("type", type);
		
		model.setViewName(GlobalConfigProject.VIEW_TAKE_CASHBOX);
		
		return model;
	}
	
	@RequestMapping(value = GlobalConfigProject.TAKE_CASHBOX,method = RequestMethod.POST)
	public ModelAndView takeCashAction(HttpServletRequest request,
																ModelAndView model, 
																@RequestParam String summ,
																@RequestParam String id,
																@RequestParam String type
																){
		
		
//		double summVar = Double.parseDouble(summ);
		BigDecimal summVar = new BigDecimal(summ);
		long idVar = Long.parseLong(id);
		
		boolean takeCash = cashboxService.takeCash(idVar, summVar);
		
		if(!takeCash){
			model.addObject("errorMessage", GlobalConfigProject.ERROR_TAKE_CASH);
			model.setViewName(GlobalConfigProject.ERROR_PAGE);
			return model;
		}
		
		model.setViewName("redirect:"+"/"+GlobalConfigProject.CASHBOX+"/"+GlobalConfigProject.VIEW_TAKE_CASHBOX+"/"+type);
		return model;
	}
	
	/*
	 * 
	 * 
	 * 
	 * Show log 
	 * */
	@RequestMapping(value = GlobalConfigProject.LOG_CASHBOX, method = RequestMethod.GET)
	public ModelAndView getLog(ModelAndView model, 
							HttpServletRequest request, 
							@RequestParam(required=false) String select){
		
		if(select == null || select.equals("")){
			select = "you";
		}
		
		if(select.equals("you")){
			User user = (User)request.getSession().getAttribute("user");
			List<ActionCashLog> lastLog = actionCashLogService.getLastLog(user);
			model.addObject("logList", lastLog);
		}
		
		if(select.equals("main")){
			
			Cashbox mainCashbox = cashboxService.getMainCashbox();
			List<ActionCashLog> lastLog = actionCashLogService.getLastLog(mainCashbox);
			model.addObject("logList", lastLog);
		}
		model.addObject("message", select);
		model.setViewName(GlobalConfigProject.VIEW_LOG_CASHBOX);
		
		return model;
	}
}
