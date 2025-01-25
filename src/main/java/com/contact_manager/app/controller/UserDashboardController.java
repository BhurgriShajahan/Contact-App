package com.contact_manager.app.controller;

import com.contact_manager.app.model.request.UserSignupDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/user/")
public class UserDashboardController {

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("dashboard")
    public String signupForm(Model model) {
        model.addAttribute("userSignupDto", new UserSignupDto());
        return "user/dashboard";
    }


}
