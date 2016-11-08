package com.holodniysvitanok.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Entity
@Table(name="user_table")
public class User {

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name_user", nullable=false)
	private String name;
	
	@Column(name = "login_user", unique=true, nullable=false)
	private String login;
	
	@Column(name = "password_user", nullable=false)
	private String password;
	
	@Column(name = "access_level_user", nullable=false)
	private int accessLevel;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Order> order = new ArrayList<>();
	
	@Column(name = "id_select_order_user")
	private long idSelectOrder;
	
	@Column(name = "mobile_phone_user")
	private String mobilePhone;
	
	@Column(name = "email_user")
	private String email;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Cashbox cashbox;
	
	
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * Getters and setters
	 * */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	public long getIdSelectOrder() {
		return idSelectOrder;
	}

	public void setIdSelectOrder(long idSelectOrder) {
		this.idSelectOrder = idSelectOrder;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Cashbox getCashbox() {
		return cashbox;
	}

	public void setCashbox(Cashbox cashbox) {
		this.cashbox = cashbox;
	}

	/*
	 * 
	 * 
	 * Constructor
	 * 
	 * */
	public User(String name) {
		this.name = name;
	}

	public User() {
	}
	
	
	public User(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password + ", accessLevel=" + accessLevel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
	
}
