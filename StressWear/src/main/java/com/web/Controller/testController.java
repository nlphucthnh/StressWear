package com.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

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

    @GetMapping("login-page2")
    public String loginPage() {
        return "User/User-login-page";
    }

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

    @GetMapping("/manager/account")
    public String ManagerAccount() {
        return "Manager/Manager-account-page.html";
    }

    @GetMapping("/manager/filter")
    public String ManagerFilter() {
        return "Manager/Manager-filter-page";
    }

    @GetMapping("hello")
    public String getPage() {
        return "Manager/Manager-filter-page.html";
    }

    @GetMapping("/manager/revenue")
    public String ManagerRevenue() {
        return "Manager/Manager-revenue-page";
    }

    @GetMapping("/manager/oder")
    public String ManagerOder() {
        return "Manager/Manager-oder-page";
    }

    @GetMapping("/manager/login")
    public String ManagerLogin() {
        return "Manager/Manager-login-page";
    }

    @GetMapping("/manager/product")
    public String ManagerProduct() {
        return "Manager/Manager-product-page";
    }

    @GetMapping("/manager/profile")
    public String ManagerProfile() {
        return "Manager/Manager-profile-page";
    }
}
