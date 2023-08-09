package com.web.Controller.Manager_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class MgController {

    @GetMapping("filter")
    public String getFilterPage() {
        return "Manager/Manager-filter-page";
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "Manager/Manager-login-page";
    }
    @GetMapping("order")
    public String getOrderPage() {
        return "Manager/Manager-order-page";
    }

    @GetMapping("product")
    public String getProductPage() {
        return "Manager/Manager-product-page";
    }

    @GetMapping("profile")
    public String getProfliePage() {
        return "Manager/Manager-profile-page";
    }

    @GetMapping("revenue")
    public String getRevenuePage() {
        return "Manager/Manager-revenue-page";
    }

}
