package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.MemberDto;
import com.masterpiece.stockmarketsimulator.dtos.MemberViewDto;
import com.masterpiece.stockmarketsimulator.entities.Member;
import com.masterpiece.stockmarketsimulator.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void delete(Long id) {
        memberRepo.deleteById(id);
    }

    @Override
    public MemberViewDto getOne(Long id) {
        return memberRepo.getById(id);
    }

    @Override
    public List<MemberViewDto> getAll() {
        return memberRepo.getAllProjectedBy();
    }

    @Override
    public void update(Long id, MemberDto dto) {
    Member member = memberRepo.findById(id).get();
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
