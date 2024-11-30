package com.contact_manager.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/msg")
    @ResponseBody
    public String message() {
        return "test controller running..";
    }

    @GetMapping("/msg2")
    @ResponseBody
    public String message2() {
        String name = "Shahajahan";
        String username = "Shahjahan";
        return "test controller running..";
    }

    @GetMapping("/index")
    public String index() {
        return "/main/index";
    }
}
