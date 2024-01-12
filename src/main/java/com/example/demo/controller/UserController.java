package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.services.UsersServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UsersServices service;
	@PostMapping("/registration")
	
	
	public String addUser(@ModelAttribute Users user) {
		boolean usersStatus=service.emailExists(user.getEmail());
		if(usersStatus==false) {
		service.addUser(user);
		System.out.println("user added");
	}
		else {
			System.out.println("user exist");
		}
		return "home";
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session) {
		if(service.validateUser(email,password)==true) {
			String role=service.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "administrationHome";
			}
			else {
				return "customerHome";
			}
			
		}
		else {
			return "login";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}


}
