package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.DealDto;
import com.masterpiece.stockmarketsimulator.dtos.DealViewDto;

import java.util.List;

public interface DealService {

    void create(DealDto dto);
    List<DealViewDto> getAllByWalletId(Long id);
}
