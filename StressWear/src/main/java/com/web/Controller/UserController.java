package com.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.service.UserService;

@Controller
public class UserController {
	@RequestMapping("/auth/login/form")
    public String loginPage() {
        return "User/User-login-page";
    }
	
	@RequestMapping("/auth/login/success")
	public String success(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		return "redirect:/";
	}
	
	@RequestMapping("/auth/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập");
		return "forward:/auth/login/form";
	}
	
	@RequestMapping("/auth/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message", "Đăng xuất thành công");
		return "forward:/auth/login/form";
	}
	
	@RequestMapping("/auth/access/denied")
	public String denided(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy xuất");
		return "forward:/auth/login/form";
	}
	
	/*
	 * OAUTH2
	 */
	@Autowired
	UserService userService;
	
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oAuth2) {
		userService.loginFromOAuth2(oAuth2);
		return "forward:/auth/login/success";
	}
}

