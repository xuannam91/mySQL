package com.ra.dto;

public class ProductDTO {

    private String productName;
    private Double price;

    private Integer categoryId;

    public ProductDTO() {
    }

    public ProductDTO(String productName, Double price, Integer categoryId) {
        this.productName = productName;
        this.price = price;
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
