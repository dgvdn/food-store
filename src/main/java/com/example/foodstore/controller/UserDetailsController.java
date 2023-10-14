package com.example.foodstore.controller;

import com.example.foodstore.dto.UserDetailsDto;
import com.example.foodstore.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user-details")
public class UserDetailsController {
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/info")
    public ResponseEntity<String> info() {
        String response = UserDetailsService.getCurrentUsername();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody UserDetailsDto userDetailsDto) {
        userDetailsService.addUserDetails(userDetailsDto);
        return ResponseEntity.ok("User details added");
    }
}
