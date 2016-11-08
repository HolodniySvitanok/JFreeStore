package com.holodniysvitanok.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Table(name = "cashbox_table")
@Entity
public class Cashbox {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cashbox")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private User user;

	@Column(name = "cash_cashbox", nullable = false, precision = 8, scale = 2, columnDefinition="Decimal(8,2) default '00.00'")
	private BigDecimal cash = new BigDecimal("0.00");

	@Column(name = "main_cashbox")
	private boolean mainCashbox;

	@Column(name = "percent_cashbox", nullable = false)
	private int percent;

	/*
	 * 
	 * 
	 * 
	 * Constructors
	 */
	public Cashbox() {
	}

	public Cashbox(long id) {
		this.id = id;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public boolean isMainCashbox() {
		return mainCashbox;
	}

	public void setMainCashbox(boolean mainCashbox) {
		this.mainCashbox = mainCashbox;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

}
