package com.masterpiece.stockmarketsimulator.repositories;

import com.masterpiece.stockmarketsimulator.dtos.MemberViewDto;
import com.masterpiece.stockmarketsimulator.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    MemberViewDto getById(Long id);

    List<MemberViewDto> getAllProjectedBy();
}
