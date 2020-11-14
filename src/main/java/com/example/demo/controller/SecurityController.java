package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	// �F�؍ς݂̃��[�U�[�����擾����
	@GetMapping
	public String showList(Authentication loginUser,Model model) {
		model.addAttribute("username",loginUser.getName());
		model.addAttribute("role",loginUser.getAuthorities());
		return "index";
	}

}
