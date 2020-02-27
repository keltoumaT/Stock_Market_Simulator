package com.masterpiece.stockmarketsimulator.repositories;

import com.masterpiece.stockmarketsimulator.dtos.DealViewDto;
import com.masterpiece.stockmarketsimulator.entities.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

    List<DealViewDto> getAllByWalletId(Long id);
}
