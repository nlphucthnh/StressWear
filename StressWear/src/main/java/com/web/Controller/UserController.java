package com.web.Controller;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.service.UserService;

@Controller
public class UserController {
	@Autowired
	HttpSession session;


	@RequestMapping("/auth/login/form")
    public String loginPage() {
        return "User/User-login-page";
    }
	
	@RequestMapping("/auth/login/success")
	public String success(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		return "redirect:/index";
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
	public String success(OAuth2AuthenticationToken oAuth2,Model model) {
		userService.loginFromOAuth2(oAuth2);
		session.setAttribute("oAuth2", oAuth2);
		session.getAttribute("oAuth2");
		return "forward:/auth/login/success";
	}
}

