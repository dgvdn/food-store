package com.example.foodstore.controller;

import com.example.foodstore.dto.JWTAuthRespone;
import com.example.foodstore.dto.LoginDto;
import com.example.foodstore.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
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
    @PostMapping("/login")
    public ResponseEntity<JWTAuthRespone> authenticate(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JWTAuthRespone jwtAuthRespone = new JWTAuthRespone();
        jwtAuthRespone.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthRespone);
    }

}
