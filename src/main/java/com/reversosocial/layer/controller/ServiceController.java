package com.reversosocial.layer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reversosocial.bean.dto.ServiceDto;
import com.reversosocial.layer.service.impl.ServiceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
    @RequestMapping("/api/services/")
    @RequiredArgsConstructor
    public class ServiceController {
    private final ServiceService serviceService; 

    @PostMapping
    public ResponseEntity<ServiceDto> createService (@RequestBody @Valid ServiceDto serviceDto) {
   ServiceDto addService = serviceService.createService(serviceDto);
   return new ResponseEntity<ServiceDto>(addService, HttpStatus.CREATED);
    
}
}
