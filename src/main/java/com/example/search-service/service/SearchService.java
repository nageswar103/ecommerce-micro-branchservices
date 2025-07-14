package com.example.searchservice.service;

import com.example.searchservice.entity.Product;
import com.example.searchservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product p) { return productRepository.save(p); }
    public List<Product> getAllProducts() { return productRepository.findAll(); }
}
