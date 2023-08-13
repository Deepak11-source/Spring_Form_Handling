package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService service;
		
	@GetMapping("/")
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "register";
	}
	
	
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user) {
		String result = null;
		System.out.println(user);
		service.registerUser(user);
		
		if(user.getPassword().equals(user.getCpassword())) {
			try {
				service.registerUser(user);
				result = "home";
			}
			catch(Exception e) {
				result = "Error";
			}
		}
		
		return result;		
	}

}