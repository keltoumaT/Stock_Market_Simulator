package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.DealDto;
import com.masterpiece.stockmarketsimulator.dtos.DealViewDto;
import com.masterpiece.stockmarketsimulator.entities.Deal;
import com.masterpiece.stockmarketsimulator.entities.Wallet;
import com.masterpiece.stockmarketsimulator.repositories.DealRepository;
import com.masterpiece.stockmarketsimulator.repositories.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final WalletRepository walletRepository;

    public DealServiceImpl(DealRepository dealRepository, WalletRepository walletRepository) {
        this.dealRepository = dealRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public void create(DealDto dto) {
        Deal deal = new Deal();
        populateAndSave(dto, deal);
    }

    @Override
    public List<DealViewDto> getAllByWalletId(Long id) {
        return dealRepository.getAllByWalletId(id);
    }


    private void populateAndSave(DealDto dto, Deal deal){
        deal.setCompanyName(dto.getCompanyName());
        deal.setDate(dto.getDate());
        deal.setQuantity(dto.getQuantity());
        deal.setUnityPrice(dto.getUnityPrice());
        Wallet wallet = walletRepository.getOne(dto.getWalletId());
        deal.setWallet(wallet);
        dealRepository.save(deal);

    }
}
