package com.andre.product_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.product_backend.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
