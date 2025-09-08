package com.andre.product_backend.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andre.product_backend.models.Product;
import com.andre.product_backend.repository.ProductRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        return ResponseEntity.ok(product);
    }

    @PostMapping("products")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        
        product = productRepository.save(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(product);
    }

    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
