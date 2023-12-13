package com.ra.repository;

import com.ra.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CategoryRepository extends CrudRepository<Category,Integer> {
    List<Category> findByCategoryNameContaining(String name);
}
