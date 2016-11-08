package com.holodniysvitanok.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.holodniysvitanok.service.ExchangerateService;

@Service
public class ExchangeratePrivateBankServiceImpl implements ExchangerateService{

	private static final String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
	
	@Override
	public double rateCoefficient()  {
		
		try {
			String jsonString = getJSONStringFromPrivateBank(url);

			JSONArray jsonArray = new JSONArray(jsonString);
			JSONObject json = (JSONObject) jsonArray.get(2);
			double sale = json.getDouble("sale");
			return sale;
			
		} catch (IOException e) {
			return 1.0;
		}	
	}

	private String getJSONStringFromPrivateBank(String resource) throws IOException{
		URL url = new URL(resource);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		
		StringBuilder sb = new StringBuilder();
		
		String temp = null;
		
		while((temp = reader.readLine()) != null){
			sb.append(temp+"\n");
		}
		
		return sb.toString();
	}
	
}
