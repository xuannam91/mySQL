package com.ra.model.service;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Boolean create(Category category);
    Category find(Integer categoryID);
    Boolean update(Category category);
    void delete(Integer categoryID);
}
