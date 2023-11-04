package com.example.foodstore.controller;

import com.example.foodstore.dto.OrderDto;
import com.example.foodstore.model.Order;
import com.example.foodstore.model.UserDetails;
import com.example.foodstore.service.OrderService;
import com.example.foodstore.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/create")
    public String createOrder(String paymentMethod, String address, String name, String phone) {
        UserDetails userDetails = userDetailsService.findByUsername(UserDetailsService.getCurrentUsername());
        if (userDetails == null) {
            return "User not found";
        }
        if (userDetails.getCart().getCartItems().isEmpty()) {
            return "Cart is empty";
        }
        orderService.createOrder(userDetails.getCart(), paymentMethod, address, name, phone);
        return "Order created";
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        // Your code to retrieve and return the order by ID
        return ResponseEntity.ok(new OrderDto(orderService.findById(id)));
    }
}
