package com.arifwider.boottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(String userEmail) {

        var iterator = productRepository.findAll();

        var products = new ArrayList<Product>();
        for (Product p : iterator) {
            if(p.getOwner()!=null && p.getOwner().equals(userEmail)) products.add(p);
        }

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
