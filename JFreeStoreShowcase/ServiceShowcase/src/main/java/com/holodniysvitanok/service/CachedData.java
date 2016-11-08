package com.holodniysvitanok.service;

import java.util.List;

public class CachedData<TYPE> {

	private List<TYPE> listData;
	
	private long timeQuery;

	private TYPE object;
	
	
	/*
	 * 
	 * 
	 * 
	 * Getters and setters
	 * */
	public List<TYPE> getData() {
		return listData;
	}

	public void setData(List<TYPE> date) {
		this.listData = date;
	}

	public long getTimeQuery() {
		return timeQuery;
	}

	public void setTimeQuery(long timeQuery) {
		this.timeQuery = timeQuery;
	}
	
	public TYPE getObject() {
		return object;
	}

	public void setObject(TYPE object) {
		this.object = object;
	}

	/*
	 * 
	 * 
	 * 
	 * Constructor
	 * */
	public CachedData(List<TYPE> date) {
		this.listData = date;
		this.timeQuery = System.currentTimeMillis();
	}
	public CachedData(TYPE object) {
		this.object = object;
		this.timeQuery = System.currentTimeMillis();
	}
	
	
}
