package com.holodniysvitanok.service;

import java.util.List;

import com.holodniysvitanok.entity.Manufacturer;

public interface FunctionManufacturer {

	default Manufacturer getManufacturer(List<Manufacturer> list, String str){
		
		if(str.equals("all")){
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setName("Все производители");
			return manufacturer;
		}
		
		long id = Long.parseLong(str);
		for(Manufacturer manufacturer : list){
			if(manufacturer.getId() == id){
				return manufacturer;
			}
		}
		return null;
	}
	
}
