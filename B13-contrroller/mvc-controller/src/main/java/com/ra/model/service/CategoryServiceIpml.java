package com.ra.model.service;

import com.ra.model.dao.CategoryDAO;
import com.ra.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceIpml implements CategoryService{
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Boolean create(Category category) {
        return categoryDAO.create(category);
    }

    @Override
    public Boolean update(Category category, Integer integer) {
        return categoryDAO.update(category,integer);
    }

    @Override
    public Category findById(Integer integer) {
        return categoryDAO.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        categoryDAO.delete(integer);
    }
}
