package com.reversosocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reversosocial.models.entity.Employ;
import com.reversosocial.repository.EmployRepository;

import java.util.List;

@Service
public class EmployService {
    
    @Autowired
    private EmployRepository employRepository;

    public List<Employ> getAllEmploys() {
        return employRepository.findAll();
    }

    public Employ getEmployById(Long id) {
        return employRepository.findById(id).orElse(null);
    }

    public Employ createEmployOffer(Employ employ) {
        return employRepository.save(employ);
    }

    public Employ updateEmployOffer(Long id, Employ employDetails) {
        Employ employ = employRepository.findById(id).orElse(null);
        if (employ != null) {
            employ.setPosition(employDetails.getPosition());
            employ.setCvUrl(employDetails.getCvUrl());
            employ.setDescription(employDetails.getDescription());
            employ.setUser(employDetails.getUser());
            employ.setSector(employDetails.getSector());

            return employRepository.save(employ);
        }
        return null;
    }

    public void deleteEmployOffer(Long id) {
        employRepository.deleteById(id);
    }
}
