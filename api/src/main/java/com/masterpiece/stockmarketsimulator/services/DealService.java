package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.DealDto;
import com.masterpiece.stockmarketsimulator.dtos.DealViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DealService {

    void create(DealDto dto);
    List<DealViewDto> getAllByWalletId(Long id);
    DealViewDto getOne(Long id);
    DealViewDto getFirstByWalletId(Long id);
    void delete(Long id);
    Page<DealViewDto>getAllByWalletId(Long walletId, Pageable pageable);
    void update (Long id, DealDto dto);
}
