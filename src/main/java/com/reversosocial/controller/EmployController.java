package com.reversosocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reversosocial.models.entity.Employ;
import com.reversosocial.service.EmployService;

@RestController
@RequestMapping("/api/employs")
public class EmployController {
    
    @Autowired
    private EmployService employService;

    @GetMapping
    public List<Employ> getAllEmploys() {
        return employService.getAllEmploys();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employ> getEmployById(@PathVariable Long id) {
        Employ employ = employService.getEmployById(id);
        if (employ != null) {
            return ResponseEntity.ok(employ);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Employ creatEmployOffer(@RequestBody Employ employ) {
        return employService.createEmployOffer(employ);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employ> updateEmploy(@PathVariable Long id, @RequestBody Employ employDetails) {
        Employ employ = employService.updateEmployOffer(id, employDetails);
        return ResponseEntity.ok(employ);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployOffer(@PathVariable Long id) {
        employService.deleteEmployOffer(id);
        return ResponseEntity.noContent().build();
    }    
}
