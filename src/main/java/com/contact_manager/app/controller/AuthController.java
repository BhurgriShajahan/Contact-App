package com.contact_manager.app.controller;

import com.contact_manager.app.model.entities.CustomResponseEntity;
import com.contact_manager.app.model.request.LoginRequest;
import com.contact_manager.app.model.request.UserSignupDto;
import com.contact_manager.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@Controller
@RequestMapping("/v1/auth/")
public class AuthController {

    AuthService authService;
    @Autowired
    AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("signup")
    public CustomResponseEntity<?> signup(@RequestBody UserSignupDto userSignupDto) {
        return authService.signup(userSignupDto);
    }

    @PostMapping("login")
    public CustomResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }


//    @GetMapping("signup")
//    public String signup() {
//        return "main/signup";
//    }
//
//    @GetMapping("login")
//    public String login() {
//        return "main/login";
//    }
}
