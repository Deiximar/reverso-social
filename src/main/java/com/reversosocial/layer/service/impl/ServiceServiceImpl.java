package com.reversosocial.layer.service.impl;

import org.modelmapper.ModelMapper;

import com.reversosocial.bean.dto.ServiceDto;
import com.reversosocial.bean.entity.Sector;
import com.reversosocial.bean.entity.ServiceBusiness;
import com.reversosocial.bean.entity.User;
import com.reversosocial.config.exception.ResourceNotFountException;
import com.reversosocial.config.exception.UsernameNotFoundException;
import com.reversosocial.layer.repository.SectorRepository;
import com.reversosocial.layer.repository.ServiceRepository;
import com.reversosocial.layer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final SectorRepository sectorRepository;
    private final ModelMapper modelMapper;

    @Override
    public ServiceDto createService(ServiceDto serviceDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));
        Sector sector = sectorRepository.findBySector(serviceDto.getSector())
                .orElseThrow(() -> new ResourceNotFountException("Sector no encontrado"));
        ServiceBusiness serviceBusiness = modelMapper.map(serviceDto, ServiceBusiness.class);
        serviceBusiness.setUser(user);
        serviceBusiness.setSector(sector);
        ServiceBusiness savedService = serviceRepository.save(serviceBusiness);
        return modelMapper.map(savedService, ServiceDto.class);
    }
}