package com.example.md5ss03boot.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String categoryName;
    private Boolean categoryStatus = true;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category() {
    }

    public Category(Integer id, String categoryName, Boolean categoryStatus, Set<Product> products) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
