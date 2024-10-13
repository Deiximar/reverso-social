package com.reversosocial.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reversosocial.models.entity.User;
import com.reversosocial.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("El usuario: " + username + " no existe."));

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    authorities.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRole().getRole().name())));
    user.getRole().getPermissionList().stream()
        .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getPermission().name())));

    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .authorities(authorities)
        .build();
  }
}
