package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.DealDto;
import com.masterpiece.stockmarketsimulator.dtos.DealUpdateDto;
import com.masterpiece.stockmarketsimulator.dtos.DealViewDto;
import com.masterpiece.stockmarketsimulator.entities.Deal;
import com.masterpiece.stockmarketsimulator.entities.Wallet;
import com.masterpiece.stockmarketsimulator.repositories.DealRepository;
import com.masterpiece.stockmarketsimulator.repositories.WalletRepository;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class DealServiceImpl extends AbstractService implements DealService {

    private final DealRepository dealRepository;
    private final WalletRepository walletRepository;
    private final CustomUserDetailsServiceImpl customUserDetailsService;

    public DealServiceImpl(DealRepository dealRepository, WalletRepository walletRepository, CustomUserDetailsServiceImpl customUserDetailsService) {
        this.dealRepository = dealRepository;
        this.walletRepository = walletRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public void create(DealDto dto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Deal deal = getModelMapper().map(dto, Deal.class);
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

        LocalDateTime dateTime = LocalDateTime.now();
        deal.setDate(dateTime);
        deal.setUserId(customUserDetailsService.getCurrentUserId());
        Wallet wallet = walletRepository.getOne(dto.getWalletId());
        deal.setWallet(wallet);
        double newCapitalAmount = wallet.getCapital() - (dto.getQuantity() * dto.getUnityPrice());
        wallet.setCapital(round(newCapitalAmount, 4));
        dealRepository.save(deal);
        walletRepository.save(wallet);
    }

    @Override
    public Page<DealViewDto> getAllByWalletId(Long walletId, Pageable pageable) {
        return dealRepository.getAllByWalletId(walletId, pageable);
    }

    @Override
    public void update(Long id, DealUpdateDto dealUpdateDto) {
        updateWalletCapital(dealUpdateDto);
        Deal deal = dealRepository.getOne(id);
        if (dealUpdateDto.getQuantity().equals(deal.getQuantity()) || dealUpdateDto.getQuantity() == 0) {
            dealRepository.deleteById(id);
        } else {
            deal.setQuantity(dealUpdateDto.getQuantity());
            dealRepository.save(deal);
        }
    }

    @Override
    public DealViewDto getWalletIdById(Long id) {
        return dealRepository.getWalletIdById(id);
    }

    private void updateWalletCapital(DealUpdateDto dealUpdateDto) {
        Wallet wallet = walletRepository.getOne(dealUpdateDto.getWalletId());
        Deal deal = dealRepository.getOne(dealUpdateDto.getId());
        double currentValue = dealUpdateDto.getUnityPrice() * (deal.getQuantity() - dealUpdateDto.getQuantity());
        double newCapital = wallet.getCapital() + currentValue;
        wallet.setCapital(round(newCapital, 4));
        walletRepository.save(wallet);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public Long getUserIdFromDealList(List<DealViewDto> dealViewDtos) {
        return dealViewDtos.get(0).getUserId();
    }
}
