package com.masterpiece.stockmarketsimulator.repositories;

import com.masterpiece.stockmarketsimulator.dtos.WalletViewDto;
import com.masterpiece.stockmarketsimulator.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    WalletViewDto getById(Long id);

    List<WalletViewDto> getAllByCustomUserId(Long id);

    Wallet getOne(Long id);

    Optional<Wallet> findById(Long id);

}
