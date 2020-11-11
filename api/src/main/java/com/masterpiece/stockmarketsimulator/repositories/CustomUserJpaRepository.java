package com.masterpiece.stockmarketsimulator.repositories;

import com.masterpiece.stockmarketsimulator.dtos.CustomUserAuthDto;
import com.masterpiece.stockmarketsimulator.dtos.CustomUserInfoDto;
import com.masterpiece.stockmarketsimulator.entities.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomUserJpaRepository extends JpaRepository<CustomUser, Long> {

//    MemberViewDto getById(Long id);

    Optional<CustomUserAuthDto> findByUsername(String username);


    Optional<CustomUserInfoDto> getById(Long id);
}
