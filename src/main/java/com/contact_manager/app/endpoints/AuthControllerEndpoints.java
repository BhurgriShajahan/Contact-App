package com.contact_manager.app.endpoints;

import com.contact_manager.app.model.request.UserSignupDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/auth/")
public interface AuthControllerEndpoints {

    //Signup Request
    @GetMapping("signup")
    public String signupForm(Model model);

    //Signup Response
    @PostMapping("signup")
    public String signupSubmit(@ModelAttribute UserSignupDto userSignupDto, Model model);

    //Login
    @GetMapping("login")
    public String loginForm(Model model);

}
