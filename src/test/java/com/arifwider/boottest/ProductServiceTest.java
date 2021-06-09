package com.arifwider.boottest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository repository;

    @Test
    @DisplayName("should find products by owner's email")
    void testFindByOwnerEmail() {
        var p1 = new Product("Lineal", 42);
        p1.setOwner("arif@test.com");
        var p2 = new Product("Stift", 42);
        p2.setOwner("jens@test.com");
        doReturn(List.of(p1, p2)).when(repository).findAll();

        List<Product> arifsProducts = service.findAll("arif@test.com");

        Assertions.assertSame(arifsProducts.size(), 1, "The number of products returned was wrong");
        Assertions.assertSame(arifsProducts.get(0).getName(), "Lineal", "The wrong product was returned");
    }
}
