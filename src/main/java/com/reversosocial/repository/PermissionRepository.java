package com.reversosocial.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.reversosocial.models.entity.EPermission;
import com.reversosocial.models.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Optional<Permission> findByPermission(EPermission permission);
}
