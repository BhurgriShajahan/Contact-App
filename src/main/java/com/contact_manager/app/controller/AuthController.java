package com.contact_manager.app.controller;

import com.contact_manager.app.endpoints.AuthControllerEndpoints;
import com.contact_manager.app.model.entities.CustomResponseEntity;
import com.contact_manager.app.model.request.UserSignupDto;
import com.contact_manager.app.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
public class AuthController implements AuthControllerEndpoints {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    AuthService authService;

    @Autowired
    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public String signupForm(Model model) {
        model.addAttribute("userSignupDto", new UserSignupDto());
        return "main/signup";
    }

    @Override
    public String signupSubmit(UserSignupDto userSignupDto, Model model) {
        CustomResponseEntity<UserSignupDto> response = authService.signup(userSignupDto);

        if (!response.isSuccess()) {
            model.addAttribute("error", response.getMessage());
            return "main/signup";
        }

        model.addAttribute("message", "Signup successful! Please log in.");
        return "redirect:/v1/auth/login";
    }

    @Override
    public String loginForm(Model model) {
//        model.addAttribute("loginRequest", new LoginRequest());
        return "main/login";
    }

}
