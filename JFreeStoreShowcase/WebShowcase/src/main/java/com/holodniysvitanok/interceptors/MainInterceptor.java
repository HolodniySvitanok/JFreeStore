package com.holodniysvitanok.interceptors;

import com.holodniysvitanok.entity.Category;
import com.holodniysvitanok.entity.Manufacturer;
import com.holodniysvitanok.entity.Product;
import com.holodniysvitanok.entity.User;
import com.holodniysvitanok.models.BasketStatus;
import com.holodniysvitanok.service.BasketService;
import com.holodniysvitanok.service.CategoryService;
import com.holodniysvitanok.service.ManufacturerService;
import com.holodniysvitanok.service.ProductService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class MainInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ServletContext context;

    /*
    *
    *
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        loadAttribute(request);
        userInspect(session);
        BasketStatus basketStatus = basketInspect(request, response);

        session.setAttribute("basketStatus", basketStatus);

        return super.preHandle(request, response, handler);
    }

    /*
    *
    *
    * */
    private BasketStatus basketInspect(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BasketStatus basketStatus = (BasketStatus) request.getSession().getAttribute("basketStatus");

        if (basketStatus != null) {
            return basketStatus;
        }

        basketStatus = getBasketStatusFromCookie(request, response);


        return basketStatus;
    }

    /*
    *
    *
    *
    *
    *
    *
    * */
    private BasketStatus getBasketStatusFromCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            request.setAttribute("globalMessage", "Not find cookies !");

            return null;
        }

        Cookie basketCookie = searhCookie(cookies, "basket");

        if (basketCookie == null) { // если куков нет, то создать пустые и вернуть пустую корзину
            JSONArray basketJSONArray = new JSONArray();
            basketCookie = new Cookie("basket", basketJSONArray.toString());
            basketCookie.setMaxAge(60 * 60 * 24 * 31);
            basketCookie.setPath(context.getContextPath());



            response.addCookie(basketCookie);
            return new BasketStatus(new BigDecimal("0.00"));
        }

        // если куки нашлись, то берем информацию из них
        String basketCookieValue = basketCookie.getValue();
        JSONArray basketJSONArray = new JSONArray(basketCookieValue);


        BasketStatus basketStatus = new BasketStatus();

        for (int i = 0; i < basketJSONArray.length(); i++) {
            JSONObject jsonObject = basketJSONArray.getJSONObject(i);
            long idProduct = jsonObject.getLong("idProduct");
            int countProduct = jsonObject.getInt("countProduct");

            Product productById = productService.getProductById(idProduct);

            BigDecimal multiply = productById.getSellPrice().multiply(new BigDecimal(countProduct));

            basketStatus.setTotalPrice(basketStatus.getTotalPrice().add(multiply));
        }

        return basketStatus;
    }


    /*
    *
    *
    *
    *
    *
    *
    * Загружает в аттрибуты кешированые данные
    * */
    private void loadAttribute(HttpServletRequest request) {
        List<Category> cachedAllCategory = categoryService.getCachedAllCategory();
        List<Manufacturer> cachedAllManufacturer = manufacturerService.getCachedAllManufacturers();

        request.setAttribute("categoryList", cachedAllCategory);
        request.setAttribute("categoryManufacturer", cachedAllManufacturer);
    }

    /*
    *
    *
    *
    *
    *
    *
    *
    *
    * Следит да пользователем
    * */
    private void userInspect(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            return;
        }

        user = new User();
        user.setAccessLevel(10);
        user.setName("Гость");
        session.setAttribute("user", user);
    }

    private Cookie searhCookie(Cookie[] cookies, String basket) {

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(basket)) {
                return cookie;
            }
        }
        return null;
    }

}
