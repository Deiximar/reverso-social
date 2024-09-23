package com.reversosocial.layer.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reversosocial.bean.dto.AuthResponseDto;
import com.reversosocial.bean.dto.RegisterDto;
import com.reversosocial.layer.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  // @PostMapping("login")
  // public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginRequest) {
  //   AuthResponseDto response = userService.login(loginRequest);
  //   return new ResponseEntity<>(response, HttpStatus.OK);
  // }
  @PostMapping("register")
  public ResponseEntity<String> register(@RequestBody RegisterDto request) {
    String response = userService.register(request);
    return ResponseEntity.ok(response);
  }
}
