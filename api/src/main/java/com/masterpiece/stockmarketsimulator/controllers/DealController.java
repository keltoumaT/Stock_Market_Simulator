package com.masterpiece.stockmarketsimulator.controllers;


import com.masterpiece.stockmarketsimulator.dtos.DealDto;
import com.masterpiece.stockmarketsimulator.dtos.DealViewDto;
import com.masterpiece.stockmarketsimulator.services.DealService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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


    @GetMapping("all/{id}")
    protected List<DealViewDto> getAllByWalletId(@PathVariable("id")Long id){
        return dealService.getAllByWalletId(id);
    }
}
