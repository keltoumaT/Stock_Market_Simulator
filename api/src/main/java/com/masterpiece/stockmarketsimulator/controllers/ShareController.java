package com.masterpiece.stockmarketsimulator.controllers;


import com.masterpiece.stockmarketsimulator.dtos.ShareDto;
import com.masterpiece.stockmarketsimulator.services.ShareService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/shares")
public class ShareController {


    private final ShareService shareService;

    public ShareController(ShareService shareService){
        this.shareService = shareService;
    }


    @PostMapping
    protected void create(@Valid @RequestBody ShareDto dto){
        shareService.create(dto);
    }
}
