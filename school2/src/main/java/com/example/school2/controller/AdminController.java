package com.example.school2.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    // Show admin login page
    @GetMapping("/admin/login")
    public String adminLogin() {
        return "admin_login";
    }

    // Process admin login
    @PostMapping("/admin/login")
    public String adminLoginProcess(@RequestParam("password") String password, 
                                   HttpSession session, 
                                   Model model) {
        // Check if password is correct
        if ("hello".equals(password)) {
            // Set admin authenticated in session
            session.setAttribute("adminAuthenticated", true);
            return "redirect:/admin/students";
        } else {
            // Wrong password, show error
            model.addAttribute("error", "Invalid password. Please try again.");
            return "admin_login";
        }
    }

    // Admin logout
    @GetMapping("/admin/logout")
    public String adminLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}