package com.holodniysvitanok.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.holodniysvitanok.dao.FileDublicate;
import com.holodniysvitanok.entity.Manufacturer;

public interface ManufacturerService extends FileDublicate {
	
	
	Manufacturer setManufacturer(Manufacturer manufacturer, HttpServletRequest request);
	
	List<Manufacturer> getAllManufacturer(int count);

	Manufacturer getManufacturer(long id);

	void saveOrUpdateManufacturer(Manufacturer manufacturer);

	void deleteManufacturer(Manufacturer manufacturer);
	
}
