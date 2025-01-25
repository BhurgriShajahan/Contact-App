package com.contact_manager.app.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/base/")
public interface MainControllerEndpoints {

    @GetMapping("home")
    public String index();

    @GetMapping("about")
    public String about();

    @GetMapping("services")
    public String services();
}
