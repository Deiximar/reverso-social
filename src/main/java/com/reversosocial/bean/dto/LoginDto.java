package com.reversosocial.bean.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDto {

  @NotNull(message = "El campo Correo Electrónico es requerido.")
  @NotBlank(message = "El campo Correo Electrónico es requerido.")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Correo electrónico inválido. El formato debe ser example@gmail.com")
  private String email;

  @NotNull(message = "El campo Contraseña es requerido.")
  @NotBlank(message = "El campo Contraseña es requerido.")
  private String password;
}