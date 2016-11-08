package com.holodniysvitanok.models;

import java.math.BigDecimal;

/**
 * Created by Admin on 06.11.2016.
 */
public class BasketStatus {

    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BasketStatus(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BasketStatus() {
        totalPrice = new BigDecimal("0.00");
    }
}
