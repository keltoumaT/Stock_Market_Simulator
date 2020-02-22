package com.masterpiece.stockmarketsimulator.repositories;

import com.masterpiece.stockmarketsimulator.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
