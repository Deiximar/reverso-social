package com.reversosocial.models.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
  private String accessToken;

  public AuthResponseDto(String accessToken) {
    this.accessToken = accessToken;
  }
}
