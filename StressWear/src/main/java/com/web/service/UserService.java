package com.web.service;

import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.web.DAO.TaiKhoanDAO;
import com.web.DAO.ThongTinTaiKhoanDAO;
import com.web.Entity.TaiKhoan;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	TaiKhoanDAO taiKhoanDAO;

	@Autowired
	ThongTinTaiKhoanDAO thongTinTaiKhoanDAO;

	@Autowired
	HttpSession session;


	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			TaiKhoan taiKhoan = taiKhoanDAO.findById(username).get();
			// tài khoản lấy từ csdl với DAO
			// tạo userdetail từ thông tin account
			String password = taiKhoan.getMatKhau(); // lấy từ csdl
			String[] roles = taiKhoan.getList_VTTK().stream() // lấy từ csdl
					.map(au -> au.getVaiTro().getIdVaiTro().trim()) // list đổi sang mảng -> lamda / cắt khoảng trắng ở đầu
					.collect(Collectors.toList()).toArray(new String[0]);
			session.setAttribute("tenDangNhapLogin",taiKhoan.getTenDangNhap());
			return User.withUsername(username)
					.password(pe.encode(password))
					.roles(roles).build(); // roles yêu cầu mảng 
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + "not found"); 
		}
		
	}

	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
//		String fullname = oauth2.getPrincipal().getAttribute("name");
		// principal = thông tin user
		// đọc thông tin tài khoản từ mạng xã hội 
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());

		// tạo đối tượng userDetails(Principal) lấy email làm username
		UserDetails userDetails = User.withUsername(email)
				.password(pe.encode(password)).roles("GUEST").build();
		// tạo đối tượng authentication từ userDetails
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());	
		// thay đổi thông tin đăng nhập của hệ thống	
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
