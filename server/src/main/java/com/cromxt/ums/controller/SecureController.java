package com.cromxt.ums.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/secured")
public class SecureController {

    @GetMapping
    public String secured() {
        return "secured";
    }
}
