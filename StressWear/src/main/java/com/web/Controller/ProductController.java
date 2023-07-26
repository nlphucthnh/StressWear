package com.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProductController {

    @GetMapping("User/product")
    public String product() {
        return "User/User-product";
    }
}
