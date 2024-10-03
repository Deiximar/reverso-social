package com.reversosocial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reversosocial.models.entity.ERole;
import com.reversosocial.models.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
        Optional<Role> findByRole(ERole role);

        Role findRoleByUser(String email);
}
