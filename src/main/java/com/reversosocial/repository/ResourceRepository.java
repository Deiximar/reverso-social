package com.reversosocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.reversosocial.models.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
    List<Resource> findByTitleContainingIgnoreCase(String title);
}
