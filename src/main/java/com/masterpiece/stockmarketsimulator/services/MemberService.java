package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.MemberDto;
import com.masterpiece.stockmarketsimulator.dtos.MemberViewDto;

import java.util.List;

public interface MemberService {

    void create(MemberDto dto);

    void delete(Long id);

    MemberViewDto getOne(Long id);

    List<MemberViewDto> getAll();

    void update(Long id, MemberDto dto);
}
