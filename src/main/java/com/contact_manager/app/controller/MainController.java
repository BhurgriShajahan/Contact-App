package com.contact_manager.app.controller;

import com.contact_manager.app.endpoints.MainControllerEndpoints;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController implements MainControllerEndpoints {

    @Override
    public String index() {
        return "main/home";
    }

    @Override
    public String about() {
        return "main/about";
    }

    @Override
    public String services() {
        return "main/services";
    }
}
