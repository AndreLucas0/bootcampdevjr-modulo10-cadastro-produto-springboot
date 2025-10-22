package com.andre.product_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.andre.dto.ProductRequest;
import com.andre.dto.ProductResponse;
import com.andre.product_backend.models.Category;
import com.andre.product_backend.models.Product;
import com.andre.product_backend.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public ProductResponse getDTOById(long id) {
        Product product = this.getById(id);
        return product.toDTO();
    }
    
    public Product getById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
        
        return product;
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                                    .stream()
                                    .map(c -> c.toDTO())
                                    .collect(Collectors.toList());
    }

    public ProductResponse save(ProductRequest product) {
        Product productSaved = productRepository.save(product.toEntity());
        return productSaved.toDTO();
    }

    public void deleteById(long id) {
        Product product = this.getById(id);
        productRepository.delete(product);
    }

    public void update(long id, ProductRequest productUpdate) {
        Product product = this.getById(id);
        
        if (productUpdate.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category can not be empty.");
        }

        Category category = categoryService.getById(productUpdate.getCategory().getId());

        product.setDescription(productUpdate.getDescription());
        product.setName(productUpdate.getName());
        product.setPrice(productUpdate.getPrice());
        product.setNewProduct(productUpdate.isNewProduct());
        product.setPromotion(productUpdate.isPromotion());
        product.setCategory(category);
        

        productRepository.save(product);
    }
}
