package com.reversosocial.config.admin;

import org.springframework.stereotype.Component;
import com.reversosocial.models.entity.Role;
import com.reversosocial.models.entity.User;
import com.reversosocial.models.entity.ERole;
import com.reversosocial.repository.UserRepository;
import com.reversosocial.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Component
public class AdminInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Optional<Role> adminRole = roleRepository.findByRole(ERole.ADMIN);

        if (adminRole.isPresent()) {
            if (userRepository.findByEmail("pilar.limon.fc@gmail.com").isEmpty()) {
                User adminReverso = User.builder()
                        .name("AdminReverso")
                        .email("pilar.limon.fc@gmail.com")
                        .password(passwordEncoder.encode("Sintonías2024"))
                        .role(adminRole.get())
                        .build();

                userRepository.save(adminReverso);
                System.out.println("Administrador creado: pilar.limon.fc@gmail.com / ****");
            }

            if (userRepository.findByEmail("lola.martinez@factoriaf5.org").isEmpty()) {
                User adminFemsenior = User.builder()
                        .name("AdminFemsenior")
                        .email("lola.martinez@factoriaf5.org")
                        .password(passwordEncoder.encode("Sintonías2024"))
                        .role(adminRole.get())
                        .build();

                userRepository.save(adminFemsenior);
                System.out.println("Administrador creado: lola.martinez@factoriaf5.org / ****");
            }
        } else {
            System.out.println("Error: El rol ADMIN no existe en la base de datos.");
        }
    }
}
