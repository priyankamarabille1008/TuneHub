package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Users;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UsersService usv;
	
	@GetMapping("/")
    public String redirectToRegister() {
        // Redirect to the register page
        return "redirect:/register";
    }
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user){
		String res="";
		boolean userState=usv.emailExists(user.getEmail());
		if(userState == false) {
			usv.addUser(user);
			System.out.println(user.getUsername()+user.getEmail()+user.getPassword()+user.getGender()+user.getRole()+user.getAddress());
			System.out.println("User added");
			res="login";
		}else {
			System.out.println("User Already exists");
			res="failed";
		}
			return res;
	}
	
	@PostMapping("/login")
	public String validateUser(@RequestParam(name="email") String email,@RequestParam(name="password") String password,HttpSession session) {
		if(usv.validateUser(email,password)==true) {
			String role=usv.getuserRole(email).toLowerCase();
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				return "adminHome";
			}else{
				return "customerHome";
			}
		}
		else {
			return "login";
			}
	}

//	@GetMapping("/pay")
//	public String pay(@RequestBody String email) {
//		boolean paymentStatus = false;
//		
//		if(paymentStatus == true) {
//			Users user= usv.getUser(email);
//			user.setPremium(true);
//			usv.updateUser(user);
//		}
//		return "login";
//	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
