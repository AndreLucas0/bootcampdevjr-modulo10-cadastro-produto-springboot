package com.andre.dto;

import com.andre.product_backend.models.Category;
import com.andre.product_backend.models.Product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductRequest {

    @Size(min = 3, max = 255, message = "Name length min=3 and max=255")
    private String name;

    @NotBlank(message = "Name can not be blank.")
    @Size(min = 3, max = 1024, message = "Name length min=3 and max=1024")
    private String description;

    private boolean promotion;

    private boolean newProduct;

    @Min(value = 0, message = "Price min value=0")
    private Double price;

    @Valid
    private Category category;

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

    public Product toEntity() {
        return new Product(this.name, this.description, this.category, this.promotion, this.newProduct, this.price);
    }

}
