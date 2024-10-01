package com.reversosocial.layer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reversosocial.bean.dto.AuthResponseDto;
import com.reversosocial.bean.dto.LoginDto;
import com.reversosocial.bean.dto.RegisterDto;
import com.reversosocial.layer.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class AuthController {

  private final UserService userService;

  @PostMapping("login")
  @PreAuthorize("permitAll()")
  public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid LoginDto loginRequest) {
    AuthResponseDto response = userService.login(loginRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("register")
  @PreAuthorize("permitAll()")
  public ResponseEntity<String> register(@RequestBody @Valid RegisterDto request) {
    String response = userService.register(request);
    return ResponseEntity.ok(response);
  }
}
