package com.reversosocial.layer.service;

import com.reversosocial.bean.dto.AuthResponseDto;
import com.reversosocial.bean.dto.RegisterDto;

public interface UserService {
    //AuthResponseDto login(LoginDto request);
    String register(RegisterDto request);
  }
