package com.arifwider.boottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/testpage")
    public String testPage(Model model) {
        model.addAttribute("name", "My Name");
        return "testpage";
    }

    @GetMapping("/createproduct")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        return "productcreation";
    }

    @PostMapping("/createproduct")
    public String productSubmit(@ModelAttribute Product product, Model model) {
        productService.save(product);
        model.addAttribute("product", product);
        return "productresult";
    }

    @GetMapping("/listproducts")
    public String productsTable(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "productstable";
    }
}
