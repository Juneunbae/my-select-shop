package com.sparta.myselectshop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Value("${spring.profiles.active}")
    private String activeProfile;


    @GetMapping("/")
    public String home() {
        System.out.println(activeProfile);
        return "index";
    }
}