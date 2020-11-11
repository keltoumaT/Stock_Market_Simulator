package com.masterpiece.stockmarketsimulator.dtos;

import com.masterpiece.stockmarketsimulator.entities.Role;

import java.util.Set;

public interface CustomUserAuthDto {


    Long getId();

    String getUsername();

    String getPassword();

    Set<Role> getRoles();

    boolean isEnabled();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();
}
