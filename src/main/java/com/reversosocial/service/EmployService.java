package com.reversosocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.reversosocial.models.dto.EmployDto;
import com.reversosocial.models.entity.Employ;
import com.reversosocial.models.entity.Sector;
import com.reversosocial.repository.EmployRepository;
import com.reversosocial.repository.SectorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployService {
    
    @Autowired
    private EmployRepository employRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public List<EmployDto> getAllEmploys() {
        List<Employ> employs = employRepository.findAll();
        return employs.stream().map(employ -> 
            new EmployDto(
                employ.getPosition(), 
                employ.getCvUrl(), 
                employ.getDescription(), 
                employ.getSector().getId()
            )
        ).collect(Collectors.toList());
    }

    public EmployDto getEmployById(Long id) {
        Employ employ = employRepository.findById(id).orElse(null);
        if (employ != null) {
            
            int sectorId = employ.getSector().getId();
            return new EmployDto(
                employ.getPosition(), 
                employ.getCvUrl(), 
                employ.getDescription(), 
                sectorId
            );
        } else {
            return null;
        }
    }

    public EmployDto createEmployOffer(String position, String description, MultipartFile cvFile, int sectorId) {
        String cvUrl = fileStorageService.storeFile(cvFile);

        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new RuntimeException("Sector no encontrado"));

        Employ employ = new Employ();
        employ.setPosition(position);
        employ.setDescription(description);
        employ.setCvUrl(cvUrl);
        employ.setSector(sector);

        Employ savedEmploy = employRepository.save(employ);

        return new EmployDto(savedEmploy.getPosition(), savedEmploy.getCvUrl(), savedEmploy.getDescription(), savedEmploy.getSector().getId());
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
