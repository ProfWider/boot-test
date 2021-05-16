package com.arifwider.boottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {

    @Autowired
    private Environment env;

    @RequestMapping("/env")
    public String index() {
        String testEnvValue = Optional.ofNullable(env.getProperty("TEST_VALUE")).orElse("Environment variable not found");
        return "Hey there, I know environment variables, e.g. " + testEnvValue;
    }

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> allProducts(@AuthenticationPrincipal OidcUser user) {
        return productService.findAll(user.getEmail());
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable String id) {
        Long productId = Long.parseLong(id);
        productService.deleteById(productId);
    }

    @GetMapping("/products/count")
    public Long count() {
        return productService.count();
    }

}
