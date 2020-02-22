package com.masterpiece.stockmarketsimulator.repositories;

import com.masterpiece.stockmarketsimulator.entities.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
}
