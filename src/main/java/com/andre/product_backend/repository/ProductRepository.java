package com.andre.product_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.product_backend.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    
}
