package com.holodniysvitanok.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.dao.OrderDetailsDAO;
import com.holodniysvitanok.dao.ProductDAO;
import com.holodniysvitanok.dao.UserDAO;
import com.holodniysvitanok.entity.Order;
import com.holodniysvitanok.entity.OrderDetails;
import com.holodniysvitanok.entity.User;
import com.holodniysvitanok.service.CashboxService;
import com.holodniysvitanok.service.GlobalConfigProject;
import com.holodniysvitanok.service.OrderService;
import com.holodniysvitanok.service.ProductService;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Controller
@RequestMapping(value = GlobalConfigProject.ORDER)
public class OrderController {

	private final static Logger logger = Logger.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CashboxService cashboxService;


	@Autowired
	private OrderDetailsDAO orderDetailsDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView order(ModelAndView model, HttpServletRequest request) {

		model.setViewName("redirect: " + request.getContextPath() + "/" + GlobalConfigProject.ORDER + "/" + GlobalConfigProject.ACTIVE_ORDER);

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * Add
	 */
	@RequestMapping(value = GlobalConfigProject.ADD_ORDER, method = RequestMethod.GET)
	public ModelAndView addOrder(ModelAndView model, HttpServletRequest request) {

		long id = orderService.getLastID();

		model.addObject("nameOrder", "Заказ #" + (id + 1));
		model.setViewName(GlobalConfigProject.VIEW_ADD_ORDER);

		return model;
	}

	@RequestMapping(value = GlobalConfigProject.ADD_ORDER, method = RequestMethod.POST)
	public ModelAndView saveOrder(ModelAndView model, HttpServletRequest request) {

		model.setViewName("redirect: " + request.getContextPath() + "/" + GlobalConfigProject.ORDER + "/" + GlobalConfigProject.ACTIVE_ORDER);

		try {
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			User user = (User) request.getSession().getAttribute("user");

			Order order = new Order();
			order.setName(name);
			order.setDescription(description);
			order.setActive(true);
			order.setUser(user);
			
			orderService.saveOrUpdate(order);

		} catch (Exception e) {
			model.addObject("message", "Ошибка");
			logger.error(e);
			e.printStackTrace();
		}
		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * Active
	 */
	@RequestMapping(value = GlobalConfigProject.ACTIVE_ORDER, method = RequestMethod.GET)
	public ModelAndView activeOrder(ModelAndView model, HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");

		String parameter = request.getParameter("active");
		List<Order> orderList = null;

		if (parameter == null) {
			parameter = "you";
		}

		if (parameter.equals("") || parameter.equals("all")) {
			orderList = orderService.getAllActiveOrders();
			model.addObject("message", "all");
		}
		if (parameter.equals("you")) {
			orderList = orderService.getAllActiveOrdersUser(user);
			model.addObject("message", "you");
		}
		if (parameter.equals("new")) {
			orderList = orderService.getAllNewOrders();
			model.addObject("message", "new");
		}

		model.addObject("orderList", orderList);
		model.setViewName(GlobalConfigProject.VIEW_ACTIVE_ORDER);

		return model;
	}

	/*
	 * 
	 * 
	 * Show comlete orders
	 */
	@RequestMapping(value = GlobalConfigProject.COMPLETE_ORDER, method = RequestMethod.GET)
	public ModelAndView completeOrder(ModelAndView model, HttpServletRequest request) {

		List<Order> allOrders = orderService.getCompleteOrders(0, 0);

		model.addObject("orderList", allOrders);
		model.setViewName(GlobalConfigProject.VIEW_COMPLETE_ORDER);

		return model;

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Review
	 */
	@RequestMapping(value = GlobalConfigProject.REVIEW_ORDER, method = RequestMethod.GET)
	public ModelAndView reviewOrder(ModelAndView model, HttpServletRequest request) {

		String parameter = request.getParameter("id");

		try{
		
		long id = Long.parseLong(parameter);

		Order order = orderService.getOrderById(id);
		
		if(order != null){
			List<OrderDetails> orderDetails = order.getOrderDetails();
			model.addObject("order", order);
			model.addObject("orderDetailsList", orderDetails);
			
			
			if(order.isActive()){
				model.setViewName(GlobalConfigProject.VIEW_REVIEW_ORDER);
			}else{
				model.setViewName(GlobalConfigProject.VIEW_REVIEW_COMPLETE_ORDER);
			}
			
			
		}else{
			model.setViewName(GlobalConfigProject.ERROR_PAGE);
			model.addObject("errorMessage","Нет такого заказа");
		}
		
		}catch(Exception e){
			model.setViewName("errorPage");
			model.addObject("errorMessage", GlobalConfigProject.ERROR_PARAM);
			logger.error(e);
		}

		return model;

	}

	@RequestMapping(value = GlobalConfigProject.REVIEW_COMPLETE_ORDER, method = RequestMethod.GET)
	public ModelAndView reviewComliteOrder(ModelAndView model, HttpServletRequest request) {

		String parameter = request.getParameter("id");

		long id = Long.parseLong(parameter);

		Order order = orderService.getOrderById(id);
		List<OrderDetails> orderDetails = order.getOrderDetails();

		model.addObject("order", order);
		model.addObject("orderDetailsList", orderDetails);
		model.setViewName(GlobalConfigProject.VIEW_REVIEW_COMPLETE_ORDER);

		return model;

	}

	/*
	 * 
	 * 
	 * Find
	 */
	@RequestMapping(value = GlobalConfigProject.FIND_ORDER, method = RequestMethod.GET)
	public ModelAndView findOrder(ModelAndView model) {
		model.setViewName(GlobalConfigProject.VIEW_FIND_ORDER);
		return model;

	}

	/*
	 * 
	 * 
	 * 
	 * Expenses
	 */
	@RequestMapping(value = GlobalConfigProject.EXPENSES_ORDER, method = RequestMethod.POST)
	public ModelAndView expensesOrder(ModelAndView model, HttpServletRequest request) {

		model.setViewName("redirect: " + request.getHeader("referer"));

		String expensesStr = request.getParameter("expenses");
		String commentExpensesStr = request.getParameter("commentExpenses");
		String parameter = request.getParameter("id");

		if (parameter == null || parameter.equals("")) {
			return model;
		}

		Order order = orderService.getOrderById(Long.parseLong(parameter));
		order.setCommentExpenses(commentExpensesStr);
		
		try {
//			float parseFloat = Float.parseFloat(expensesStr);
			BigDecimal temp = new BigDecimal(expensesStr);
			order.setExpenses(temp);
			
		} catch (Exception e) {
			logger.error(e);
		}
		orderService.saveOrUpdate(order);
		

		return model;

	}

	/*
	 * 
	 * 
	 * Set select
	 */
	@RequestMapping(value = GlobalConfigProject.SET_SELECT_ORDER, method = RequestMethod.GET)
	public ModelAndView setSelectOrder(ModelAndView model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String parameter = request.getParameter("id");
		long id = Long.parseLong(parameter);

		user.setIdSelectOrder(id);
		userDAO.saveUser(user);

		Order order = orderService.getOrderById(id);

		session.setAttribute("selectOrderId", id);
		session.setAttribute("nameSelectOrder", order.getName());

		model.setViewName("redirect: " + request.getHeader("referer"));
		return model;

	}

	/*
	 * 
	 * 
	 * 
	 * Set
	 */
	@RequestMapping(value = GlobalConfigProject.SET_ORDER, method = RequestMethod.GET)
	public ModelAndView setOrder(ModelAndView model, HttpServletRequest request) {

		String parameter = request.getParameter("id");
		long id = Long.parseLong(parameter);

		Order order = orderService.getOrderById(id);

		model.addObject("order", order);

		model.setViewName(GlobalConfigProject.VIEW_SET_ORDER);

		return model;

	}

	@RequestMapping(value = GlobalConfigProject.SET_ORDER, method = RequestMethod.POST)
	public ModelAndView actionSetOrder(ModelAndView model, HttpServletRequest request) {

		try {
			String strId = request.getParameter("id");
			String strName = request.getParameter("name");
			String strDescription = request.getParameter("description");
			String strNewOrd = request.getParameter("newOrder");

			long id = Long.parseLong(strId);

			Order order = orderService.getOrderById(id);

			order.setName(strName);
			order.setDescription(strDescription);
			order.setNewOrder(productService.checkboxString(strNewOrd));

			orderService.saveOrUpdate(order);

			model.addObject("order", order);
			model.addObject("message", "Сохранено");

		} catch (Exception e) {
			model.addObject("message", "Ошибка");
			logger.error(e);
		}

		model.setViewName(GlobalConfigProject.VIEW_SET_ORDER);

		return model;

	}

	/*
	 * 
	 * 
	 * 
	 * Complete order
	 */
	@RequestMapping(value = GlobalConfigProject.CLOSE_ORDER, method = RequestMethod.GET)
	public ModelAndView closeOrder(ModelAndView model, HttpServletRequest request) {
		model.setViewName("redirect: " + request.getContextPath() + "/" + GlobalConfigProject.ORDER + "/" + GlobalConfigProject.ACTIVE_ORDER);
		try {
			String parameter = request.getParameter("id");
			long id = Long.parseLong(parameter);

			Order order = orderService.getOrderById(id);

			model = orderService.setComplete(order, model, request);
			
		
		} catch (Exception e) {
			logger.error(e);
		}

		

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * Return active order
	 */
	@RequestMapping(value = GlobalConfigProject.RETURN_ACTIVE_ORDER, method = RequestMethod.GET)
	public ModelAndView returnActiveOrder(ModelAndView model, HttpServletRequest request) {

		String strId = request.getParameter("id");
		long id = Long.parseLong(strId);
		Order order = orderService.getOrderById(id);

		order.setActive(true);
		
		orderService.setNotComplete(order);
		orderService.saveOrUpdate(order);
		cashboxService.returnCashbox(order);
		
		model.setViewName("redirect: " + request.getContextPath() + "/" + GlobalConfigProject.ORDER + "/" + GlobalConfigProject.ACTIVE_ORDER);
		
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
	 * 
	 * 
	 * Del
	 */
	@RequestMapping(value = GlobalConfigProject.ACTION_DEL_ORDER, method = RequestMethod.GET)
	public ModelAndView actionDelOrder(ModelAndView model, HttpServletRequest request) {

		try {

			String parameter = request.getParameter("id");
			long id = Long.parseLong(parameter);

			orderService.delete(new Order(id));


			orderService.completeOrderForUser(request);
		} catch (Exception e) {
			logger.equals(e);
		}

		model.setViewName("redirect: " + request.getContextPath() + "/" + GlobalConfigProject.ORDER);

		return model;
	}

	@RequestMapping(value = GlobalConfigProject.DEL_ORDER, method = RequestMethod.GET)
	public ModelAndView delOrder(ModelAndView model, HttpServletRequest request) {

		model.setViewName(GlobalConfigProject.VIEW_DEL_ORDER);

		try {
			String idStr = request.getParameter("id");

			if (idStr == null || idStr.equals("")) {
				return model;
			}

			Order order = orderService.getOrderById(Long.parseLong(idStr));

			model.addObject("order", order);

		} catch (Exception e) {
			logger.equals(e);
		}

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
	 * 
	 * 
	 * 
	 * Add product in order
	 */
	@RequestMapping(value = GlobalConfigProject.ADD_PRODUCT_IN_ORDER, method = RequestMethod.POST)
	public ModelAndView addProductInOrder(ModelAndView model, HttpServletRequest request) {

		model.setViewName("redirect: " + request.getHeader("referer"));

		try {

			String productIdStr = request.getParameter("id");
			String countStr = request.getParameter("count");

			User user = (User) request.getSession().getAttribute("user");
			Order userSelectOrder = orderService.userSelectOrder(user);

			if (userSelectOrder == null) {
				model.setViewName("errorPage");
				model.addObject("errorMessage", GlobalConfigProject.NOT_SELECTED_ORDER);
				return model;
			}

			long id = Long.parseLong(productIdStr);
			int count = Integer.parseInt(countStr);

			for (OrderDetails od : userSelectOrder.getOrderDetails()) {
				if (od.getProduct().getId() == id) {
					od.setCountProduct(od.getCountProduct() + count);
					orderDetailsDAO.saveOrUpdate(od);
					return model;
				}
			}

			OrderDetails od = new OrderDetails();
			od.setCountProduct(count);
			od.setOrder(userSelectOrder);
			od.setProduct(productDAO.getProduct(id));

			orderDetailsDAO.saveOrUpdate(od);

		} catch (Exception e) {
			logger.equals(e);
		}

		return model;
	}

	/*
	 * 
	 * 
	 * 
	 * Update in order - orderDetails
	 */
	@RequestMapping(value = GlobalConfigProject.UPDATE_ORDER_DETAILS, method = RequestMethod.POST)
	public ModelAndView updateOrderDetails(ModelAndView model, HttpServletRequest request) {

		String[] delId = request.getParameterValues("del");
		String idStr = request.getParameter("id");

		if (idStr == null || idStr.equals("")) {
			model.setViewName("redirect: " + request.getContextPath());
			return model;
		}

		long id = Long.parseLong(idStr);

		Order order = orderService.getOrderById(id);

		String[] countStr = request.getParameterValues("count");
		String[] kPrice = request.getParameterValues("kPrice");

		List<OrderDetails> odList = order.getOrderDetails();

		for (int i = 0; i < odList.size(); i++) {

			OrderDetails orderDetails = odList.get(i);
			orderDetails.setCountProduct(Integer.parseInt(countStr[i]));
			orderDetails.setkPrice(Integer.parseInt(kPrice[i]));
			orderDetails.setSumPrice(orderDetails.calcSumPrice()); // запаменает текущее значение цены
			orderDetailsDAO.saveOrUpdate(orderDetails);

		}

		orderService.saveOrUpdate(order);

		if (delId != null && delId.length != 0) {
			for (String idOD : delId) {
				orderDetailsDAO.delete(new OrderDetails(Long.parseLong(idOD)));
			}
		}

		model.setViewName("redirect: " + request.getHeader("referer"));

		return model;
	}

}
