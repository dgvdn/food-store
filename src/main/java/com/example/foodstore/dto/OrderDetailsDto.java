package com.example.foodstore.dto;

import com.example.foodstore.model.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    private Long id;
    private int quantity;
    private int totalPrice;
    private ProductDto product;

    public OrderDetailsDto(OrderDetails orderDetails) {
        this.id = orderDetails.getId();
        this.quantity = orderDetails.getQuantity();
        this.totalPrice = orderDetails.getTotalPrice();
        this.product = new ProductDto(orderDetails.getProduct());
    }
    public static List<OrderDetailsDto> convertToDto(List<OrderDetails> orderDetails) {
        return orderDetails.stream().map(OrderDetailsDto::new).collect(java.util.stream.Collectors.toList());
    }
}
