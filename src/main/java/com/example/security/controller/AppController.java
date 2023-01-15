package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/moderator")
    public String mode(){
        return "mode";
    }

    @GetMapping("/system")
    public String system(){
        return "system";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }

}
