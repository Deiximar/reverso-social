package com.reversosocial.layer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reversosocial.bean.entity.Roles;
import com.reversosocial.config.exception.ExceptionType;
import com.reversosocial.config.exception.RevSocException;
import com.reversosocial.layer.repository.RolesRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/rol")
public class RolesController {

    @Autowired
    private RolesRepository rolesRepository;
    
    @PostMapping("/create")
    public ResponseEntity<Roles> createRol(@Valid @RequestBody Roles roles) {
if(rolesRepository.findByRol(roles.getRol()) != null){
    throw new RevSocException(ExceptionType.VALIDATION, "El rol ya existe");
}
        Roles saveRol = rolesRepository.save(roles);
        return new ResponseEntity<>(saveRol, HttpStatus.CREATED);
    }
    
}
