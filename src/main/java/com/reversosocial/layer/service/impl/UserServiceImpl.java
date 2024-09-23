package com.reversosocial.layer.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reversosocial.bean.dto.AuthResponseDto;
import com.reversosocial.bean.dto.RegisterDto;
import com.reversosocial.bean.entity.User;
import com.reversosocial.config.exception.ExistingEmailException;
import com.reversosocial.config.security.jwt.JWTAuthenticationConfig;
import com.reversosocial.layer.repository.UserRepository;
import com.reversosocial.layer.service.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JWTAuthenticationConfig jwtAuthenticationConfig;
  private final AuthenticationManager authenticationManager;

//   @Override
//   public AuthResponseDto login(LoginDto request) {
//     UserEntity user = userRepository.findByEmail(request.getEmail())
//         .orElseThrow(() -> new UsernameNotFoundException(
//             "Invalid email or password."));
//     authenticationManager
//         .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//     String token = jwtUtil.generateToken(user.getEmail());
//     return new AuthResponseDto(token);
//   }

  @Override
  public String register(RegisterDto request) {
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      throw new ExistingEmailException("This email is already taken");
    }

    User user = new User();
    user.setName(request.getName());
    user.setLastname(request.getLastname());
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setBirthday(request.getBirthday());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    userRepository.save(user);
        return ("Usuario registrado exitosamente");
  }
}

