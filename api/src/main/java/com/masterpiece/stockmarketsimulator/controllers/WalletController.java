package com.masterpiece.stockmarketsimulator.controllers;


import com.masterpiece.stockmarketsimulator.dtos.WalletDto;
import com.masterpiece.stockmarketsimulator.dtos.WalletViewDto;
import com.masterpiece.stockmarketsimulator.services.WalletService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("private/wallets")
public class WalletController {

    private final WalletService walletService;


    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    protected void create(@Valid @RequestBody WalletDto dto) {
        walletService.create(dto);
    }

    @PostAuthorize("returnObject.customUserId == @customUserDetailsServiceImpl.currentUserId")
    @GetMapping("/{id}")
    protected WalletViewDto getWallet(@PathVariable("id") Long id) {
        WalletViewDto wallet = walletService.getOne(id);
        return wallet;
    }

    @PostAuthorize("#id == @customUserDetailsServiceImpl.currentUserId")
    @GetMapping("all/{id}")
    protected List<WalletViewDto> getAll(@PathVariable("id") Long id) {
        return walletService.getAll(id);
    }

    @PreAuthorize("dto.customUserId == @customUserDetailsServiceImpl.currentUserId")
    @PutMapping("/{id}")
    protected void update(@PathVariable("id") Long id,
                          @Valid @RequestBody WalletDto dto) {
        walletService.update(id, dto);
    }

}
