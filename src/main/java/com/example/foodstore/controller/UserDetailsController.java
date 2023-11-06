package com.example.foodstore.controller;

import com.example.foodstore.dto.UserDetailsDto;
import com.example.foodstore.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/user-details")
public class UserDetailsController {
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> info() {
        Map<String, String> response = new HashMap<>();
        response.put("username", UserDetailsService.getCurrentUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody UserDetailsDto userDetailsDto) {
        userDetailsService.addUserDetails(userDetailsDto);
        return ResponseEntity.ok("User details added");
    }
}
