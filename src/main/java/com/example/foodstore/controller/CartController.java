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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getCart() {
        UserDetails userDetails = userDetailsService.findByUsername(UserDetailsService.getCurrentUsername());

        if (userDetails == null || userDetails.getCart() == null) {
            // If userDetails or the cart is null, it means the cart does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Cart not found\"}");
        }

        return ResponseEntity.ok(new CartDto(userDetails.getCart()));
    }


    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return ResponseEntity.ok("Product removed from cart");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCartItem(Long cartItemId, int quantity) {
        cartService.updateCartItem(cartItemId, quantity);
        return ResponseEntity.ok("Cart updated");
    }
}
