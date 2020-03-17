package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.WalletDto;
import com.masterpiece.stockmarketsimulator.dtos.WalletViewDto;

import java.util.List;

public interface WalletService {

    void create(WalletDto dto);

    WalletViewDto getOne(Long id);

    List<WalletViewDto> getAll();
}
