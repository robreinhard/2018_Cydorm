package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.CyDormUser;

@Repository
public interface RepositoryForUser extends CrudRepository<CyDormUser, Integer>{

}
