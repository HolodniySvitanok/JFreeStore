package com.holodniysvitanok.service;

import com.holodniysvitanok.models.BasketStatus;
import com.holodniysvitanok.models.Purchase;
import com.holodniysvitanok.models.PurchasedProduct;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Admin on 02.11.2016.
 */
public interface BasketService {

    BasketStatus addPurchaseToCookieAndSession(String basketCookieValue, Purchase purchase, HttpServletRequest request, HttpServletResponse response);

    List<PurchasedProduct> getPurchasedProductFromBasket(String basketCookieValue, HttpServletRequest request);

    List<PurchasedProduct> updateBasket(String basketCookie, HttpServletRequest request, HttpServletResponse response);
}
