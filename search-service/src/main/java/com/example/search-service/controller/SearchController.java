package com.example.searchservice.controller;

import com.example.searchservice.entity.Product;
import com.example.searchservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")  // 🔁 Changed from /products to /search
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public Product create(@RequestBody Product p) {
        return searchService.createProduct(p);
    }

    @GetMapping
    public List<Product> list() {
        return searchService.getAllProducts();
    }
}
