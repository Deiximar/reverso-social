package com.reversosocial.layer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reversosocial.bean.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    
        Roles findByRol(String rol);
    
}
