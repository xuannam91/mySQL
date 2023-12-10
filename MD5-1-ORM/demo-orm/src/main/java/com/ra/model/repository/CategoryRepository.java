package com.ra.model.repository;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAll();
    Boolean create(Category category);
    Category find(Integer categoryID);
    Boolean update(Category category);
    void delete(Integer categoryID);
}
