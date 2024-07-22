package com.github.bruce_mig.jwt_symmetric_encryption.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping ("/")
    public String home(Authentication authentication){
        return "Hello, "  + authentication.getName();
    }
}
