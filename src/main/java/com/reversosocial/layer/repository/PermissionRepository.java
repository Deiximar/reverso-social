package com.reversosocial.layer.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.reversosocial.bean.entity.EPermission;
import com.reversosocial.bean.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Optional<Permission> findByPermission(EPermission permission);
}

