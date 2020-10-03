package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.MemberDto;
import com.masterpiece.stockmarketsimulator.dtos.MemberViewDto;
import com.masterpiece.stockmarketsimulator.entities.Member;
import com.masterpiece.stockmarketsimulator.entities.Role;
import com.masterpiece.stockmarketsimulator.repositories.MemberRepository;
import com.masterpiece.stockmarketsimulator.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepo;
    private final RoleRepository roleRepository;


    public MemberServiceImpl(MemberRepository memberRepo, RoleRepository roleRepository) {
        this.memberRepo = memberRepo;
        this.roleRepository = roleRepository;
    }

    @Override
    public MemberViewDto getOne(Long id) {
        return memberRepo.getById(id);
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
        member.setEnabled(true);
        Role role = roleRepository.findByDefaultRoleTrue();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        member.setRoles(roles);
        memberRepo.save(member);

    }
}
