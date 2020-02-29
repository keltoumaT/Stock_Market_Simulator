package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.entities.Company;
import com.masterpiece.stockmarketsimulator.entities.SharePriceEvolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class CompanyService {

    @Autowired
    RestTemplate restTemplate;

    private String  url_base = "https://sandbox.iexapis.com/stable/stock/";
    private String token = "?token=Tpk_bb8d26bbc06543a884fc9098c59f2ae8";
    public CompanyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Company getCompanyData(String name){
        String url  = url_base + name +"/quote" + token;
        System.out.println(url);
        return restTemplate.getForObject(url, Company.class);
    }

    public SharePriceEvolution[] getSharePriceEvolutionData(String symbol, String range){
        String chart = "/chart/";
        String url = url_base + symbol + chart + range + token;
        System.out.println(url);
        return  restTemplate.getForObject(url, SharePriceEvolution[].class);
    }

}
