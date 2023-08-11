package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.web.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // cho phép sử dụng annotati
public class AuthConfig extends WebSecurityConfigurerAdapter {

	/* Mã hóa mật khẩu */
	@Bean
	public BCryptPasswordEncoder getPasswordEnconder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	UserService userService;

	/* Quản lý người dữ liệu người sử dụng */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService); // từ userService
		// đưa nguồn từ cơ sở dữ liệu
	}

	/* Phân quyền sử dụng và hình thức đăng nhập */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF , CORS
		http.csrf().disable().cors().disable();

		// phân quyền sử dụng
		http.authorizeRequests()
				.antMatchers("/manager/account", "/manager/revenue").hasRole("ADMIN")
				.antMatchers("/manager/filter", "/manager/order", "/manager/product", "/manager/profile/**").hasAnyRole("ADMIN", "STAFF")
				.anyRequest().permitAll();

		// điều khiển lỗi truy cập không đúng vai trò
		http.exceptionHandling().accessDeniedPage("/auth/access/denied"); // [error]

		// giao diện đăng nhập
		http.formLogin().loginPage("/auth/login/form") // địa chỉ từ controller
				.loginProcessingUrl("/auth/login") // [/login] - từ form trong file html
				.defaultSuccessUrl("/auth/login/success", false).failureUrl("/auth/login/error")
				.usernameParameter("tenDangNhap") // [username]
				.passwordParameter("matKhau"); // [password]
		http.rememberMe().rememberMeParameter("remember"); // [remember]

		// đăng xuất
		http.logout().logoutUrl("/auth/logoff") // [/logout]
				.logoutSuccessUrl("/auth/logoff/success");

		// OAuth2 đăng nhập từ mạng xã hội
		http.oauth2Login()
				.loginPage("/auth/login/form")
				.defaultSuccessUrl("/oauth2/login/success", true) // lấy từ controller oauth2
				.failureUrl("/auth/login/error")
				.authorizationEndpoint()
				.baseUri("/oauth2/authorization"); // lấy từ href html

	}
}
