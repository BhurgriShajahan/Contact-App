package com.contact_manager.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Controller
@RequestMapping("/v1/base/")
public class MainController {

    @GetMapping("home")
    public String index() {
        return "main/home";
    }

    @GetMapping("about")
    public String about() {
        return "main/about";
    }

    @GetMapping("services")
    public String services() {
        return "main/services";
    }
}
