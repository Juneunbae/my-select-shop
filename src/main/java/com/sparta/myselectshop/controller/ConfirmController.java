package com.sparta.myselectshop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfirmController {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @GetMapping("/confirm")
    public String confirm() {
        System.out.println(activeProfile);
        return activeProfile;
    }
}