package com.holodniysvitanok.models;

import java.math.BigDecimal;

/**
 * Created by Admin on 02.11.2016.
 */
public class Purchase {

    private int idProduct;
    private int count;


    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public Purchase(){
    }

    public Purchase(int idProduct, int count, BigDecimal priceProduct) {
        this.idProduct = idProduct;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "idProduct=" + idProduct +
                ", count=" + count +
                '}';
    }
}
