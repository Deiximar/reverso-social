package com.reversosocial.config.data;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reversosocial.bean.entity.ERole;
import com.reversosocial.bean.entity.Role;
import com.reversosocial.bean.entity.Sector;
import com.reversosocial.layer.repository.RoleRepository;
import com.reversosocial.layer.repository.SectorRepository;

@Configuration
public class DataLoader implements CommandLineRunner {

  private RoleRepository roleRepository;

  public DataLoader(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    if (roleRepository.findByRole(ERole.ROLE_ADMIN).isEmpty()) {
      Role adminRole = new Role();
      adminRole.setRole(ERole.ROLE_ADMIN);
      roleRepository.save(adminRole);
    }

    if (roleRepository.findByRole(ERole.ROLE_USER).isEmpty()) {
      Role userRole = new Role();
      userRole.setRole(ERole.ROLE_USER);
      roleRepository.save(userRole);
    }

    if (roleRepository.findByRole(ERole.ROLE_FEMSENIOR).isEmpty()) {
      Role femseniorRole = new Role();
      femseniorRole.setRole(ERole.ROLE_FEMSENIOR);
      roleRepository.save(femseniorRole);
    }
  }

  @Bean
  CommandLineRunner initDatabase(SectorRepository repository) {
    return args -> {
      insertSectorIfNotExists(repository, "Tecnología");
      insertSectorIfNotExists(repository, "Administración y Finanzas");
      insertSectorIfNotExists(repository, "Dirección y Ejecución");
      insertSectorIfNotExists(repository, "Psicología");
      insertSectorIfNotExists(repository, "Terapias Alternativas y Desarrollo Personal");
      insertSectorIfNotExists(repository, "Atención y Cuidados");
      insertSectorIfNotExists(repository, "Atención al Cliente y Servicios");
      insertSectorIfNotExists(repository, "Hostelería y Turismo");
      insertSectorIfNotExists(repository, "Artes y Creatividades");
      insertSectorIfNotExists(repository, "Servicios a la Comunidad");
    };
  }

  private void insertSectorIfNotExists(SectorRepository repository, String sectorName) {
    Optional<Sector> sector = repository.findBySector(sectorName);
    if (!sector.isPresent()) {
      Sector newSector = new Sector();
      newSector.setSector(sectorName);
      repository.save(newSector);
    }
  }
}
