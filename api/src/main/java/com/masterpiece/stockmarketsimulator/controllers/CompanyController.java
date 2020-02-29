package com.masterpiece.stockmarketsimulator.controllers;


import com.masterpiece.stockmarketsimulator.entities.Company;
import com.masterpiece.stockmarketsimulator.entities.SharePriceEvolution;
import com.masterpiece.stockmarketsimulator.services.CompanyService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/companies")
public class CompanyController {


    private final CompanyService companyService;


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{name}")
    protected Company getCompanyData(@PathVariable("name") String name){
        return companyService.getCompanyData(name);
    }

    @GetMapping("/{name}/{range}")
    protected SharePriceEvolution[] getSharePriceEvolution(@PathVariable("name")String name, @PathVariable("range")String range){
        return companyService.getSharePriceEvolutionData(name, range);
    }
}
