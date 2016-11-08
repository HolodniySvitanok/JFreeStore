package com.holodniysvitanok.service.impl;

import com.holodniysvitanok.models.Purchase;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Admin on 04.11.2016.
 */
public class BasketServiceImplTest {

    @Test
    public void test() {

        Purchase p = new Purchase(1, 2, new BigDecimal("3.25"));
        Purchase p2 = new Purchase(6, 1, new BigDecimal("5.25"));

        JSONObject jo = new JSONObject();
        jo.put("id", p.getIdProduct());
        jo.put("count", p.getCount());


        JSONObject jo1 = new JSONObject();
        jo1.put("id", p2.getIdProduct());
        jo1.put("count", p2.getCount());


        JSONArray array = new JSONArray();
        array.put(jo);
        array.put(jo1);


        JSONArray array2 = new JSONArray(array.toString());

        for (int i = 0; i < array2.length(); i++) {
            System.out.println(array2.getJSONObject(i));
        }


    }
}