package com.example.foodstore.dto;

import com.example.foodstore.model.Cart;
import com.example.foodstore.model.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private int totalPrice;
    private int totalQuantity;
    private UserDetailsDto userDetails;
    private Set<CartItemDto> cartItems;

    public CartDto(Cart cart) {
        this.id = cart.getId();
        this.totalPrice = cart.getTotalPrice();
        this.totalQuantity = cart.getTotalQuantity();
        UserDetails userDetails = cart.getUserDetails();
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setName(userDetails.getName());
        userDetailsDto.setAddress(userDetails.getAddress());
        userDetailsDto.setPhone(userDetails.getPhone());
        userDetailsDto.setEmail(userDetails.getEmail());
        this.userDetails = userDetailsDto;
        this.cartItems = CartItemDto.convertToDto(cart.getCartItems());

    }
}
