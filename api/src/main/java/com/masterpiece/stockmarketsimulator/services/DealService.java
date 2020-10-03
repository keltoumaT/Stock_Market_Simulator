package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DealService {

    void create(DealDto dto);
    List<DealViewDto> getAllByWalletId(Long id);
    DealViewDto getOne(Long id);
    DealViewDto getFirstByWalletId(Long id);
//    void delete(Long id, DealUpdateDto dealUpdateDto);
    Page<DealViewDto>getAllByWalletId(Long walletId, Pageable pageable);
    void update (Long id, DealUpdateDto dealUpdateDto);
//    void update (Long id, DealDto dto);
    void delete(Long id);
    DealViewDto getWalletIdById(Long id);
}
