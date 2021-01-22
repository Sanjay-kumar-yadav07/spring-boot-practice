package com.springboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
 
    User findByUsername(String username);
    
    User findByLastName(String lastName);
    
    
    
}
