package com.arifwider.boottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    private Map<String, LocalDateTime> usersLastAccess = new HashMap<>();

    @Autowired
    private Environment env;

    @GetMapping("/")
    public String getCurrentUser(@AuthenticationPrincipal OidcUser user, Model model) {
        String email = user.getEmail();

        model.addAttribute("email", email);
        model.addAttribute("lastAccess", usersLastAccess.get(email));
        model.addAttribute("firstName", user.getGivenName());
        model.addAttribute("lastName", user.getFamilyName());

        usersLastAccess.put(email, LocalDateTime.now());

        return "hello";
    }

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
    public String productSubmit(@AuthenticationPrincipal OidcUser user, @ModelAttribute Product product, Model model) {
        product.setOwner(user.getEmail());
        productService.save(product);
        model.addAttribute("product", product);
        return "productresult";
    }

    @GetMapping("/listproducts")
    public String productsTable(@AuthenticationPrincipal OidcUser user, Model model) {
        List<Product> products = productService.findAll(user.getEmail());
        model.addAttribute("products", products);
        return "productstable";
    }
}
