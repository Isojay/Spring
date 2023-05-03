package com.example.demo.COntroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AdminController {
	
	@GetMapping("/admin")
	public String showHome() {
		return "adminHome";
	}
	
	
	
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
}
