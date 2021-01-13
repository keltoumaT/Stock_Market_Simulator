package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.config.CustomUserDetails;
import com.masterpiece.stockmarketsimulator.config.ResourceNotFoundException;
import com.masterpiece.stockmarketsimulator.config.SecurityHelper;
import com.masterpiece.stockmarketsimulator.dtos.CustomUserAuthDto;
import com.masterpiece.stockmarketsimulator.dtos.CustomUserInfoDto;
import com.masterpiece.stockmarketsimulator.entities.CustomUser;
import com.masterpiece.stockmarketsimulator.repositories.CustomUserJpaRepository;
import com.masterpiece.stockmarketsimulator.repositories.RoleRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService{

    private final CustomUserJpaRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    protected CustomUserDetailsServiceImpl(CustomUserJpaRepository repo, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    // Throws UsernameNotFoundException (Spring contract)
    @Override
    public CustomUserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        CustomUserAuthDto user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "no user found with username: " + username));
        return new CustomUserDetails(user);
    }

    // Throws ResourceNotFoundException (restful practice)
    @Override
    public CustomUserInfoDto getCurrentUserInfo(Long id) {
        return repo.getById(id).orElseThrow(
                () -> new ResourceNotFoundException("with id:" + id));
    }

    @Override
    public void create(CustomUserAuthDto customUserAuthDto) {
        CustomUser customUser = new CustomUser();
    }

    public Long getCurrentUserId(){
       return SecurityHelper.getUserId();
    }
}
