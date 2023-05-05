package com.example.demo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Repo.RoleRepo;

@Component
public class GoogleOAuth2SucessHandler {
	
	@Autowired
	RoleRepo roleRepo;
}
