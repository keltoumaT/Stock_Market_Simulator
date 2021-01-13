package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.WalletDto;
import com.masterpiece.stockmarketsimulator.dtos.WalletViewDto;
import com.masterpiece.stockmarketsimulator.entities.CustomUser;
import com.masterpiece.stockmarketsimulator.entities.Wallet;
import com.masterpiece.stockmarketsimulator.repositories.CustomUserJpaRepository;
import com.masterpiece.stockmarketsimulator.repositories.DealRepository;
import com.masterpiece.stockmarketsimulator.repositories.WalletRepository;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl  extends  AbstractService implements WalletService {

    private final WalletRepository walletRepository;
    private final CustomUserJpaRepository customUserJpaRepository;
    private final DealRepository dealRepository;
    private final CustomUserDetailsServiceImpl customUserDetailsService;

    public WalletServiceImpl(WalletRepository walletRepository, CustomUserJpaRepository customUserJpaRepository, DealRepository dealRepository, CustomUserDetailsServiceImpl customUserDetailsService){
        this.walletRepository = walletRepository;
        this.customUserJpaRepository = customUserJpaRepository;
        this.dealRepository = dealRepository;
        this.customUserDetailsService = customUserDetailsService;
    }
    @Override
    public void create(WalletDto dto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Wallet wallet = getModelMapper().map(dto, Wallet.class);
    populateAndSave(dto, wallet);
    }

    @Override
    public WalletViewDto getOne(Long id) {
        return walletRepository.getById(id);
    }

    @Override
    public List<WalletViewDto> getAll(Long id) {
        return walletRepository.getAllByCustomUserId(id);
    }

    @Override
    public void update(Long id, WalletDto dto) {
        Wallet wallet = walletRepository.findById(id).get();
        populateAndSave(dto, wallet);
    }


    private void populateAndSave(WalletDto dto, Wallet wallet){
        CustomUser customUser = customUserJpaRepository.getOne(customUserDetailsService.getCurrentUserId());
        wallet.setCustomUser(customUser);
        walletRepository.save(wallet);
    }



}
