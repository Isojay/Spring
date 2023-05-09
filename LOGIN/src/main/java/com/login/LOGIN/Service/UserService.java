package com.login.LOGIN.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.login.LOGIN.Entity.ConfirmationToken;
import com.login.LOGIN.Entity.User;
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
	
	
	public ResponseEntity<?> saveUser(User user) {

        if (userRepo.existsByUserEmail(user.getUserEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        userRepo.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken();

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUserEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8085/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepo.findUserByUserEmail(token.getUserEntity().getUserEmail());
            user.setEnabled(true);
            userRepo.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
}
