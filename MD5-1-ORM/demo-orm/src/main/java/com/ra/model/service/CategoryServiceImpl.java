package com.ra.model.service;

import com.ra.model.entity.Category;
import com.ra.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public Boolean create(Category category) {
        return categoryRepository.create(category);
    }

    @Override
    public Category find(Integer categoryID) {
        return categoryRepository.find(categoryID);
    }

    @Override
    public Boolean update(Category category) {
        return categoryRepository.update(category);
    }

    @Override
    public void delete(Integer categoryID) {
        categoryRepository.delete(categoryID);
    }
}
