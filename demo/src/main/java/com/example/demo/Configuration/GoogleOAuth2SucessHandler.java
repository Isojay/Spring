//package com.example.demo.Configuration;
//
//import java.io.IOException;
//import java.nio.channels.NonReadableChannelException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.Repo.RoleRepo;
//import com.example.demo.Repo.UserRepo;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class GoogleOAuth2SucessHandler implements AuthenticationSuccessHandler{
//	
//	@Autowired
//	RoleRepo roleRepo;
//	
//	@Autowired
//	UserRepo userRepo;
//	
//	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//		
//		token.getPrincipal().getAttribute().get().toString();
//		
//	}
//}
