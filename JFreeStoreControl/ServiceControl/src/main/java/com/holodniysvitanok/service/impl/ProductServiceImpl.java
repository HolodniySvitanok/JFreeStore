package com.holodniysvitanok.service.impl;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.holodniysvitanok.dao.FileDublicate;
import com.holodniysvitanok.dao.ProductDAO;
import com.holodniysvitanok.entity.Category;
import com.holodniysvitanok.entity.ImageURL;
import com.holodniysvitanok.entity.Manufacturer;
import com.holodniysvitanok.entity.Product;
import com.holodniysvitanok.service.ProductService;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ImageBox box;
	
	@Autowired
	private ServletContext context;


	
	
	public void deleteImageCategoryAndManufacturer(ImageURL imageURL, FileDublicate fileDublicate) {

		if (imageURL.imageUrl() != null) {
			if (fileDublicate.getCountDuplicateFiles(imageURL.imageUrl()) <= 1) {
				box.deleteImage(imageURL.imageUrl(), context.getInitParameter("imageBox"));
			}
		}
	}

	public void deleteImageProduct(ProductDAO productDAO, Product product) {
		if (product.getUrlImage1() != null) {
			if (productDAO.getCountDuplicateFiles(product.getUrlImage1(), 1) <= 1) {
				box.deleteImage(product.getUrlImage1(), context.getInitParameter("imageBox"));
			}
		}
		if (product.getUrlImage2() != null) {
			if (productDAO.getCountDuplicateFiles(product.getUrlImage2(), 2) <= 1) {
				box.deleteImage(product.getUrlImage2(), context.getInitParameter("imageBox"));
			}
		}
		if (product.getUrlImage3() != null) {
			if (productDAO.getCountDuplicateFiles(product.getUrlImage3(), 3) <= 1) {
				box.deleteImage(product.getUrlImage3(), context.getInitParameter("imageBox"));
			}
		}
	}

	public void setProductObject(WebRequest request, Product product) {


		String purchaseUsdPrice = request.getParameter("purchaseUsdPrice");
		String purchaseExchange = request.getParameter("purchaseExchange");
		
		///////////////////////////////////////
		String purchasePrice = request.getParameter("purchasePrice");
		String sellPrice = request.getParameter("sellPrice");
		
		
		
		String sellUsdPrice = request.getParameter("sellUsdPrice");
		String sellExchange = request.getParameter("sellExchange");
		
		String count = request.getParameter("count");
		String mainDescription = request.getParameter("mainDescription");
		String shortDescription = request.getParameter("shortDescription");
		String manufacturer = request.getParameter("manufacturer");
		String show = request.getParameter("show");
		String novelty = request.getParameter("novelty");
		String carousel = request.getParameter("carousel");
		String stock = request.getParameter("stock");
		String category = request.getParameter("category");

		String field1 = request.getParameter("field1");
		String field2 = request.getParameter("field2");
		String field3 = request.getParameter("field3");
		String field4 = request.getParameter("field4");
		String field5 = request.getParameter("field5");
		String field6 = request.getParameter("field6");
		String field7 = request.getParameter("field7");
		String field8 = request.getParameter("field8");
		String field9 = request.getParameter("field9");
		String field10 = request.getParameter("field10");


		if (purchaseUsdPrice != null && !purchaseUsdPrice.equals("")) {
			product.setPurchaseUsdPrice(new BigDecimal(purchaseUsdPrice));
		}

		if (purchaseExchange != null && !purchaseExchange.equals("")) {
			product.setPurchaseExchange(new BigDecimal(purchaseExchange));
		}
		
		if (sellUsdPrice != null && !sellUsdPrice.equals("")) {
			product.setSellUsdPrice(new BigDecimal(sellUsdPrice));
		}
		
		if (sellExchange != null && !sellExchange.equals("")) {
			product.setSellExchange(new BigDecimal(sellExchange));
		}

		if (count != null && !count.equals("")) {
			product.setCount(Integer.parseInt(count));
		}

		if (category != null && !category.equals("")) {
			product.setCategory(new Category(Long.parseLong(category)));
		}
		
		if (purchasePrice != null && !purchasePrice.equals("")) {
			product.setPurchasePrice(new BigDecimal(purchasePrice));
		}
		
		if (sellPrice != null && !sellPrice.equals("")) {
			product.setSellPrice(new BigDecimal(sellPrice));
		}

		product.setMainDescription(mainDescription);
		product.setShortDescription(shortDescription);
		product.setManufacturer(new Manufacturer(Long.parseLong(manufacturer)));
		product.setSupportField1(field1);
		product.setSupportField2(field2);
		product.setSupportField3(field3);
		product.setSupportField4(field4);
		product.setSupportField5(field5);
		product.setSupportField6(field6);
		product.setSupportField7(field7);
		product.setSupportField8(field8);
		product.setSupportField9(field9);
		product.setSupportField10(field10);
		product.setShow(checkboxString(show));
		product.setStock(checkboxString(stock));
		product.setCarousel(checkboxString(carousel));
		product.setNovelty(checkboxString(novelty));
	}

	public void saveImages(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {

		if (file1.getBytes().length != 0) {
			box.deleteImage(product.getUrlImage1(), context.getInitParameter("imageBox"));
			product.setUrlImage1(file1.getOriginalFilename());
			box.saveImage(file1.getBytes(), file1.getOriginalFilename(), context.getInitParameter("imageBox"));
		}

		if (file2.getBytes().length != 0) {
			box.deleteImage(product.getUrlImage2(), context.getInitParameter("imageBox"));
			product.setUrlImage2(file2.getOriginalFilename());
			box.saveImage(file2.getBytes(), file2.getOriginalFilename(), context.getInitParameter("imageBox"));
		}

		if (file3.getBytes().length != 0) {
			box.deleteImage(product.getUrlImage3(), context.getInitParameter("imageBox"));
			product.setUrlImage3(file3.getOriginalFilename());
			box.saveImage(file3.getBytes(), file3.getOriginalFilename(), context.getInitParameter("imageBox"));
		}
	}

	public boolean checkboxString(String checkboxParameter) {
		if (checkboxParameter != null) {
			return true;
		}
		return false;
	}
}
