package com.holodniysvitanok.service;

import java.io.IOException;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.holodniysvitanok.dao.FileDublicate;
import com.holodniysvitanok.dao.ProductDAO;
import com.holodniysvitanok.entity.ImageURL;
import com.holodniysvitanok.entity.Product;

public interface ProductService {

	void deleteImageCategoryAndManufacturer(ImageURL imageURL, FileDublicate fileDublicate);

	void deleteImageProduct(ProductDAO productDAO, Product product);

	void setProductObject(WebRequest request, Product product);

	void saveImages(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;

	boolean checkboxString(String checkboxParameter);
}
