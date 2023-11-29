package com.ra.model.entity;

public class Product {
    private Integer id;
    private String nameProduct;
    private Double price;
    private String image;
    private Category category;


    public Product() {
    }

    public Product(Integer id, String nameProduct, Double price, String image, Category category) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
