package com.holodniysvitanok.models;

import java.util.List;

public class PageItems<TYPE> {

	private int countPage;

	private int thisPage;

	private List<TYPE> items;

	private List<PageLine> line;

	
	/*
	 * 
	 * 
	 * 
	 * Getters and setters
	 * */
	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public int getThisPage() {
		return thisPage;
	}

	public void setThisPage(int thisPage) {
		this.thisPage = thisPage;
	}

	public List<TYPE> getItems() {
		return items;
	}

	public void setItems(List<TYPE> items) {
		this.items = items;
	}

	public List<PageLine> getLine() {
		return line;
	}

	public void setLine(List<PageLine> line) {
		this.line = line;
	}





	/*
	 * 
	 * 
	 * Inner class
	 * */
	public class PageLine {
		
		private int start;
		private int stop;
		
		public int getStart() {
			return start;
		}
		public void setStart(int start) {
			this.start = start;
		}
		public int getStop() {
			return stop;
		}
		public void setStop(int stop) {
			this.stop = stop;
		}
	}
	
}

