package com.tutorial.portal.controller;

import com.tutorial.portal.config.DefaultUserSeeder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class WebController {

    @ModelAttribute
    public void addCommonData(Model model) {
        model.addAttribute("appName", "Secure Login Portal");
        model.addAttribute("testUsername", DefaultUserSeeder.TEST_USERNAME);
        model.addAttribute("testPassword", DefaultUserSeeder.TEST_PASSWORD);
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String homePage(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "home";
    }

    @GetMapping("/admin/dashboard")
    public String adminPage() {
        return "admin";
    }
}
