package com.holodniysvitanok.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="manufacturer_table")
public class Manufacturer implements ImageURL{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_manufacturer")
    private Long id;
	
	@Column(name = "url_image", length = ConfigEntity.URL_LENGTH)
	private String urlImage;
	
	@Column(name = "name_manufacturer", unique=true, nullable=false,  length = ConfigEntity.NAME_LENGTH)
	private String name;
	
	@Column(name = "description_manufacturer", length = ConfigEntity.DESCRIPTION_LENGTH)
	private String description;
	
	@Column(name = "country_manufacturer", length = ConfigEntity.DEFAULT_FIELD_LEHGTH)
	private String country;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manufacturer")
	private List<Product> products = new ArrayList<>();


	
	/*
	 * 
	 * Constructor
	 * 
	 * */
	
	public Manufacturer() {
	}

	public Manufacturer(long id) {
		this.id= id;
	}

	/*
	 * 
	 * Getters and Setters
	 * 
	 * */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String imageUrl() {
		return getUrlImage();
	}

	

	
	
}
