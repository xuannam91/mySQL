package com.example.md5ss03boot.repository;

import com.example.md5ss03boot.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {
}
