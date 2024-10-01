package com.reversosocial.layer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reversosocial.bean.dto.ServiceBusinessDto;
import com.reversosocial.layer.service.impl.ServiceBusinessService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
    @RequestMapping("/api/services/")
    @RequiredArgsConstructor
    public class ServiceBusinessController {
    private final ServiceBusinessService serviceBusinessService; 
   

    @PostMapping
    public ResponseEntity<ServiceBusinessDto> createService (@RequestBody @Valid ServiceBusinessDto serviceBusinessDto) {
   ServiceBusinessDto addService = serviceBusinessService.createService(serviceBusinessDto);
   return new ResponseEntity<ServiceBusinessDto>(addService, HttpStatus.CREATED);
    
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deleteService(@PathVariable int id) {
    serviceBusinessService.deleteService(id);
    return ResponseEntity.ok("El servicio ha sido eliminado correctamente.");
}

}
