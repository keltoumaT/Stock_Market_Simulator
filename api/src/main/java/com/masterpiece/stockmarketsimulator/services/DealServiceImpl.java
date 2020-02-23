package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.DealDto;
import com.masterpiece.stockmarketsimulator.entities.Deal;
import com.masterpiece.stockmarketsimulator.repositories.DealRepository;
import org.springframework.stereotype.Service;


@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;

    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    public void create(DealDto dto) {
        Deal deal = new Deal();
        populateAndSave(dto, deal);
    }


    private void populateAndSave(DealDto dto, Deal deal){
        deal.setCompanyName(dto.getCompanyName());
        deal.setDate(dto.getDate());
        deal.setQuantity(dto.getQuantity());
        deal.setUnityPrice(dto.getUnityPrice());
        dealRepository.save(deal);

    }
}
