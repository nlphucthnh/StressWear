package com.web.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/home/index")
	public String index(Model model) {
		model.addAttribute("message", "This is home page");
		return "forward:/login-page";
	}
	
	@RequestMapping("/home/about")
	public String about(Model model) {
		model.addAttribute("message", "This is introduction page");
		return "forward:/login-page";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/home/admins")
	public String admins(Model model) {
		model.addAttribute("message", "Hello administrator");
		return "forward:/login-page";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@RequestMapping("/home/users")
	public String users(Model model) {
		model.addAttribute("message", "Hello staff");
		return "forward:/login-page";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/home/authenticated")
	public String authenticated(Model model) {
		model.addAttribute("message", "Hello authenticated user");
		return "forward:/login-page";
	}
}

