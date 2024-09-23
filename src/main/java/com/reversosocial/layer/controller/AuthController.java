package com.reversosocial.layer.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {
    @PostMapping("login")
  public String login() {
    return ("Hola soy login");
  }
  @PostMapping("register")
  public String register() {
    return ("Hola soy register");
  }
}
