package com.holodniysvitanok.service;


import java.util.List;

import com.holodniysvitanok.entity.Manufacturer;
import com.holodniysvitanok.models.PageItems;

public interface ManufacturerService {


	PageItems<Manufacturer> getCachedManufacturersPageItems(String sman, String eman,  int countIntemInPag);

	List<Manufacturer> getCachedAllManufacturers();

}
