package com.reversosocial.layer.service.impl;

import com.reversosocial.bean.dto.ServiceBusinessDto;


public interface ServiceBusinessService {

ServiceBusinessDto createService(ServiceBusinessDto service);
void deleteService(int id);
    
}
