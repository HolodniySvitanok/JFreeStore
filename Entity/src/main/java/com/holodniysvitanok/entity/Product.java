package com.holodniysvitanok.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Entity
@Table(name="product_table")
public class Product {

	@Id
    @Column(name = "id_product")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "purchase_usd_product", precision=8, scale=2, nullable=false, columnDefinition="Decimal(8,2) default '00.00'") // цена покупки в $ 
	private BigDecimal purchaseUsdPrice = new BigDecimal("0.00");
	
	@Column(name = "purchase_exchange_product", precision=8, scale=2, nullable=false, columnDefinition="Decimal(8,2) default '00.00'") // курс к текущей валюте на помент закупки
	private BigDecimal purchaseExchange = new BigDecimal("0.00");
	
	@Column(name = "sell_usd_product", precision=8, scale=2, nullable=false, columnDefinition="Decimal(8,2) default '00.00'") // цена продажи в $
	private BigDecimal sellUsdPrice = new BigDecimal("0.00");
	
	@Column(name = "sell_exchange_product", precision=8, scale=2, nullable=false, columnDefinition="Decimal(8,2) default '00.00'") // курс к текущей валюте на момент продажи
	private BigDecimal sellExchange = new BigDecimal("0.00");

	@Column(name = "purchase_price_product", precision=8, scale=2, nullable=false, columnDefinition="Decimal(8,2) default '00.00'") // цена закупки в текущей валюте
	private BigDecimal purchasePrice = new BigDecimal("0.00");
	
	@Column(name = "sell_price_product", precision=8, scale=2, nullable=false, columnDefinition="Decimal(8,2) default '00.00'") // цена продажи в текущей валюте
	private BigDecimal sellPrice = new BigDecimal("0.00");
	
	@Column(name = "count_product")
	private int count;
	
	@Column(name = "rate_product")
	private int rate;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<OrderDetails> orderDetails = new ArrayList<>();
	
	@Column(name = "stock_product")
	private boolean stock;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name = "id_manufacturer")
	private Manufacturer manufacturer;
	
	@Column(name = "main_description_product", length = ConfigEntity.DESCRIPTION_LENGTH)
	private String mainDescription;
	
	@Column(name = "short_description_product", length = ConfigEntity.DEFAULT_FIELD_LEHGTH)
	private String shortDescription;
		
	@Column(name = "show_product")
	private boolean show;
	
	@Column(name = "novelty_product")
	private boolean novelty;
	
	@Column(name = "carousel_product")
	private boolean carousel;
	
	@Column(name = "url_image1_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String urlImage1;
	
	@Column(name = "url_image2_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String urlImage2;
	
	@Column(name = "url_image3_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String urlImage3;

	@Column(name = "support_field1_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH) 
	private String supportField1;
	
	@Column(name = "support_field2_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String supportField2;
	
	@Column(name = "support_field3_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String supportField3;
	
	@Column(name = "support_field4_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String supportField4;
	
	@Column(name = "support_field5_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String supportField5;
	
	@Column(name = "support_field6_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String supportField6;
	
	@Column(name = "support_field7_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String supportField7;
	
	@Column(name = "support_field8_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String supportField8;
	
	@Column(name = "support_field9_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String supportField9;
	
	@Column(name = "support_field10_product", length = ConfigEntity.SUPPORT_FIELD_LENGTH)
	private String supportField10;

	


	/*
	 * 
	 * Constructor
	 * 
	 * */
	public Product(long id) {
		this.id = id;
	}

	public Product() {
		super();
	}

	/*
	 * 
	 * Getter and Setter 
	 * 
	 * */
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getMainDescription() {
		return mainDescription;
	}

	public void setMainDescription(String mainDescription) {
		this.mainDescription = mainDescription;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getUrlImage1() {
		return urlImage1;
	}

	public void setUrlImage1(String urlImage1) {
		this.urlImage1 = urlImage1;
	}

	public String getUrlImage2() {
		return urlImage2;
	}

	public void setUrlImage2(String urlImage2) {
		this.urlImage2 = urlImage2;
	}

	public String getUrlImage3() {
		return urlImage3;
	}

	public void setUrlImage3(String urlImage3) {
		this.urlImage3 = urlImage3;
	}

	public String getSupportField1() {
		return supportField1;
	}

	public void setSupportField1(String supportField1) {
		this.supportField1 = supportField1;
	}

	public String getSupportField2() {
		return supportField2;
	}

	public void setSupportField2(String supportField2) {
		this.supportField2 = supportField2;
	}

	public String getSupportField3() {
		return supportField3;
	}

	public void setSupportField3(String supportField3) {
		this.supportField3 = supportField3;
	}

	public String getSupportField4() {
		return supportField4;
	}

	public void setSupportField4(String supportField4) {
		this.supportField4 = supportField4;
	}

	public String getSupportField5() {
		return supportField5;
	}

	public void setSupportField5(String supportField5) {
		this.supportField5 = supportField5;
	}

	public String getSupportField6() {
		return supportField6;
	}

	public void setSupportField6(String supportField6) {
		this.supportField6 = supportField6;
	}

	public String getSupportField7() {
		return supportField7;
	}

	public void setSupportField7(String supportField7) {
		this.supportField7 = supportField7;
	}

	public String getSupportField9() {
		return supportField9;
	}

	public void setSupportField9(String supportField9) {
		this.supportField9 = supportField9;
	}

	public String getSupportField10() {
		return supportField10;
	}

	public void setSupportField10(String supportField10) {
		this.supportField10 = supportField10;
	}

	public String getSupportField8() {
		return supportField8;
	}

	public void setSupportField8(String supportField8) {
		this.supportField8 = supportField8;
	}
	
	
	public boolean isStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public  BigDecimal getPurchaseUsdPrice() {
		return purchaseUsdPrice;
	}

	public  void setPurchaseUsdPrice(BigDecimal purchaseUsdPrice) {
		this.purchaseUsdPrice = purchaseUsdPrice;
	}

	public  BigDecimal getPurchaseExchange() {
		return purchaseExchange;
	}

	public  void setPurchaseExchange(BigDecimal purchaseExchange) {
		this.purchaseExchange = purchaseExchange;
	}

	public  BigDecimal getSellUsdPrice() {
		return sellUsdPrice;
	}

	public  void setSellUsdPrice(BigDecimal sellUsdPrice) {
		this.sellUsdPrice = sellUsdPrice;
	}

	public  BigDecimal getSellExchange() {
		return sellExchange;
	}

	public  void setSellExchange(BigDecimal sellExchange) {
		this.sellExchange = sellExchange;
	}
	
	public boolean isNovelty() {
		return novelty;
	}

	public void setNovelty(boolean novelty) {
		this.novelty = novelty;
	}
	 
	public boolean isCarousel() {
		return carousel;
	}

	public void setCarousel(boolean carousel) {
		this.carousel = carousel;
	}
	
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	
	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/*
	 * 
	 * Method
	 * 
	 * */
	public boolean isAvailable(){	// наличие
		return (this.count > 0)? true : false;
	} 

	public BigDecimal calcPurchasePrice(){ // цена покупки  
//		return purchaseUsdPrice.multiply(purchaseExchange);
		return getPurchasePrice();
	}
	
	public BigDecimal calcSellPrice(){ // цена продажи
//		return sellUsdPrice.multiply(sellExchange);
		return getSellPrice();
	}
	

}
