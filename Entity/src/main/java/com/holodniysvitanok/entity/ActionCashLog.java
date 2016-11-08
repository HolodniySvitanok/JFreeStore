package com.holodniysvitanok.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "action_cash_log_table")
@Entity
public class ActionCashLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_action_cash")
	private long id;

	@ManyToOne
	@JoinColumn(name = "id_from_cashbox")
	private Cashbox fromCashbox;

	@ManyToOne
	@JoinColumn(name = "id_from_order")
	private Order fromOrder;

	@ManyToOne
	@JoinColumn(name = "id_cashbox")
	private Cashbox cashbox;

	@Column(name = "sum_action_cash", nullable=false, precision=8, scale=2, columnDefinition="Decimal(8,2) default '0.00'" )
	private BigDecimal sum = new BigDecimal("0.00");

	@Column(name = "description_action_cash", length = ConfigEntity.DESCRIPTION_LENGTH)
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date timestamp = new Date();
	/*
	 * 
	 * 
	 * 
	 * 
	 * Constructor
	 */
	public ActionCashLog() {
	}

	/*
	 * 
	 * 
	 * 
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

	public Cashbox getFromCashbox() {
		return fromCashbox;
	}

	public void setFromCashbox(Cashbox fromCashbox) {
		this.fromCashbox = fromCashbox;
	}

	public Order getFromOrder() {
		return fromOrder;
	}

	public void setFromOrder(Order fromOrder) {
		this.fromOrder = fromOrder;
	}

	public Cashbox getCashbox() {
		return cashbox;
	}

	public void setCashbox(Cashbox cashbox) {
		this.cashbox = cashbox;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
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

	
}
