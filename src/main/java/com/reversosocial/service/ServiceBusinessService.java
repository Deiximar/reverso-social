package com.reversosocial.service;

import java.util.List;

import com.reversosocial.models.dto.ServiceBusinessDto;

public interface ServiceBusinessService {

ServiceBusinessDto createService(ServiceBusinessDto service);

String deleteService(Integer id);

ServiceBusinessDto updateService(Integer serviceId, ServiceBusinessDto serviceBusinessDto);

List<ServiceBusinessDto> getAllServices();

ServiceBusinessDto getServiceById(Integer serviceId);

List<ServiceBusinessDto> searchServiceByTitle(String title);

}
