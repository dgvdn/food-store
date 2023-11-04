package com.example.foodstore.service;

import com.example.foodstore.model.*;
import com.example.foodstore.repository.CartItemRepository;
import com.example.foodstore.repository.CartRepository;
import com.example.foodstore.repository.OrderDetailsRepository;
import com.example.foodstore.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public void createOrder(Cart cart, String paymentMethod, String deliveryAddress, String recipientName, String recipientPhone) {
        // Create and save the Order
        Order order = new Order();
        order.setPaymentMethod(paymentMethod);
        order.setDeliveryAddress(deliveryAddress);
        order.setRecipientName(recipientName);
        order.setRecipientPhone(recipientPhone);
        order.setTotalPrice(cart.getTotalPrice());
        order.setUserDetails(cart.getUserDetails());
        order.setOrderDate(LocalDate.now().toString());
        order.setStatus("Đang xử lý");
        order = orderRepository.save(order);

        Set<CartItem> cartItems = cart.getCartItems();
        List<OrderDetails> orderDetails = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setTotalPrice(cartItem.getTotalPrice());
            orderDetail.setOrder(order);  // Set the Order here
            orderDetailsRepository.save(orderDetail);
            orderDetails.add(orderDetail);
        }

        // Update the Order with OrderDetails
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);

        // Delete cart items and the cart itself
        cartItemRepository.deleteAll(cartItems);
        cartRepository.delete(cart);

    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }


    public List<Order> findAllByUserDetails(UserDetails userDetails) {
        return orderRepository.findAllByUserDetails(userDetails);
    }

}
