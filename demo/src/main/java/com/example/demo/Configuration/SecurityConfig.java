package com.example.demo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.customUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	customUserDetailService customUserDetailService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(configurer -> 
							configurer								
								
								.requestMatchers("/","/shop/**","/register").permitAll()
								.requestMatchers("/admin/**").hasRole("ADMIN")								
								.anyRequest().authenticated()
								
				)
			.formLogin(form -> form
					.loginPage("/login")
					.failureUrl("/login?error=true")
					.permitAll()
					.usernameParameter("email")
					.passwordParameter("password")
				)
			.logout(logout->
				logout
					.permitAll()
					.logoutSuccessUrl("/login")
				)
			.exceptionHandling(configurer -> 
				configurer
						.accessDeniedPage("/")
					);
		http.csrf().disable();
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService);
	}
	
	public void configure(WebSecurity web) throws Exception {
		  web
		    .ignoring()
		    .requestMatchers("/css/**","/js/**");
		}
	
	public SecurityConfig() {
		// TODO Auto-generated constructor stub
	}

}
