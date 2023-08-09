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
import com.web.Entity.TaiKhoan;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	TaiKhoanDAO taiKhoanDAO;



	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			TaiKhoan taiKhoan = taiKhoanDAO.findById(username).get();
			
			// tạo userdetail từ account
			String password = taiKhoan.getMatKhau(); // lấy từ csdl
			String[] roles = taiKhoan.getList_VTTK().stream() // lấy từ csdl
					.map(au -> au.getVaiTro().getIdVaiTro()) // list đổi sang mảng -> lamda
					.collect(Collectors.toList()).toArray(new String[0]);
			return User.withUsername(username)
					.password(pe.encode(password))
					.roles(roles).build();
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + "not found"); 
		}
		
	}

	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
//		String fullname = oauth2.getPrincipal().getAttribute("name");
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());
		
		UserDetails userDetails = User.withUsername(email)
				.password(pe.encode(password)).roles("GUEST").build();
		
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());	
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
