package com.masterpiece.stockmarketsimulator.controllers;


import com.masterpiece.stockmarketsimulator.dtos.*;
import com.masterpiece.stockmarketsimulator.entities.Deal;
import com.masterpiece.stockmarketsimulator.services.DealService;
import org.hibernate.annotations.Proxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/deals")
@Proxy(lazy = false)
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

    @GetMapping("{id}")
    protected DealViewDto getDeal(@PathVariable("id")Long id){
        return dealService.getOne(id);
    }

    @GetMapping("/first/{id}")
    protected DealViewDto getFirstDealById(@PathVariable("id")Long walletId){
        return  dealService.getFirstByWalletId(walletId);
    }

    @GetMapping("all/page/{id}")
    protected Page<DealViewDto>getAllProjectedBy(@PathVariable("id")Long walletId, @RequestParam("p") int page, @RequestParam("s")int size, @RequestParam("by")String by){
        Sort sort = Sort.by(Sort.Direction.DESC, by);
        Pageable pageable = PageRequest.of(page, size, sort);
        return dealService.getAllByWalletId(walletId, pageable);
    }

//    @PutMapping("test/{id}")
//    protected void delete(@PathVariable("id")Long id, @RequestBody DealUpdateDto dealUpdateDto){
//        dealService.delete(id, dealUpdateDto);
//    }

    @PutMapping("{id}")
    protected void update(@PathVariable("id")Long id, @Valid @RequestBody DealUpdateDto dealUpdateDto){
       dealService.update(id, dealUpdateDto);
    }

//    @PutMapping("{id}")
//    protected void update(@PathVariable("id")Long id, @Valid @RequestBody DealDto dealDto){
//        dealService.update(id, dealDto);
//    }

    @DeleteMapping("{id}")
    protected void delete(@PathVariable("id")Long id){
        dealService.delete(id);
    }


}
