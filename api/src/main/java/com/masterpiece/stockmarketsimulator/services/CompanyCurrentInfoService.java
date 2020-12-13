package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.DealViewDto;
import com.masterpiece.stockmarketsimulator.entities.CompanyCurrentInfo;
import com.masterpiece.stockmarketsimulator.entities.DealCurrentPrice;
import com.masterpiece.stockmarketsimulator.entities.SharePriceEvolution;
import com.masterpiece.stockmarketsimulator.repositories.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class CompanyCurrentInfoService {

    @Autowired
    RestTemplate restTemplate;
    private final DealRepository dealRepository;

    private String url_base = "https://sandbox.iexapis.com/stable/stock/";
    private String token = "token=Tpk_bb8d26bbc06543a884fc9098c59f2ae8";

    public CompanyCurrentInfoService(RestTemplate restTemplate, DealRepository dealRepository) {
        this.restTemplate = restTemplate;
        this.dealRepository = dealRepository;
    }


    public CompanyCurrentInfo getCompanyData(String name) {
        String url = url_base + name + "/quote?" + token;
        System.out.println(restTemplate);
        return restTemplate.getForObject(url, CompanyCurrentInfo.class);
    }

    public SharePriceEvolution[] getSharePriceEvolutionData(String symbol, String range) {
        String chart = "/chart/";
        String url = url_base + symbol + chart + range + "?" + token;
        System.out.println(url);
        return restTemplate.getForObject(url, SharePriceEvolution[].class);
    }

    public DealCurrentPrice getLatestPrice(Long walletId) {
        List<DealViewDto> deals = dealRepository.getAllByWalletId(walletId);
        DealCurrentPrice dealCurrentPrice = new DealCurrentPrice();
        String symbol = "";
        for (DealViewDto dealViewDto : deals) {
            symbol += dealViewDto.getSymbol() + ",";
        }

        String x = "market/batch?symbols=" + symbol + "&types=quote&filter=latestPrice";
        String url = url_base + x + "&" + token;
        dealCurrentPrice = restTemplate.getForObject(url, DealCurrentPrice.class);
        System.out.println(dealCurrentPrice.getDetails());
        return dealCurrentPrice;

    }
}
