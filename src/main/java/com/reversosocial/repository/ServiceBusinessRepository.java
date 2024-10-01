package com.reversosocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reversosocial.models.entity.ServiceBusiness;

@Repository
public interface ServiceBusinessRepository extends JpaRepository<ServiceBusiness, Integer> {

}
