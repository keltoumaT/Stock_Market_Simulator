package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.WalletDto;
import com.masterpiece.stockmarketsimulator.entities.Member;
import com.masterpiece.stockmarketsimulator.entities.Wallet;
import com.masterpiece.stockmarketsimulator.repositories.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository){
        this.walletRepository = walletRepository;
    }
    @Override
    public void create(WalletDto dto) {
    Wallet wallet = new Wallet();
    populateAndSave(dto, wallet);
    }

    private void populateAndSave(WalletDto dto, Wallet wallet){
        wallet.setInitialCapital(dto.getInitialCapital());
        wallet.setMemo(dto.getMemo());
        wallet.setName(dto.getName());
        Member member = new Member();
        wallet.setMember(member);
        walletRepository.save(wallet);

    }
}
