package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.LoginData;
import com.example.demo.entity.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    UsersService usv;

    @Autowired
    SongService songserv;

    @PostMapping("/register")
    public String addUser(Model model, @ModelAttribute Users user) {
        boolean userState = usv.emailExists(user.getEmail());
        if (userState == false) {
            usv.addUser(user);
            model.addAttribute("successful", "User registered successfully");
            return "login";
        } else {
            model.addAttribute("error", "User already exists");
            return "register";
        }
    }

    @PostMapping("/login")
    public String validateUser(Model model, @ModelAttribute LoginData data, HttpSession session) {
        String email = data.getEmail();
        String password = data.getPassword();

        if (usv.validateUser(email, password)) {
            String role = usv.getuserRole(email).toLowerCase();
            session.setAttribute("email", email);

            if (role.equals("admin")) {
                model.addAttribute("user", usv.getUser(email));
                return "adminHome";
            } else {
                Users user = usv.getUser(email);
                boolean isPremium = user.isPremium();
                model.addAttribute("isPremium", isPremium);
                model.addAttribute("songs", songserv.fetchAllSongs());
                model.addAttribute("user", usv.getUser(email));
                return "customerHome";
            }
        } else {
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
