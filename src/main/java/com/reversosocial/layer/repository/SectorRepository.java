package com.reversosocial.layer.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.reversosocial.bean.entity.Sector;

public interface SectorRepository extends JpaRepository<Sector, Integer> {
  Optional<Sector> findBySector(String sector);
}
