package com.github.bruce_mig.mssc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "Hello, home!";
    }

    @GetMapping("/private")
    public String secure(){
        return "secured";
    }
}
