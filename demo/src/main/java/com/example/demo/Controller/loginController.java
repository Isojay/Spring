package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Repo.RoleRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.dao.Roles;

@Controller
public class loginController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	@GetMapping("/register")
	public String showRegister() {
		return "register";
	}
	
	
}
