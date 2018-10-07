package com.example.demo;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Grocery;

@Repository
public interface GroceryInterface extends CrudRepository<Grocery, Integer> {

	Collection<Grocery> findAll();

	Collection<Grocery> findById(@Param("id") int id);

	Grocery save(Grocery groceryItem);

}
