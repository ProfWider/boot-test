package com.arifwider.boottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RestController
public class HelloController {

    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String index() {
        String jdbcUrl = Optional.of(env.getProperty("JDBC_DATABASE_URL")).orElse("Environment variable not found");
        String jdbcUser = Optional.of(env.getProperty("JDBC_DATABASE_USERNAME")).orElse("Environment variable not found");
        return "Hello from " + jdbcUrl + "! (" + jdbcUser + ")";
    }

}
