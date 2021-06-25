package com.vinay.FormSubmission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FromController {
	
	@Autowired
	private UserRepositary repo;
	
	@GetMapping("")
	public String home() {
		return "index";
	}
	
	@GetMapping("/register")
	public String Register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/list_users")
	public String listusers(Model model) {
		List<User> list = repo.findAll();
		model.addAttribute("userlist", list);
		return "users";
	}
	
	@PostMapping("/process_register")
	public String processregistration(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
		repo.save(user);
		return "registersuccess";
	}
}
