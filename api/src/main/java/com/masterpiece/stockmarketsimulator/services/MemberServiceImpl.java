package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.MemberDto;
import com.masterpiece.stockmarketsimulator.dtos.MemberViewDto;
import com.masterpiece.stockmarketsimulator.entities.Member;
import com.masterpiece.stockmarketsimulator.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepo;

    public MemberServiceImpl(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }

    @Override
    public void create(MemberDto dto) {
    Member member = new Member();
    populateAndSave(dto, member);
    }



    private void populateAndSave(MemberDto dto, Member member){
        member.setLastName(dto.getLastName());
        member.setFirstName(dto.getFirstName());
        member.setEmail(dto.getEmail());
        member.setPassword(dto.getPassword());
        memberRepo.save(member);
    }
}
