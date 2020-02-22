package com.masterpiece.stockmarketsimulator.controllers;


import com.masterpiece.stockmarketsimulator.dtos.WalletDto;
import com.masterpiece.stockmarketsimulator.services.WalletService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @PostMapping
    protected void create(WalletDto dto){
        walletService.create(dto);
    }
}
