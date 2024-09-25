package com.reversosocial.config.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.reversosocial.bean.entity.ERole;
import com.reversosocial.bean.entity.Role;
import com.reversosocial.layer.repository.RoleRepository;

@Component
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
}
