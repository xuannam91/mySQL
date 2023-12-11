package com.example.md5ss03boot.service;

import com.example.md5ss03boot.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category saveOrUpdate(Category category);
    Category findById(Integer id);
    void delete(Integer id);
}
