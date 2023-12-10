package com.ra.model.service;

import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Boolean create(Product product);
    Product find(Integer productID);
    Boolean update(Product product);
    void delete(Integer productID);
}
