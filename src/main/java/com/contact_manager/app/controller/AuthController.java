package com.contact_manager.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Controller
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @GetMapping("signup")
    public String signup() {
        return "main/signup";
    }

    @GetMapping("login")
    public String login() {
        return "main/login";
    }
}
