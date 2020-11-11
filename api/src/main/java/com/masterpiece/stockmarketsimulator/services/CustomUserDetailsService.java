package com.masterpiece.stockmarketsimulator.services;

import com.masterpiece.stockmarketsimulator.dtos.CustomUserAuthDto;
import com.masterpiece.stockmarketsimulator.dtos.CustomUserInfoDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {

    CustomUserInfoDto getCurrentUserInfo(Long id);

    void create(CustomUserAuthDto customUserAuthDto);
}
