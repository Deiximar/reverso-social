package com.reversosocial.layer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reversosocial.bean.entity.ERole;
import com.reversosocial.bean.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
        Optional<Role> findByRole(ERole role);
}
