package com.andre.product_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.andre.dto.CategoryRequest;
import com.andre.dto.CategoryResponse;
import com.andre.product_backend.models.Category;
import com.andre.product_backend.repository.CategoryRepository;
import com.andre.product_backend.service.exceptions.DataBaseException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse getById(int id) {
        Category category = categoryRepository.findById(id)
                                 .orElseThrow(() -> new EntityNotFoundException("Category not found."));
        return category.toDTO();
    }

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll()
                                    .stream()
                                    .map(c -> c.toDTO())
                                    .collect(Collectors.toList());
    }

    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category categorySaved = categoryRepository.save(categoryRequest.toEntity());
        return categorySaved.toDTO();
    }

    public void deleteById(int id) {
        
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not fond");
        }

        try {
            categoryRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e) {
            throw new DataBaseException("Constraint violation, category can not delete");
        }
    }

    public void update(int id, CategoryRequest categoryUpdate) {
        try {
            Category category = categoryRepository.getReferenceById(id);
            category.setName(categoryUpdate.getName());
            categoryRepository.save(category);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Category not fond");
        }
    }
}
