package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.MemberDto;
import com.masterpiece.stockmarketsimulator.dtos.MemberViewDto;


public interface MemberService {

    void create(MemberDto dto);
    MemberViewDto getOne(Long id);


}
