package com.reversosocial.service;

import com.reversosocial.models.dto.AuthResponseDto;
import com.reversosocial.models.dto.LoginDto;
import com.reversosocial.models.dto.RegisterDto;

public interface UserService {
  AuthResponseDto login(LoginDto request);

  String register(RegisterDto request);
}
