package com.holodniysvitanok.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.holodniysvitanok.models.PageItems;

@Component
public class PageItemsBuilder<TYPE> {

	public PageItems<TYPE> getPageItems(List<TYPE> items, int start, int end, int countIntemInPage) {

		PageItems<TYPE> pageItems = new PageItems<TYPE>();
		
		end = end + 1;
		if (end >= items.size()) {
			end = items.size();
		}
		
		List<TYPE> subList = items.subList(start, end);
		
		pageItems.setItems(subList); // элементы на странице

		int size = items.size();
		int countPage = size / countIntemInPage;

		if ((size % countIntemInPage) > 0) {
			countPage++;
		}

		pageItems.setCountPage(countPage); // количество возможных страниц

		List<PageItems<TYPE>.PageLine> pageLineList = new ArrayList<>();

		for (int i = 0, y = 0; i < size; i = i + countIntemInPage, y++) {
			PageItems<TYPE>.PageLine pageLine = pageItems.new PageLine();
			pageLine.setStart(i);
			pageLine.setStop(i + countIntemInPage-1);
			pageLineList.add(pageLine);

			if (i == start) {
				pageItems.setThisPage(y);
			}

		}

		pageItems.setLine(pageLineList);

		return pageItems;
	}
	
	public PageItems<TYPE> getPageItems(int countAllProducts, List<TYPE> items, int start, int end, int countIntemInPage){
		PageItems<TYPE> pageItems = new PageItems<TYPE>();
		
		end = end + 1;
		if (end >= items.size()) {
			end = items.size();
		}
				
		pageItems.setItems(items); // элементы на странице

		int size = countAllProducts;
		int countPage = size / countIntemInPage;

		if ((size % countIntemInPage) > 0) {
			countPage++;
		}

		pageItems.setCountPage(countPage); // количество возможных страниц

		List<PageItems<TYPE>.PageLine> pageLineList = new ArrayList<>();

		for (int i = 0, y = 0; i < size; i = i + countIntemInPage, y++) {
			PageItems<TYPE>.PageLine pageLine = pageItems.new PageLine();
			pageLine.setStart(i);
			pageLine.setStop(i + countIntemInPage-1);
			pageLineList.add(pageLine);

			if (i == start) {
				pageItems.setThisPage(y);
			}

		}

		pageItems.setLine(pageLineList);

		return pageItems;
	}

}
