package com.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    @GetMapping("hello")
    public String getPage(){
        return "Manager/Manager-login-page.html";
    }

    @GetMapping("/login-page")
    public String getLogin(){
        return "User/User-login-page";
    }

    @GetMapping("/register-page")
    public String getRegister(){
        return "User/User-register-page";
    }

    @GetMapping("login-page")
    public String loginPage(){
        return "User/User-login-page";
    }

    @GetMapping("sign-up-page")
    public String signUpPage(){
        return "User/User-register-page";
    }

     @GetMapping("user-person-edit")
    public String personEdit(){
        return "User/User-person";
    }

    @GetMapping("forgot-pass")
    public String forgotPass(){
        return "User/User-forgot-pass";
    }


}
