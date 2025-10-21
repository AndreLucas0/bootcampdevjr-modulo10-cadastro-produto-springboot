package com.andre.dto;

import com.andre.product_backend.models.Category;

public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private boolean promotion;
    private boolean newProduct;
    private Double price;
    private Category category;

    public ProductResponse(Long id, String name, String description, boolean promotion, boolean newProduct,
            Double price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.promotion = promotion;
        this.newProduct = newProduct;
        this.price = price;
        this.category = category;
    }

    public ProductResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

}
