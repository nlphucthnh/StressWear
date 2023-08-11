package com.web.Controller.User_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UrLoginController {
   

    @RequestMapping("/user/confirm")
    public String getConfirm(){
        return "User/User-confirm-acc";
    } 

    @RequestMapping("/user/fogot")
    public String getFogotPass(){
        return "User/User-forgot-pass";
    } 

    // @RequestMapping("auth/sign/form")
	// public String signInPage() {
	// 	return "User/User-register-page";
	// }
    
}
