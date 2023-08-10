package com.web.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.DAO.ThongTinTaiKhoanDAO;
import com.web.service.UserService;

@Controller
public class UserController {
	@Autowired
	HttpSession session;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ThongTinTaiKhoanDAO thongTinTaiKhoanDAO;

	@RequestMapping("/auth/login/form")
	public String loginPage() {
		return "User/User-login-page";
	}

	@RequestMapping("/auth/login/success")
	public String success(Model model) {
		if (request.isUserInRole("USER") && (request.isUserInRole("ADMIN") || request.isUserInRole("STAFF"))) {
			System.out.println("Vừa người dùng vừa quản trị viên");
		} else if (request.isUserInRole("ADMIN") || request.isUserInRole("STAFF")) {
			return "redirect:/manager/profile";
		}
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

	@RequestMapping("auth/sign/form")
	public String signInPage() {
		return "User/User-register-page";
	}

	/*
	 * OAUTH2
	 */
	@Autowired
	UserService userService;

	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oAuth2, Model model) {
		userService.loginFromOAuth2(oAuth2);
		session.setAttribute("oAuth2", oAuth2);
		String rootString = oAuth2.getPrincipal().toString();
		// Cắt picture
		String pictureString = rootString.substring(rootString.indexOf("picture="), rootString.indexOf("aud="))
				.replace("picture=", "").replace(",", "").trim();
		session.setAttribute("picture", pictureString);
		System.out.println(pictureString);
		return "forward:/auth/login/success";
	}
}
