package com.arifwider.boottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String index() {
        String testValue = env.getProperty("TEST_VALUE");
        return "Greetings from Webtech! (" + (testValue == null ? "Environment variable not found" : testValue) + ")";
    }

}
