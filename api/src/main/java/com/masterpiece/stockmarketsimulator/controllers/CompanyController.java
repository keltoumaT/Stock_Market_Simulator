package com.masterpiece.stockmarketsimulator.controllers;


import com.masterpiece.stockmarketsimulator.entities.Company;
import com.masterpiece.stockmarketsimulator.entities.DealCurrentPrice;
import com.masterpiece.stockmarketsimulator.entities.SharePriceEvolution;
import com.masterpiece.stockmarketsimulator.services.CompanyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/private/companies")
public class CompanyController {


    private final CompanyService companyService;


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{name}")
    protected Company getCompanyData(@PathVariable("name") String name){
        return companyService.getCompanyData(name);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{name}/{range}")
    protected SharePriceEvolution[] getSharePriceEvolution(@PathVariable("name")String name, @PathVariable("range")String range){
        return companyService.getSharePriceEvolutionData(name, range);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/currentPrice/{walletId}")
    protected DealCurrentPrice getDealCurrentPrice(@PathVariable("walletId")Long walletId){
        return companyService.getLatestPrice(walletId);
    }
}
