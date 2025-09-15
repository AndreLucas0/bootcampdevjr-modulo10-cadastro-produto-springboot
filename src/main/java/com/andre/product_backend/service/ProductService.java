package com.andre.product_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.andre.product_backend.models.Category;
import com.andre.product_backend.models.Product;
import com.andre.product_backend.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product getById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
        
        return product;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        Product productSaved = productRepository.save(product);
        return productSaved;
    }

    public void deleteById(int id) {
        Product product = this.getById(id);
        productRepository.delete(product);
    }

    public void update(int id, Product productUpdate) {
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
