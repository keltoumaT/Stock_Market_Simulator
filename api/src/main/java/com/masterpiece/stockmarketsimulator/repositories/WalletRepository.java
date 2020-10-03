package com.masterpiece.stockmarketsimulator.repositories;

import com.masterpiece.stockmarketsimulator.dtos.WalletViewDto;
import com.masterpiece.stockmarketsimulator.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    WalletViewDto getById(Long id);

    List<WalletViewDto> getAllByMemberId(Long id);

    Wallet getOne(Long id);

}
