package com.example.foodstore.dto;

import com.example.foodstore.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private int quantity;
    private int totalPrice;
    private ProductDto product;

    public CartItemDto(CartItem cartItem) {
        this.id = cartItem.getId();
        this.quantity = cartItem.getQuantity();
        this.totalPrice = cartItem.getTotalPrice();
        this.product = new ProductDto(cartItem.getProduct());
    }

    public static Set<CartItemDto> convertToDto(Set<CartItem> cartItems) {
        return cartItems.stream().map(CartItemDto::new).collect(Collectors.toSet());
    }
}
