package com.holodniysvitanok.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Entity
@Table(name = "order_details_table")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order_details")
	private long id;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product product;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name = "id_order")
	private Order order;

	@Column(name = "count_product_order_details")
	private int countProduct; // количество товара

	@Column(name = "sum_price_order_details",  precision=8, scale=2, nullable=false, columnDefinition="Decimal(8,2) default '00.00'")
	private BigDecimal sumPrice = new BigDecimal("0.00");

	@Column(name = "k_price_order_details")
	private int kPrice;

	/*
	 * 
	 * Constructor
	 */
	public OrderDetails(long id) {
		this.id = id;
	}

	public OrderDetails() {
		super();
	}

	/*
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getCountProduct() {
		return countProduct;
	}

	public void setCountProduct(int countProduct) {
		this.countProduct = countProduct;
	}

	public BigDecimal getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}

	public int getkPrice() {
		return kPrice;
	}

	public void setkPrice(int kPrice) {
		this.kPrice = kPrice;
	}

	/*
	 * 
	 * 
	 * Method
	 */


	public BigDecimal difference() { // разница между закупкой и продажей c учетом
		BigDecimal result = product.getSellPrice().subtract(product.getPurchasePrice());	
		if (kPrice != 0) {
			return result.divide(new BigDecimal(100)).multiply(new BigDecimal(kPrice)).add(result);
		}
		return result;
	}

	public BigDecimal differenceUSD() { // разница между закупкой и продажей c учетом скидки в $
		BigDecimal result = product.getSellUsdPrice().subtract(product.getPurchaseUsdPrice());
		if (kPrice != 0) {
			return result.divide(new BigDecimal(100)).multiply(new BigDecimal(kPrice)).add(result);
		}
		return result;
	}

	public BigDecimal percentageDifference() { // разница в процентах
		
		if (kPrice != 0) {
			BigDecimal temp = product.getSellPrice().divide(new BigDecimal(100)).multiply(new BigDecimal(kPrice));
			temp = temp.add(product.getSellPrice());
			temp = temp.multiply(new BigDecimal(100)).divide(product.getPurchasePrice(), BigDecimal.ROUND_HALF_EVEN);
			return temp.subtract(new BigDecimal(100));
		}
		
		
		BigDecimal result = product.getSellUsdPrice().multiply(new BigDecimal(100)).divide(product.getPurchaseUsdPrice(), BigDecimal.ROUND_HALF_EVEN).subtract(new BigDecimal(100));
		return result;
				
	}
	public BigDecimal percentageDifferenceUSD() { // разница в процентах
		
		if (kPrice != 0) {
			BigDecimal temp = product.getSellUsdPrice().divide(new BigDecimal(100)).multiply(new BigDecimal(kPrice));
			temp = temp.add(product.getSellUsdPrice());
			temp = temp.multiply(new BigDecimal(100)).divide(product.getPurchaseUsdPrice(), BigDecimal.ROUND_HALF_EVEN);
			return temp.subtract(new BigDecimal(100));
		}
		
		
		BigDecimal result = product.getSellUsdPrice().multiply(new BigDecimal(100)).divide(product.getPurchaseUsdPrice(), BigDecimal.ROUND_HALF_EVEN).subtract(new BigDecimal(100));
		return result;
		
	}



	public BigDecimal calcSumUsdPrice() { // полная сумма товара в $
		if (kPrice != 0) {
			BigDecimal temp = product.getSellUsdPrice().divide(new BigDecimal(100)).multiply(new BigDecimal(kPrice));
			return product.getSellUsdPrice().add(temp).multiply(new BigDecimal(countProduct));
		}
		return product.getSellUsdPrice().multiply(new BigDecimal(countProduct));
	}

	public BigDecimal calcSumPrice() { // полная сумма товара по курсу
		if (kPrice != 0) {
			BigDecimal temp = product.getSellPrice().divide(new BigDecimal(100)).multiply(new BigDecimal(kPrice));
			return product.getSellUsdPrice().multiply(product.getSellExchange()).add(temp).multiply(new BigDecimal(countProduct));
		}
		return product.getSellPrice().multiply(new BigDecimal(countProduct));
		
	}

	public BigDecimal differenceSumPrice() { // заработок с общего кол
		return difference().multiply(new BigDecimal(countProduct));
	}
}
