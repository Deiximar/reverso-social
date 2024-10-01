package com.reversosocial.service;

import com.reversosocial.models.dto.ServiceBusinessDto;

public interface ServiceBusinessService {

  ServiceBusinessDto createService(ServiceBusinessDto service);

  void deleteService(int id);

}
