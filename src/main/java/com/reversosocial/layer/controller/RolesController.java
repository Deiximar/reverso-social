package com.reversosocial.layer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reversosocial.bean.entity.Role;
import com.reversosocial.config.exception.ExceptionType;
import com.reversosocial.config.exception.RevSocException;
import com.reversosocial.layer.repository.RoleRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/role")
public class RolesController {

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
        if (roleRepository.findByRole(role.getRole()) != null) {
            throw new RevSocException(ExceptionType.VALIDATION, "El role ya existe");
        }
        Role saveRole = roleRepository.save(role);
        return new ResponseEntity<>(saveRole, HttpStatus.CREATED);
    }

}
