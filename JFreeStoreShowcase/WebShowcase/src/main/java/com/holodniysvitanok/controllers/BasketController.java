package com.holodniysvitanok.controllers;

import com.holodniysvitanok.conf.WebModuleConfig;
import com.holodniysvitanok.models.BasketStatus;
import com.holodniysvitanok.models.Purchase;
import com.holodniysvitanok.models.PurchasedProduct;
import com.holodniysvitanok.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Admin on 02.11.2016.
 */
@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    /*
    *
    *
    * Добовление товаров в корзину
    * */
    @ResponseBody
    @RequestMapping(value = WebModuleConfig.ADD_TO_BASKET, method = RequestMethod.POST)
    public BasketStatus addToBasket(@CookieValue("basket") String basketCookie, @RequestBody Purchase purchase, HttpServletRequest request, HttpServletResponse response) {

        BasketStatus basketStatus =  basketService.addPurchaseToCookieAndSession(basketCookie, purchase, request, response);

        return basketStatus;
    }

    /*
    *
    *
    * Просмотр корзины
    * */
    @RequestMapping(value = WebModuleConfig.SHOW_BASKET, method = RequestMethod.GET)
    public ModelAndView showBasket(@CookieValue("basket") String basketCookie, ModelAndView model, HttpServletRequest request) {

        List<PurchasedProduct> purchasedProductList = basketService.getPurchasedProductFromBasket(basketCookie, request);

        model.addObject("purchasedList", purchasedProductList);
        model.setViewName(WebModuleConfig.VIEW_SHOW_BASKET);

        return model;
    }

    /*
    *
    *
    *
    * Обновление корзины
    * */
    @RequestMapping(value = WebModuleConfig.UPDATE_BASKET, method=RequestMethod.POST)
    public ModelAndView updateBasket(@CookieValue("basket") String basketCookie, ModelAndView model, HttpServletRequest request, HttpServletResponse response){

        List<PurchasedProduct> purchasedProductList = basketService.updateBasket(basketCookie, request, response);

        model.addObject("purchasedList", purchasedProductList);
        model.setViewName(WebModuleConfig.VIEW_SHOW_BASKET);

        return model;
    }

}
