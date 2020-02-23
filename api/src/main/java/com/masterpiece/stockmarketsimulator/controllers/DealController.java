package com.masterpiece.stockmarketsimulator.controllers;


import com.masterpiece.stockmarketsimulator.dtos.DealDto;
import com.masterpiece.stockmarketsimulator.services.DealService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/deals")
public class DealController {


    private final DealService dealService;

    public DealController(DealService dealService){
        this.dealService = dealService;
    }


    @PostMapping
    protected void create(@Valid @RequestBody DealDto dto){
        dealService.create(dto);
    }
}
