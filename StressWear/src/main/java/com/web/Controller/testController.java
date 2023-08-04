package com.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {

    @GetMapping("hello")
    public String getPage() {
        return "Manager/Manager-filter-page.html";
    }

    @GetMapping("index")
    public String index() {
        return "User/User-index";
    }

    @GetMapping("about")
    public String about() {
        return "User/User-about";
    }

    @GetMapping("product")
    public String product() {
        return "User/User-product";
    }

    @GetMapping("product-list")
    public String productlist() {
        return "User/User-product-List";
    }

    @GetMapping("product-item")
    public String productItem() {
        return "User/User-product-Item";
    }

    @GetMapping("/manager/filter")
    public String ManagerFilter() {
        return "Manager/Manager-filter-page";
    }

    // @RequestMapping("login-page")
    // public String loginPage() {
    //     return "User/User-login-page";
    // }

    @GetMapping("sign-up-page")
    public String signUpPage() {
        return "User/User-register-page";
    }

    @GetMapping("user-person-edit")
    public String personEdit() {
        return "User/User-person";
    }

    @GetMapping("forgot-pass")
    public String forgotPass() {
        return "User/User-forgot-pass";
    }

}
