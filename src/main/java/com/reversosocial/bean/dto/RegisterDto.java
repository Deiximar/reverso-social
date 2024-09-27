package com.reversosocial.bean.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reversosocial.bean.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterDto {

    @NotNull(message = "El campo nombre de usuario es requerido.")
    @NotBlank(message = "El campo nombre de usuario es requerido.")
    private String username;

    @NotNull(message = "El campo nombre es requerido.")
    @NotBlank(message = "El campo nombre es requerido.")
    private String name;

    @NotNull(message = "El campo apellido es requerido.")
    @NotBlank(message = "El campo apellido es requerido.")
    private String lastname;

    @NotNull(message = "El campo fecha de nacimiento es requerido.")
    @Past(message = "La fecha debe ser anterior a la fecha actual.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Size(min = 8, message = "El campo contraseña debe contener mínimo 8 caracteres.")
    @NotNull(message = "El campo contraseña es requerido.")
    @NotBlank(message = "El campo contraseña es requerido.")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email inválido. El formato debe ser example@gmail.com")
    @NotNull(message = "El campo email es requerido.")
    @NotBlank(message = "El campo email es requerido.")
    private String email;

    private Role role;
}
