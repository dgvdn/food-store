package com.example.foodstore.controller;

import com.example.foodstore.dto.CartDto;
import com.example.foodstore.model.Product;
import com.example.foodstore.model.UserDetails;
import com.example.foodstore.service.CartService;
import com.example.foodstore.service.ProductService;
import com.example.foodstore.service.UserDetailsService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(Long productId, int quantity) {
        cartService.addToCart(productId, quantity);
        return ResponseEntity.ok("Product added to cart");
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCart() {
        UserDetails userDetails = userDetailsService.findByUsername(UserDetailsService.getCurrentUsername());
        return ResponseEntity.ok(new CartDto(userDetails.getCart()));
    }
}
