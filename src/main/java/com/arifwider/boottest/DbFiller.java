package com.arifwider.boottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DbFiller implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void run(String... args) {

        productRepository.save(new Product("Aufkleber", 100));
        productRepository.save(new Product("Untersetzer", 50));
    }
}
