package com.tutorial.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.inventory.entity.Product;
import com.tutorial.inventory.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> getByName(String name) {
        return repository.findByName(name);
    }

    public List<Product> findExpensiveProducts(double price) {
        return repository.findExpensiveProducts(price);
    }
}
