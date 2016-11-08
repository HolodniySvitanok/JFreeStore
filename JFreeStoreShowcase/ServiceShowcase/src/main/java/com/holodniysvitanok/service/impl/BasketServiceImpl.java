package com.holodniysvitanok.service.impl;

import com.holodniysvitanok.dao.ProductDAO;
import com.holodniysvitanok.entity.Product;
import com.holodniysvitanok.models.BasketStatus;
import com.holodniysvitanok.models.Purchase;
import com.holodniysvitanok.models.PurchasedProduct;
import com.holodniysvitanok.service.BasketService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 02.11.2016.
 */
@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<PurchasedProduct> getPurchasedProductFromBasket(String basketCookieValue, HttpServletRequest request) {

        JSONArray jsonArray = new JSONArray(basketCookieValue);

        List<PurchasedProduct> purchasedProductList = getPurchasedProductWithJSONArray(jsonArray);


        return purchasedProductList;
    }

    @Override
    public List<PurchasedProduct> updateBasket(String basketCookieValue, HttpServletRequest request, HttpServletResponse response) {

        String[] idParameterValues = request.getParameterValues("id");
        String[] countParameterValues = request.getParameterValues("count");
        String[] deleteParameterValues = request.getParameterValues("delete");

        JSONArray jsonArray = new JSONArray(basketCookieValue);

        List<PurchasedProduct> purchasedProductWithJSONArray = getPurchasedProductWithJSONArray(jsonArray);

        Iterator<PurchasedProduct> iterator = purchasedProductWithJSONArray.iterator();

        BasketStatus basketStatus = new BasketStatus();

        p1:
        while (iterator.hasNext()) {
            PurchasedProduct nextPurchasedProduct = iterator.next();
            if (deleteParameterValues != null) {
                for (String delete : deleteParameterValues) {
                    if (nextPurchasedProduct.getProduct().getId() == Long.parseLong(delete)) {
                        iterator.remove();
                        continue p1;
                    }
                }
            }
            for (int i = 0; i < idParameterValues.length; i++) {
                if (nextPurchasedProduct.getProduct().getId() == Long.parseLong(idParameterValues[i])) {
                    nextPurchasedProduct.setCount(Integer.parseInt(countParameterValues[i]));
                    BigDecimal totalPrice = new BigDecimal(nextPurchasedProduct.getCount()).multiply(nextPurchasedProduct.getProduct().getSellPrice());
                    nextPurchasedProduct.setTotalPrice(totalPrice);

                    basketStatus.setTotalPrice(basketStatus.getTotalPrice().add(totalPrice));
                }
            }

        }

        request.getSession().setAttribute("basketStatus", basketStatus);

        JSONArray jsonArrayWithPurchasedProduct = getJSONArrayWithPurchasedProduct(purchasedProductWithJSONArray);

        Cookie cookie = new Cookie("basket", jsonArrayWithPurchasedProduct.toString());
        cookie.setMaxAge(60 * 60 * 24 * 31);

        response.addCookie(cookie);

        return purchasedProductWithJSONArray;
    }

    private List<PurchasedProduct> getPurchasedProductWithJSONArray(JSONArray jsonArray) {

        List<PurchasedProduct> purchasedProductList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            long idProduct = jsonObject.getLong("idProduct");
            int countProduct = jsonObject.getInt("countProduct");

            Product product = productDAO.getProduct(idProduct);
            BigDecimal totalPrice = product.getSellPrice().multiply(new BigDecimal(countProduct));

            purchasedProductList.add(new PurchasedProduct(product, countProduct, totalPrice));
        }

        return purchasedProductList;
    }

    private JSONArray getJSONArrayWithPurchasedProduct(List<PurchasedProduct> purchasedProductList) {
        JSONArray jsonArray = new JSONArray();

        for (PurchasedProduct purchasedProduct : purchasedProductList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idProduct", purchasedProduct.getProduct().getId());
            jsonObject.put("countProduct", purchasedProduct.getCount());

            jsonArray.put(jsonObject);
        }


        return jsonArray;
    }


    @Override
    public BasketStatus addPurchaseToCookieAndSession(String basketCookieValue, Purchase purchase, HttpServletRequest request, HttpServletResponse response) {

        JSONArray jsonArray = new JSONArray(basketCookieValue);

        PurchasedProductAndIndex purchasedProductAndIndex = searchPurchaseInJSONArray(purchase, jsonArray);

        JSONObject jsonObject = new JSONObject();

        if (purchasedProductAndIndex == null) { // логика в зависимости не было ли в корзине такова товара или был
            jsonObject.put("idProduct", purchase.getIdProduct());
            jsonObject.put("countProduct", purchase.getCount());
            jsonArray.put(jsonObject);
        } else {
            purchasedProductAndIndex.count = purchasedProductAndIndex.count + purchase.getCount();
            jsonObject.put("idProduct", purchase.getIdProduct());
            jsonObject.put("countProduct", purchasedProductAndIndex.count);
            jsonArray.put(purchasedProductAndIndex.indexInArray, jsonObject);
        }

        Cookie basketCookie = new Cookie("basket", jsonArray.toString());
        basketCookie.setMaxAge(60 * 60 * 24 * 31);
        response.addCookie(basketCookie);

        // подсчитываем стоймость всех товаров с учетом их количества
        List<PurchasedProductAndIndex> listIdProductWhithJSONArray = getListIdProductWhithJSONArray(jsonArray);
        List<Long> listIdInListPurchaseProduct = getListIdInListPurchaseProduct(listIdProductWhithJSONArray);

        List<Product> productsByIdList = productDAO.getProductsByIdList(listIdInListPurchaseProduct);

        BasketStatus basketStatus = new BasketStatus();

        for (int i = 0; i < productsByIdList.size(); i++) {
            for (int j = 0; j < listIdProductWhithJSONArray.size(); j++) {
                if (productsByIdList.get(i).getId() == listIdProductWhithJSONArray.get(j).idProduct) {
                    BigDecimal multiply = productsByIdList.get(i).getSellPrice().multiply(new BigDecimal(listIdProductWhithJSONArray.get(j).count));
                    BigDecimal add = basketStatus.getTotalPrice().add(multiply);
                    basketStatus.setTotalPrice(add);
                }
            }
        }

        request.getSession().setAttribute("basketStatus", basketStatus);

        return basketStatus;
    }

    /*
    *
    *
    * */
    private PurchasedProductAndIndex searchPurchaseInJSONArray(Purchase purchase, JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            long idProduct = jsonArray.getJSONObject(i).getLong("idProduct");
            if (idProduct == purchase.getIdProduct()) {
                int count = jsonArray.getJSONObject(i).getInt("countProduct");

                return new PurchasedProductAndIndex(idProduct, count, i);
            }
        }
        return null;
    }


    private List<Long> getListIdInListPurchaseProduct(List<PurchasedProductAndIndex> purchasedProductAndIndexList) {

        List<Long> listIdInListPurchaseProduct = new ArrayList<>();

        for (PurchasedProductAndIndex purchasedProductAndIndex : purchasedProductAndIndexList) {
            listIdInListPurchaseProduct.add(purchasedProductAndIndex.idProduct);
        }
        return listIdInListPurchaseProduct;
    }


    private List<PurchasedProductAndIndex> getListIdProductWhithJSONArray(JSONArray jsonArray) {
        List<PurchasedProductAndIndex> listSupportClases = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            long idProduct = jsonArray.getJSONObject(i).getLong("idProduct");
            int count = jsonArray.getJSONObject(i).getInt("countProduct");

            PurchasedProductAndIndex purchasedProductAndIndex = new PurchasedProductAndIndex(idProduct, count);
            listSupportClases.add(purchasedProductAndIndex);
        }
        return listSupportClases;
    }

    /*
    *
    *
    * Support  inner class
    * */
    private static class PurchasedProductAndIndex {
        long idProduct;
        int count;

        int indexInArray;

        public PurchasedProductAndIndex(long idProduct, int count, int indexInArray) {
            this.idProduct = idProduct;
            this.count = count;
            this.indexInArray = indexInArray;

        }

        public PurchasedProductAndIndex(long idProduct, int count) {
            this.idProduct = idProduct;
            this.count = count;

        }

    }


}