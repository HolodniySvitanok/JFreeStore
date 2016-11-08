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



@Entity
@Table(name = "group_table")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_group")
	private long id;


	@OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
	private List<Category> category = new ArrayList<Category>();

	
	@Column(name = "name_group", length = ConfigEntity.NAME_LENGTH)
	private String name;

	/*
	 * 
	 * Constructor
	 */
	public Group(long id) {
		this.id = id;
	}

	public Group(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Group() {
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

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
