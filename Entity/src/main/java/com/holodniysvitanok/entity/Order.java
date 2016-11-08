package com.holodniysvitanok.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Entity
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	private long id;

	@Column(name = "new_order")
	private boolean newOrder; // новый заказа

	@Column(name = "active_order")
	private boolean active; // флаг обработанного заказа

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user; // пользователь кто сделал заказ

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
	private List<OrderDetails> orderDetails = new ArrayList<>();

	@Column(name = "description_order", length = ConfigEntity.DESCRIPTION_LENGTH)
	private String description;

	@Column(name = "name_order",unique=true, length = ConfigEntity.NAME_LENGTH)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date timestamp = new Date();

	@Column(name = "expenses_order", nullable=false, precision=10, scale=2 )
	private BigDecimal expenses  = new BigDecimal("0.00");
	
	@Column(name = "comment_expenses_order", length = ConfigEntity.DESCRIPTION_LENGTH)
	private String commentExpenses;
	
	
	/*
	 * 
	 * Constructor
	 */
	public Order() {
	}

	public Order(long id) {
		this.id = id;
	}

	/*
	 * 
	 * 
	 * Getters and setters
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isNewOrder() {
		return newOrder;
	}

	public void setNewOrder(boolean newOrder) {
		this.newOrder = newOrder;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean complete) {
		this.active = complete;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getExpenses() {
		return expenses;
	}

	public void setExpenses(BigDecimal expenses) {
		this.expenses = expenses;
	}

	public String getCommentExpenses() {
		return commentExpenses;
	}

	public void setCommentExpenses(String commentExpenses) {
		this.commentExpenses = commentExpenses;
	}



	/*
	 * 
	 * 
	 * Method
	 */

	

	public BigDecimal getSumOrder(){ // сумма всего заказа
		BigDecimal sum = new BigDecimal("0.0");
		for(OrderDetails od : orderDetails){
			sum = sum.add(od.calcSumPrice());
		}
		return sum;
	}	
	
	public BigDecimal getDifferenceSumOrder(){ // зараб с всего заказа
		BigDecimal sum = new BigDecimal("0.0");
		for(OrderDetails od : orderDetails){
			sum = sum.add(od.differenceSumPrice());
		}
		return sum.subtract(expenses);
	}	
}
