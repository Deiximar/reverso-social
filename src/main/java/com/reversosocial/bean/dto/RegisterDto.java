package com.reversosocial.bean.dto;

import java.time.LocalDate;

import com.reversosocial.bean.entity.Role;

import lombok.Data;

@Data
public class RegisterDto {
     private String username;
    private String name;
    private String lastname;
    private LocalDate birthday;
    private String password;
    private String email;
    private Role role;
}
