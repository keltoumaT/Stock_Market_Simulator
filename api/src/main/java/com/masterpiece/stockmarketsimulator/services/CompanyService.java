package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CompanyService {

    @Autowired
    RestTemplate restTemplate;



    public CompanyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Company getCompanyData(String name){
        String url_base = "https://sandbox.iexapis.com/stable/stock/";
        String token = "/quote?token=Tpk_bb8d26bbc06543a884fc9098c59f2ae8";
        String url  = url_base + name + token;
        return restTemplate.getForObject(url, Company.class);
    }


}
