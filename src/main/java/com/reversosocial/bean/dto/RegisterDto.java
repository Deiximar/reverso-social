package com.reversosocial.bean.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reversosocial.bean.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterDto {

    @NotNull(message = "El campo Nombre de Usuario es requerido.")
    @NotBlank(message = "El campo Nombre de Usuario es requerido.")
    private String username;

    @NotNull(message = "El campo Nombre es requerido.")
    @NotBlank(message = "El campo Nombre es requerido.")
    private String name;

    @NotNull(message = "El campo Apellido es requerido.")
    @NotBlank(message = "El campo Apellido es requerido.")

    private String lastname;

    @NotNull(message = "El campo Fecha de nacimiento es requerido.")
    @Past(message = "La fecha debe ser anterior a la fecha actual.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull(message = "El campo Contraseña es requerido.")
    @NotBlank(message = "El campo Contraseña es requerido.")
    @Size(min = 8)
    private String password;

    @NotNull(message = "El campo Correo Electrónico es requerido.")
    @NotBlank(message = "El campo Correo Electrónico es requerido.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Correo electrónico inválido. El formato debe ser example@gmail.com")
    private String email;

    private Role role;
}
