package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.MemberDto;
import com.masterpiece.stockmarketsimulator.dtos.MemberViewDto;
import com.masterpiece.stockmarketsimulator.entities.CustomUser;
import com.masterpiece.stockmarketsimulator.entities.Role;
import com.masterpiece.stockmarketsimulator.repositories.CustomUserJpaRepository;
import com.masterpiece.stockmarketsimulator.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService {


    private final CustomUserJpaRepository customUserJpaRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public MemberServiceImpl(CustomUserJpaRepository memberRepo, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.customUserJpaRepository = memberRepo;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public MemberViewDto getOne(Long id) {
        return null;
    }



    @Override
    public void create(MemberDto dto) {
    CustomUser customUser = new CustomUser();
    populateAndSave(dto, customUser);
    }


    private void populateAndSave(MemberDto dto, CustomUser customUser){
       customUser.setPassword(passwordEncoder.encode(dto.getPassword()));
       customUser.setAccountNonExpired(true);
       customUser.setAccountNonLocked(true);
       customUser.setCredentialsNonExpired(true);
       customUser.setEnabled(true);
       customUser.setFirstname(dto.getFirstName());
       customUser.setLastname(dto.getLastName());
       customUser.setUsername(dto.getUsername());
       customUser.setEmail(dto.getEmail());
       Set<Role>roles = new HashSet<>();
       roles.add(roleRepository.findByDefaultRoleTrue());
       customUser.setRoles(roles);
       customUserJpaRepository.save(customUser);
    }
}
