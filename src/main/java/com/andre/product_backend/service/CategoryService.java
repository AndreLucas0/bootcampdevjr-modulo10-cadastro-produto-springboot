package com.andre.product_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.andre.product_backend.models.Category;
import com.andre.product_backend.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getById(int id) {
        Category category = categoryRepository.findById(id)
                                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found."));
        return category;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        Category categorySaved = categoryRepository.save(category);
        return categorySaved;
    }

    public void deleteById(int id) {
        Category category = this.getById(id);
        categoryRepository.delete(category);
    }

    public void update(int id, Category categoryUpdate) {
        Category category = this.getById(id);
        category.setName(categoryUpdate.getName());
        categoryRepository.save(category);
    }
}
