package com.holodniysvitanok.models;

import com.holodniysvitanok.entity.Product;

import java.math.BigDecimal;

/**
 * Created by Admin on 05.11.2016.
 */
public class PurchasedProduct {

    private Product product;
    private int count;
    private BigDecimal totalPrice;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    public PurchasedProduct(Product product, int count, BigDecimal totalPrice) {
        this.product = product;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public PurchasedProduct() {
    }
}
