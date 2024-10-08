package com.reversosocial.service;

import java.util.List;

import com.reversosocial.models.dto.ResourceDto;

public interface ResourceService {
    ResourceDto createResource(ResourceDto resource);

    List<ResourceDto> getAllResources();
    
    String deleteResource(Integer resourceId);

    ResourceDto updateResource(Integer resourceId, ResourceDto resourceDto);

    ResourceDto getResourceById(Integer resourceId);

}
