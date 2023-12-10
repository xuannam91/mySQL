package com.ra.model.repository;

import com.ra.model.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAll();
    Boolean create(Product product);
    Product find(Integer productID);
    Boolean update(Product product);
    void delete(Integer productID);
}
