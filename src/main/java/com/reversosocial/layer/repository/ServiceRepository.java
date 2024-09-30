package com.reversosocial.layer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reversosocial.bean.entity.ServiceBusiness;

@Repository
public interface ServiceRepository extends JpaRepository <ServiceBusiness, Integer> {

    
}
