package com.holodniysvitanok.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.dao.OrderDAO;
import com.holodniysvitanok.dao.OrderDetailsDAO;
import com.holodniysvitanok.dao.ProductDAO;
import com.holodniysvitanok.dao.UserDAO;
import com.holodniysvitanok.entity.Order;
import com.holodniysvitanok.entity.OrderDetails;
import com.holodniysvitanok.entity.Product;
import com.holodniysvitanok.entity.User;
import com.holodniysvitanok.service.CashboxService;
import com.holodniysvitanok.service.GlobalConfigProject;
import com.holodniysvitanok.service.OrderService;
import com.holodniysvitanok.service.SessionBank;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SessionBank sessionBank;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private OrderDetailsDAO orderDetailsDAO;
	
	@Autowired
	private CashboxService cashboxService;
	
	@Override
	public void selectOrder(HttpSession session){ // для авторизации

		User user = (User)session.getAttribute("user");
		
		Order order = orderDAO.getOrderById(user.getIdSelectOrder());
		
		if(order == null || user.getIdSelectOrder() == -1){
			return;
		}
		
		setTitleOrder(session, order);
		
	}
	
	@Override
	public void completeOrderForUser(HttpServletRequest request){ // для удаления и завершения заказа

		deleteTitleOrder(request.getSession());
		
		String parameter = request.getParameter("id");
		long id = Long.parseLong(parameter);
		
		List<User> userList = userDAO.getUserWhoHaveSelectOrder(new Order(id)); // все юзеры которые выбрали этот заказ
		
		for(User us : userList){
			
			HttpSession userSession = sessionBank.getUserSession(us);
			
			if(userSession != null){
				deleteTitleOrder(userSession);
				userSession.setAttribute("user", us);
			}
			
			us.setIdSelectOrder(-1);
			userDAO.saveUser(us);
			
		}
	}
	
	
	private void deleteTitleOrder(HttpSession session){
		session.setAttribute("selectOrderId", null);
		session.setAttribute("nameSelectOrder", null);
	}
	
	private void setTitleOrder(HttpSession session, Order order){
		session.setAttribute("selectOrderId", order.getId());
		session.setAttribute("nameSelectOrder", order.getName());
	}
	
	@Override
	public ModelAndView setComplete(Order order,ModelAndView model, HttpServletRequest request){

		order.setActive(false);
		List<OrderDetails> orderDetails = order.getOrderDetails();

		for (OrderDetails od : orderDetails) {
			Product product = od.getProduct();
			
			if (product.getCount() < od.getCountProduct()) {
				model.addObject(
						"errorMessage", "Не хватает количесва товара id "+product.getId()+" для вашего заказа. Вы заказали "+
						od.getCountProduct()+"шт, а в наличие "+product.getCount()+"шт");
				
				model.setViewName(GlobalConfigProject.ERROR_PAGE);
				return model;
			}
		}
		
		for(OrderDetails od : orderDetails){
			od.getProduct().setCount(od.getProduct().getCount() - od.getCountProduct());
			productDAO.saveOrUpdateProduct(od.getProduct());
		}

		for (OrderDetails od : orderDetails) {
			od.setSumPrice(od.calcSumPrice());
			orderDetailsDAO.saveOrUpdate(od);
		}
		
		orderDAO.saveOrUpdate(order);
		completeOrderForUser(request);
		
		cashboxService.profit(order);
		
		return model;
	}
	
	// восстанавливает состояние полочек в магазине, все товары возвращаются на места
	@Override
	public void setNotComplete(Order order){
		
		List<OrderDetails> orderDetails = order.getOrderDetails();
		
		for(OrderDetails od :orderDetails){
			
			long id = od.getProduct().getId();
			Product product = productDAO.getProduct(id);
			product.setCount(product.getCount()+od.getCountProduct());
			
			productDAO.saveOrUpdateProduct(product);
		}
		
	}

	@Override
	public long getLastID() {
		
		return orderDAO.getLastID();
	}

	@Override
	public boolean saveOrUpdate(Order order) {
		return orderDAO.saveOrUpdate(order);
	}

	@Override
	public List<Order> getAllActiveOrders() {
		return orderDAO.getAllActiveOrders();
	}

	@Override
	public List<Order> getAllActiveOrdersUser(User user) {
		return orderDAO.getAllActiveOrdersUser(user);
	}

	@Override
	public List<Order> getAllNewOrders() {
		return orderDAO.getAllNewOrders();
	}

	@Override
	public List<Order> getCompleteOrders(int start, int maxRows) {
		return orderDAO.getCompleteOrders(start, maxRows);
	}

	@Override
	public Order getOrderById(long id) {
		return orderDAO.getOrderById(id);
	}

	@Override
	public void delete(Order order) {
		orderDAO.delete(order);
	}

	@Override
	public Order userSelectOrder(User user) {
		return orderDAO.userSelectOrder(user);
	}
}
