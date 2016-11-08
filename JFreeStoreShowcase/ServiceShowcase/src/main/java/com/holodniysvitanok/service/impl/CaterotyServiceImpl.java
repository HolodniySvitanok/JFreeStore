package com.holodniysvitanok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.dao.CategoryDAO;
import com.holodniysvitanok.entity.Category;
import com.holodniysvitanok.models.PageItems;
import com.holodniysvitanok.service.CachedData;
import com.holodniysvitanok.service.CategoryService;
import com.holodniysvitanok.service.PageItemsBuilder;

@Service
public class CaterotyServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;
	
	private CachedData<Category> cachedData;

	private final long cachedTime = 120000;

	@Autowired
	private PageItemsBuilder<Category> pageItemsBuilder;

	@Override
	public PageItems<Category> getCachedCategoryPageItems(String scat, String ecat, int countIntemInPage) {

		List<Category> cachedListData = cachedListData();
		PageItems<Category> pages = null;

		try {

			int start = Integer.parseInt(scat); // парсим с какова элемента начинаем
			int end = Integer.parseInt(ecat); // парсим каким элементом заканчиваем
			pages = pageItemsBuilder.getPageItems(cachedListData, start, end, countIntemInPage);

		} catch (Exception ex) {
			ex.printStackTrace();
			pages = pageItemsBuilder.getPageItems(cachedListData, 0, countIntemInPage, countIntemInPage);
		}

		return pages;
	}

	private List<Category> cachedListData() {
		if (cachedData == null || (System.currentTimeMillis() - cachedData.getTimeQuery()) >= cachedTime) {
			List<Category> category = categoryDAO.getAllCategory(99999);
			cachedData = new CachedData<Category>(category);
			return category;
		}
		return cachedData.getData();
	}

	@Override
	public List<Category> getCachedAllCategory() {
		return cachedListData();
	}

}
