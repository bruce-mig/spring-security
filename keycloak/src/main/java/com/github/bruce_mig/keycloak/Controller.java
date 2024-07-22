package com.github.bruce_mig.keycloak;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
public class Controller {

    @GetMapping
    @PreAuthorize("hasRole('client_user')")
    public String hello(){
        return "Hello from Spring Boot and Keycloak";
    }

    @GetMapping("/hello2")
    @PreAuthorize("hasRole('client_admin')")
    public String hello2(){
        return "Hello from Spring Boot and Keycloak - ADMIN";
    }
}
