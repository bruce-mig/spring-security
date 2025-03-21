package com.github.bruce_mig.spring_kc_server.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello-world")
public class HelloWorldApi {

    @GetMapping
    public String helloWorld() {
        return "Hello World!";
    }
}
