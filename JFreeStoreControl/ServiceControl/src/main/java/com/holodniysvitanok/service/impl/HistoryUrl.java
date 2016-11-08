package com.holodniysvitanok.service.impl;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

public class HistoryUrl {

	private LinkedList<String> history;
	
	private int capacity;
	
	private int count;
	
	public HistoryUrl(int capacity){
		history = new LinkedList<String>();
		this.capacity = capacity;
	}
	
	public void addToHistory(HttpServletRequest request){
		if(count > capacity && history.size() != 0){
			history.removeFirst();
		}
		String previousPageURL = request.getHeader("referer");
		history.addLast(previousPageURL);
		count++;
	}
	
	public String getUrlOfHistory(){
		if(count == 0){
			return null;
		}
		count--;
		return history.removeLast();
	}
	
}
