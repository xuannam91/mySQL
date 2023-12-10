package com.ra.model.service;

import com.ra.model.entity.Product;
import com.ra.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Boolean create(Product product) {
        return productRepository.create(product);
    }

    @Override
    public Product find(Integer productID) {
        return productRepository.find(productID);
    }

    @Override
    public Boolean update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public void delete(Integer productID) {
        productRepository.delete(productID);
    }
}
