package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.DealDto;
import com.masterpiece.stockmarketsimulator.dtos.DealViewDto;
import com.masterpiece.stockmarketsimulator.entities.Deal;
import com.masterpiece.stockmarketsimulator.entities.Wallet;
import com.masterpiece.stockmarketsimulator.repositories.DealRepository;
import com.masterpiece.stockmarketsimulator.repositories.WalletRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.time.LocalDateTime;
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

    @Override
    public DealViewDto getOne(Long id) {
       return dealRepository.getById(id);
    }

    @Override
    public DealViewDto getFirstByWalletId(Long id) {
        return dealRepository.getFirstByWalletId(id);
    }

    @Override
    public void delete(Long id) {
        dealRepository.deleteById(id);
    }


    private void populateAndSave(DealDto dto, Deal deal) {
        deal.setCompanyName(dto.getCompanyName());
        LocalDateTime dateTime = LocalDateTime.now();
        deal.setDate(dateTime);
        deal.setQuantity(dto.getQuantity());
        deal.setUnityPrice(dto.getUnityPrice());
        deal.setSymbol(dto.getSymbol());
        Wallet wallet = walletRepository.getOne(dto.getWalletId());
        deal.setWallet(wallet);
        wallet.setCapital((wallet.getCapital()-dto.getQuantity()*dto.getUnityPrice()));
        dealRepository.save(deal);
        walletRepository.save(wallet);
    }

    @Override
    public Page<DealViewDto> getAllByWalletId(Long walletId, Pageable pageable) {
        return dealRepository.getAllByWalletId(walletId, pageable);
    }

    @Override
    public void update(Long id, DealDto dto) {
        Deal deal = dealRepository.getOne(id);
        populateAndSave(dto, deal);
    }

}
