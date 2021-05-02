package com.arifwider.boottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {

        var iterator = productRepository.findAll();

        var products = new ArrayList<Product>();
        iterator.forEach(products::add);

        return products;
    }

    public Long count() {

        return productRepository.count();
    }

    public void deleteById(Long productId) {

        productRepository.deleteById(productId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

}