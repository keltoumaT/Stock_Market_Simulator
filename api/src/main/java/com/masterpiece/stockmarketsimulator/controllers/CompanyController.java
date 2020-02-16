package com.masterpiece.stockmarketsimulator.controllers;


import com.masterpiece.stockmarketsimulator.entities.Company;
import com.masterpiece.stockmarketsimulator.services.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
