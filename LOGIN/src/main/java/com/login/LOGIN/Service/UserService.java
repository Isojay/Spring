package com.login.LOGIN.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.login.LOGIN.Repo.ConfirmationTokenRepository;
import com.login.LOGIN.Repo.UserRepo;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	EmailService emailService;
}
