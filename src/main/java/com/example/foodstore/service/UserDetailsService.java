package com.example.foodstore.service;

import com.example.foodstore.dto.UserDetailsDto;
import com.example.foodstore.model.User;
import com.example.foodstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    @Autowired
    private com.example.foodstore.repository.UserDetailsRepository userDetailsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepository userRepo;
    //get current username
    public static String getCurrentUsername() {
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    public void addUserDetails(UserDetailsDto userDetailsDto){
        com.example.foodstore.model.UserDetails userDetails = new com.example.foodstore.model.UserDetails();
        userDetails.setName(userDetailsDto.getName());
        userDetails.setAddress(userDetailsDto.getAddress());
        userDetails.setPhone(userDetailsDto.getPhone());
        userDetails.setEmail(userDetailsDto.getEmail());
        userDetails.setUser(userRepository.findByUsername(getCurrentUsername()).get());
        userDetailsRepository.save(userDetails);
    }

    public com.example.foodstore.model.UserDetails findByUsername(String username) {
        User user = userRepo.findByUsername(username).get();
        return userDetailsRepository.findByUser(user);
    }
}
