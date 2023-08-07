package com.web.Controller.Manager_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class MgAccountController {
   
    @GetMapping("account")
    public String getAccountPage() {
        return "Manager/Manager-account-page";
    }

    
}
