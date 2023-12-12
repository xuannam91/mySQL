package com.ra.dto;

public class ReponProductDTO {
    private Integer id;
    private String productName;
    private Double price;

    private Integer categoryId;

    public ReponProductDTO() {
    }

    public ReponProductDTO(Integer id, String productName, Double price, Integer categoryId) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
