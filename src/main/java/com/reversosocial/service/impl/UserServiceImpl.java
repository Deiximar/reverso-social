package com.reversosocial.service.impl;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reversosocial.config.exception.ExistingEmailException;
import com.reversosocial.config.exception.ExistingUsernameException;
import com.reversosocial.config.exception.InvalidCredentialsException;
import com.reversosocial.config.exception.UsernameNotFoundException;
import com.reversosocial.config.security.jwt.JWTAuthenticationConfig;
import com.reversosocial.models.dto.AuthResponseDto;
import com.reversosocial.models.dto.LoginDto;
import com.reversosocial.models.dto.RegisterDto;
import com.reversosocial.models.entity.ERole;
import com.reversosocial.models.entity.Role;
import com.reversosocial.models.entity.User;
import com.reversosocial.repository.RoleRepository;
import com.reversosocial.repository.UserRepository;
import com.reversosocial.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JWTAuthenticationConfig jwtAuthenticationConfig;
  private final AuthenticationManager authenticationManager;

  @Override
  public AuthResponseDto login(LoginDto request) {
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException(
            "La contraseña o el correo electronico es incorrecta"));
    try {
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
              request.getPassword()));
      String token = jwtAuthenticationConfig.getJWToken(user.getEmail(), user.getRole());
      return new AuthResponseDto(token);
    } catch (BadCredentialsException e) {
      throw new InvalidCredentialsException("La contraseña o el correo electronico es incorrecta");
    } catch (AuthenticationException e) {
      throw new RuntimeException("Error durante la autenticación: " + e.getMessage());
    }

  }

  @Override
  public String register(RegisterDto request) {
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      throw new ExistingEmailException("Este correo electronico ya esta en uso.");
    }
    if (userRepository.findByUsername(request.getUsername()).isPresent()) {
      throw new ExistingUsernameException("Este nombre de usuario ya esta en uso.");
    }

    User user = new User();
    user.setName(request.getName());
    user.setLastname(request.getLastname());
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setBirthday(request.getBirthday());
    user.setPassword(passwordEncoder.encode(request.getPassword()));

    int age = Period.between(request.getBirthday(), LocalDate.now()).getYears();

    if (age > 50) {
      Role femseniorRole = roleRepository.findByRole(ERole.FEMSENIOR)
          .orElseThrow(() -> new RuntimeException("Error: Rol FEMSENIOR no encontrado."));

      user.setRole(femseniorRole);
    } else {
      Role userRole = roleRepository.findByRole(ERole.USER)
          .orElseThrow(() -> new RuntimeException("Error: Rol USER no encontrado."));

      user.setRole(userRole);
    }

    userRepository.save(user);
    return "Usuario registrado exitosamente";
  }
}