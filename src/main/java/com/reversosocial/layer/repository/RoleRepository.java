package com.reversosocial.layer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reversosocial.bean.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
        Role findByRol(String rol);
}
