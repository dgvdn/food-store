package com.example.foodstore.controller;

import com.example.foodstore.dto.OrderDto;
import com.example.foodstore.model.Order;
import com.example.foodstore.model.UserDetails;
import com.example.foodstore.service.OrderService;
import com.example.foodstore.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    //get list order by user
    @GetMapping("/user")
    public ResponseEntity<List<OrderDto>> getOrdersByUser() {
        UserDetails userDetails = userDetailsService.findByUsername(UserDetailsService.getCurrentUsername());
        if (userDetails == null) {
            return ResponseEntity.status(404).body(null);
        }

        List<Order> userOrders = orderService.findAllByUserDetails(userDetails);

        // Reverse the order of the list
        Collections.reverse(userOrders);

        // Convert the list of orders to a list of DTOs
        List<OrderDto> orderDtos = userOrders.stream().map(OrderDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(orderDtos);
    }


}
