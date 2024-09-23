package com.reversosocial.layer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reversosocial.bean.entity.Users;



public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);;

    

   
}
