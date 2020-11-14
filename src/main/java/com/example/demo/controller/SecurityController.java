package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/index")
	public String login() {
		return "index";
	}
	// 認証済みのユーザー情報を取得する
	@GetMapping
	public String showList(Authentication loginUser,Model model) {
		model.addAttribute("username",loginUser.getName());
		model.addAttribute("role",loginUser.getAuthorities());
		return "index";
	}

}
