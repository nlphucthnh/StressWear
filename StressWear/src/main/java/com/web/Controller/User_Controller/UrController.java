package com.web.Controller.User_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UrController {

    @GetMapping("about")
    public String about() {
        return "User/User-about";
    }

}
