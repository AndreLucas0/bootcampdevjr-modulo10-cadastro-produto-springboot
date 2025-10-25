package com.andre.product_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.andre.dto.ProductRequest;
import com.andre.dto.ProductResponse;
import com.andre.product_backend.models.Category;
import com.andre.product_backend.models.Product;
import com.andre.product_backend.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public ProductResponse getById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found."));
        
        return product.toDTO();
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                                    .stream()
                                    .map(c -> c.toDTO())
                                    .collect(Collectors.toList());
    }

    public ProductResponse save(ProductRequest product) {

        try {
            Product productSaved = productRepository.save(product.toEntity());
            return productSaved.toDTO();
        } catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Category not found");
        }

    }

    public void deleteById(long id) {
        
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }

        productRepository.deleteById(id);
    }

    public void update(long id, ProductRequest productUpdate) {
        
        try {
            Product product = productRepository.getReferenceById(id);

            Category category = new Category(productUpdate.getCategory().getId());
    
            product.setDescription(productUpdate.getDescription());
            product.setName(productUpdate.getName());
            product.setPrice(productUpdate.getPrice());
            product.setNewProduct(productUpdate.isNewProduct());
            product.setPromotion(productUpdate.isPromotion());
            product.setCategory(category);
            
    
            productRepository.save(product);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Product not found");
        }
        catch (DataIntegrityViolationException e) {
            throw new EntityNotFoundException("Category not found");
        }

    }
}
