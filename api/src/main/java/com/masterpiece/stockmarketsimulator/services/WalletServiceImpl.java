package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.WalletDto;
import com.masterpiece.stockmarketsimulator.dtos.WalletViewDto;
import com.masterpiece.stockmarketsimulator.entities.Member;
import com.masterpiece.stockmarketsimulator.entities.Wallet;
import com.masterpiece.stockmarketsimulator.repositories.DealRepository;
import com.masterpiece.stockmarketsimulator.repositories.MemberRepository;
import com.masterpiece.stockmarketsimulator.repositories.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final MemberRepository memberRepository;
    private final DealRepository dealRepository;

    public WalletServiceImpl(WalletRepository walletRepository, MemberRepository memberRepository, DealRepository dealRepository){
        this.walletRepository = walletRepository;
        this.memberRepository = memberRepository;
        this.dealRepository = dealRepository;
    }
    @Override
    public void create(WalletDto dto) {
    Wallet wallet = new Wallet();
    populateAndSave(dto, wallet);
    }

    @Override
    public WalletViewDto getOne(Long id) {
        return walletRepository.getById(id);
    }

    @Override
    public List<WalletViewDto> getAll(Long id) {
        return walletRepository.getAllByMemberId(id);
    }

    @Override
    public void update(Long id, WalletDto dto) {
        Wallet wallet = walletRepository.findById(id).get();
        populateAndSave(dto, wallet);
    }


    private void populateAndSave(WalletDto dto, Wallet wallet){
        wallet.setCapital(dto.getCapital());
        wallet.setMemo(dto.getMemo());
        wallet.setName(dto.getName());
        Member member = memberRepository.getOne(dto.getMemberId());
        wallet.setMember(member);
        walletRepository.save(wallet);
    }



}
