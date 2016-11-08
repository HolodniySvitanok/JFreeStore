package com.holodniysvitanok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.dao.ManufacturerDAO;
import com.holodniysvitanok.entity.Manufacturer;
import com.holodniysvitanok.models.PageItems;
import com.holodniysvitanok.service.CachedData;
import com.holodniysvitanok.service.ManufacturerService;
import com.holodniysvitanok.service.PageItemsBuilder;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;

	private CachedData<Manufacturer> cachedData;

	private final long cachedTime = 120000;

	@Autowired
	private PageItemsBuilder<Manufacturer> pageItemsBuilder;

	@Override
	public PageItems<Manufacturer> getCachedManufacturersPageItems(String sman, String eman, int countIntemInPage) {

		List<Manufacturer> cachedListData = cachedListData();
		PageItems<Manufacturer> pages = null;

		try {

			int start = Integer.parseInt(sman); // парсим с какова элемента начинаем
			int end = Integer.parseInt(eman); // парсим каким элементом заканчиваем
			pages = pageItemsBuilder.getPageItems(cachedListData, start, end, countIntemInPage);

		} catch (Exception ex) {
			ex.printStackTrace();
			//TODO FIX THIS
			pages = pageItemsBuilder.getPageItems(cachedListData, 0, countIntemInPage, countIntemInPage);
		}

		return pages;
	}

	private List<Manufacturer> cachedListData() {
		if (cachedData == null || (System.currentTimeMillis() - cachedData.getTimeQuery()) >= cachedTime) {
			List<Manufacturer> manufacturer = manufacturerDAO.getAllManufacturer(99999);
			cachedData = new CachedData<Manufacturer>(manufacturer);
			return manufacturer;
		}
		return cachedData.getData();
	}

	@Override
	public List<Manufacturer> getCachedAllManufacturers() {
		return cachedListData();
	}
}
