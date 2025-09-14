package com.cromxt.ums.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {


    @GetMapping(value = {
            "/auth/**",
            "/home/**",
            "/"
    })
    public String home() {
        return "forward:/index.html";
    }

}
