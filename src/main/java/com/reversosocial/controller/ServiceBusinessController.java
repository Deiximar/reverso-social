package com.reversosocial.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reversosocial.models.dto.ServiceBusinessDto;
import com.reversosocial.service.ServiceBusinessService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/services/")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class ServiceBusinessController {
    private final ServiceBusinessService serviceBusinessService;

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<ServiceBusinessDto> createService(@RequestBody @Valid ServiceBusinessDto serviceBusinessDto) {
        ServiceBusinessDto addService = serviceBusinessService.createService(serviceBusinessDto);
        return new ResponseEntity<ServiceBusinessDto>(addService, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> deleteService(@PathVariable Integer id) {
        serviceBusinessService.deleteService(id);
        return ResponseEntity.ok("El servicio ha sido eliminado correctamente.");
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ServiceBusinessDto> updateService(@PathVariable Integer id, @RequestBody ServiceBusinessDto serviceBusinessDto) {
        return new ResponseEntity<>(serviceBusinessService.updateService(id, serviceBusinessDto), HttpStatus.OK);
    }
    
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ServiceBusinessDto>> getAllServices() {
    return new ResponseEntity<>(serviceBusinessService.getAllServices(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ServiceBusinessDto> getServiceById(@PathVariable Integer id) {
        return new ResponseEntity<>(serviceBusinessService.getServiceById(id), HttpStatus.OK);

    }
}
