package com.example.foodstore.controller;

import com.example.foodstore.dto.JWTAuthRespone;
import com.example.foodstore.dto.LoginDto;
import com.example.foodstore.service.AuthService;
import com.example.foodstore.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/register")
    public ResponseEntity<JWTAuthRespone> register(@RequestBody LoginDto loginDto) {
        String token = authService.register(loginDto);
        JWTAuthRespone jwtAuthRespone = new JWTAuthRespone();
        jwtAuthRespone.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthRespone);
    }

}
