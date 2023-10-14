package com.example.foodstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    //build login rest api
    //http://localhost:8080/auth/login
    // {
    //     "username": "admin",
    //     "password": "admin"
    // }
    // {
    //     "username": "user",
    //     "password": "user"
    // }
    @RequestMapping("/")
    public String home() {
        return "Hello Admin";
    }
}
