package com.masterpiece.stockmarketsimulator.repositories;


import com.masterpiece.stockmarketsimulator.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByDefaultRoleTrue();
}
