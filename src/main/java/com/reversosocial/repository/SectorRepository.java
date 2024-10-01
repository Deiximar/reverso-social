package com.reversosocial.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.reversosocial.models.entity.Sector;

public interface SectorRepository extends JpaRepository<Sector, Integer> {
  Optional<Sector> findBySector(String sector);
}
