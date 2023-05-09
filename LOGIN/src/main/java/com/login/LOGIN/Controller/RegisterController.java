package com.login.LOGIN.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.login.LOGIN.Entity.ConfirmationToken;
import com.login.LOGIN.Entity.User;
import com.login.LOGIN.Repo.ConfirmationTokenRepository;
import com.login.LOGIN.Repo.UserRepo;
import com.login.LOGIN.Service.EmailService;
import com.login.LOGIN.Service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;
    
    @GetMapping("/register")
	public String showRegister() {
		return "register-form.html";
	}

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,Model model) {
	    if (userRepo.existsByUserEmail(user.getUserEmail())) {
	        model.addAttribute("errorMessage", "Error: Email is already in use!");
	        return "register-form";
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

	    model.addAttribute("successMessage", "Verify email by the link sent on your email address");
	    return "register-form";
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token")String confirmationToken, Model model) {
 
    	    ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

    	    if (token != null) {
    	        User user = userRepo.findUserByUserEmail(token.getUserEntity().getUserEmail());
    	        user.setEnabled(true);
    	        userRepo.save(user);
    	        model.addAttribute("message", "Email verified successfully!");
    	        return "email-verification-result";
    	    }
    	    
    	    model.addAttribute("error", "Error: Couldn't verify email");
    	    return "email-verification-result";
    	}
 }
