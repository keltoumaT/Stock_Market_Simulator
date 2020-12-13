package com.masterpiece.stockmarketsimulator.controllers;


import com.masterpiece.stockmarketsimulator.entities.CompanyCurrentInfo;
import com.masterpiece.stockmarketsimulator.entities.DealCurrentPrice;
import com.masterpiece.stockmarketsimulator.entities.SharePriceEvolution;
import com.masterpiece.stockmarketsimulator.services.CompanyCurrentInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/private/companies")
public class CompanyController {


    private final CompanyCurrentInfoService companyCurrentInfoService;


    public CompanyController(CompanyCurrentInfoService companyCurrentInfoService) {
        this.companyCurrentInfoService = companyCurrentInfoService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{name}")
    protected CompanyCurrentInfo getCompanyData(@PathVariable("name") String name){
        return companyCurrentInfoService.getCompanyData(name);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{name}/{range}")
    protected SharePriceEvolution[] getSharePriceEvolution(@PathVariable("name")String name, @PathVariable("range")String range){
        return companyCurrentInfoService.getSharePriceEvolutionData(name, range);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/currentPrice/{walletId}")
    protected DealCurrentPrice getDealCurrentPrice(@PathVariable("walletId")Long walletId){
        return companyCurrentInfoService.getLatestPrice(walletId);
    }
}
