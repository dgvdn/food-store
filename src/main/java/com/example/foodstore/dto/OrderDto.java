package com.example.foodstore.dto;

import com.example.foodstore.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String paymentMethod;
    private String orderDate;
    private String status;
    private int totalPrice;
    private String deliveryAddress;
    private String deliveryDate;
    private String recipientName;
    private String recipientPhone;
    private UserDetailsDto userDetails;
    private List<OrderDetailsDto> orderDetails;
    public OrderDto(Order order) {
        this.id = order.getId();
        this.paymentMethod = order.getPaymentMethod();
        this.orderDate = order.getOrderDate();
        this.status = order.getStatus();
        this.totalPrice = order.getTotalPrice();
        this.deliveryAddress = order.getDeliveryAddress();
        this.deliveryDate = order.getDeliveryDate();
        this.recipientName = order.getRecipientName();
        this.recipientPhone = order.getRecipientPhone();
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setName(order.getUserDetails().getName());
        userDetailsDto.setAddress(order.getUserDetails().getAddress());
        userDetailsDto.setPhone(order.getUserDetails().getPhone());
        userDetailsDto.setEmail(order.getUserDetails().getEmail());
        this.userDetails = userDetailsDto;
        this.orderDetails = OrderDetailsDto.convertToDto(order.getOrderDetails());
    }
}
