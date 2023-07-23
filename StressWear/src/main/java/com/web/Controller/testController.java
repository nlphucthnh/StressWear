package com.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    @GetMapping("hello")
    public String getPage(){
        return "Manager/Manager-login-page.html";
    }

    @GetMapping("index")
    public String index(){
        return "User/User-index";
    }

       @GetMapping("about")
    public String about(){
        return "User/User-about";
    }

       @GetMapping("product")
    public String product(){
        return "User/User-product";
    }
  @GetMapping("product-list")
    public String productlist(){
        return "User/User-product-List";
    }
    
    @GetMapping("product-item")
    public String productItem(){
        return "User/User-product-Item";
    }
}
