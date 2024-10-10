package com.reversosocial.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.reversosocial.models.dto.ResourceDto;
import com.reversosocial.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/resource")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")

public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<ResourceDto> createResource( @ModelAttribute @Valid ResourceDto resourceDto) {

        ResourceDto createdRoutine = resourceService.createResource(resourceDto);
        return new ResponseEntity<>(createdRoutine, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ResourceDto>> getAllResources() {
        return new ResponseEntity<>(resourceService.getAllResources(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ResourceDto> updateResource(@PathVariable Integer id,  @ModelAttribute @Valid ResourceDto resourceDto) {
        return new ResponseEntity<>(resourceService.updateResource(id, resourceDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> deleteResource(@PathVariable Integer id) {
        return new ResponseEntity<>(resourceService.deleteResource(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ResourceDto> getResourceById(@PathVariable Integer id) {
        return new ResponseEntity<>(resourceService.getResourceById(id), HttpStatus.OK);
    }

}
