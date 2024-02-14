package com.code.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.code.entities.User;
import com.code.repository.UserRepo;
import com.code.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public void CommonUser(Principal p,Model m) {
		if(p!=null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user",user);	
		}	
	}
	
	@GetMapping("/")
	public String index() {
		return "index";	
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	@GetMapping("/user/profile")
	public String profile(Principal p,Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		m.addAttribute("user",user);
		
		return "profile";
	}
	@GetMapping("/user/home")
	public String home() {
		return "home";
	}
	@PostMapping("saveUser")
	public String SaveUser(@ModelAttribute User user, HttpSession session) {
		
		//System.out.println(user);
		
		User u = userService.saveUser(user);
		if(u!=null) {
			//System.out.println("save Success");
			session.setAttribute("msg", "Register Successfully..");
		}else {
		//	System.out.println("Error in Server");
			session.setAttribute("msg", "Something wrong on server..");
		}
		
		return "redirect:/register";
		
	}

}
