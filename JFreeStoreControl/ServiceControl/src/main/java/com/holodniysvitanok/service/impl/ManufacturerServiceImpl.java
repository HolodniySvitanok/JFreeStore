package com.holodniysvitanok.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.dao.ManufacturerDAO;
import com.holodniysvitanok.entity.Manufacturer;
import com.holodniysvitanok.service.ManufacturerService;


@Service
public class ManufacturerServiceImpl implements ManufacturerService{

	@Autowired
	private ManufacturerDAO manufacturerDAO; 
	
	@Override
	public Manufacturer setManufacturer(Manufacturer manufacturer, HttpServletRequest request) {
		
		manufacturer.setName(request.getParameter("name"));
		manufacturer.setDescription(request.getParameter("description"));
		manufacturer.setCountry(request.getParameter("country"));
		
		return manufacturer;
	}

	@Override
	public List<Manufacturer> getAllManufacturer(int count) {
		
		return manufacturerDAO.getAllManufacturer(count);
	}

	@Override
	public Manufacturer getManufacturer(long id) {
		
		return manufacturerDAO.getManufacturer(id);
	}

	@Override
	public void saveOrUpdateManufacturer(Manufacturer manufacturer) {
		manufacturerDAO.saveOrUpdateManufacturer(manufacturer);
		
	}

	@Override
	public void deleteManufacturer(Manufacturer manufacturer) {
		manufacturerDAO.deleteManufacturer(manufacturer);
		
	}

	@Override
	public long getCountDuplicateFiles(String fileName) {
		return manufacturerDAO.getCountDuplicateFiles(fileName);
	}

}
