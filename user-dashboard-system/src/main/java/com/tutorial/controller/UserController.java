package com.tutorial.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tutorial.model.User;
import com.tutorial.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addCommonData(Model model) {
        model.addAttribute("appName", "Spring MVC Demo");
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user,
                               BindingResult bindingResult,
                               HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        User savedUser = userService.register(user);
        session.setAttribute("loggedUserEmail", savedUser.getEmail());
        session.setAttribute("loggedUser", savedUser.getName());
        return "success";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String email = (String) session.getAttribute("loggedUserEmail");
        User user = userService.findByEmail(email);

        if (user == null) {
            return "redirect:/register";
        }

        model.addAttribute("user", user);
        model.addAttribute("keyword", "");
        return "dashboard";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "") String keyword,
                         HttpSession session,
                         Model model) {
        String email = (String) session.getAttribute("loggedUserEmail");
        User loggedUser = userService.findByEmail(email);

        if (loggedUser == null) {
            return "redirect:/register";
        }

        String normalizedKeyword = keyword.trim();
        model.addAttribute("user", loggedUser);
        model.addAttribute("keyword", normalizedKeyword);

        if (normalizedKeyword.isEmpty()) {
            model.addAttribute("searchMessage", "Enter an email to search.");
            return "dashboard";
        }

        User searchedUser = userService.findByEmail(normalizedKeyword);
        if (searchedUser == null) {
            model.addAttribute("searchMessage", "No user found for: " + normalizedKeyword);
        } else {
            model.addAttribute("searchedUser", searchedUser);
        }

        return "dashboard";
    }

    @PostMapping("/register-raw")
    public String registerRaw(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        model.addAttribute("name", name);
        return "success";
    }
}
