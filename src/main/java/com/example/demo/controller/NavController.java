package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NavController {
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/newsong")
	public String song() {
		return "addSong";
	}
}
