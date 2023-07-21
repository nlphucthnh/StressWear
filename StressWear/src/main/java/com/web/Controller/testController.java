package com.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    @GetMapping("hello")
    public String getPage(){
        return "Manager/Manager-login-page.html";
    }
}
