package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.WalletDto;
import com.masterpiece.stockmarketsimulator.dtos.WalletViewDto;

public interface WalletService {

    void create(WalletDto dto);

    WalletViewDto getOne(Long id);
}
