package com.example.foodstore.service;

import com.example.foodstore.model.Cart;
import com.example.foodstore.model.CartItem;
import com.example.foodstore.model.UserDetails;
import com.example.foodstore.repository.CartItemRepository;
import com.example.foodstore.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UserDetailsService userDetailsService;
    private final ProductService productService;

   public void addToCart(Long productId, int quantity) {
       UserDetails userDetails = userDetailsService.findByUsername(UserDetailsService.getCurrentUsername());
       Cart cart = userDetails.getCart();
       if (cart == null) {
           cart = new Cart();
           cart.setUserDetails(userDetails);
           cartRepository.save(cart);
       }
       Set<CartItem> cartItems = cart.getCartItems();
       CartItem cartItem = findCartItemByProductId(cartItems, productId);
       if(cartItems == null){
           cartItems = new HashSet<>();
           cartItem = new CartItem();
           cartItem.setQuantity(quantity);
           cartItem.setProduct(productService.findById(productId));
           cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
           cartItem.setCart(cart);
           cartItems.add(cartItem);
           cart.setCartItems(cartItems);
           cartRepository.save(cart);
         }else if(cartItem == null){
           cartItem = new CartItem();
           cartItem.setQuantity(quantity);
           cartItem.setProduct(productService.findById(productId));
           cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
           cartItem.setCart(cart);
           cartItems.add(cartItem);
           cart.setCartItems(cartItems);
           cartRepository.save(cart);
       }else {
           cartItem.setQuantity(cartItem.getQuantity() + quantity);
           cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
           cartItemRepository.save(cartItem);
       }
       cartItems.add(cartItem);
       cart.setCartItems(cartItems);
       cart.setTotalQuantity(cart.getTotalQuantity() + quantity);
       cart.setTotalPrice(cart.getTotalPrice() + cartItem.getTotalPrice());
       cart.setUserDetails(userDetails);
       cartRepository.save(cart);
   }

    private CartItem findCartItemByProductId(Set<CartItem> cartItems, Long productId) {
       if (cartItems == null) {
           return null;
       }
         for (CartItem cartItem : cartItems) {
              if (cartItem.getProduct().getId().equals(productId)) {
                return cartItem;
              }
         }
            return null;
    }

    public Cart getCart() {
        UserDetails userDetails = userDetailsService.findByUsername(UserDetailsService.getCurrentUsername());
        return userDetails.getCart();
    }

    public void removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        if (cartItem == null) {
            return;
        }
        Cart cart = cartItem.getCart();
        cart.setTotalQuantity(cart.getTotalQuantity() - cartItem.getQuantity());
        cart.setTotalPrice(cart.getTotalPrice() - cartItem.getTotalPrice());
        cart.getCartItems().remove(cartItem);
        cartRepository.save(cart);
        cartItemRepository.delete(cartItem);
    }

    public void updateCartItem(Long id, int quantity) {
        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        if (cartItem == null) {
            return;
        }
        Cart cart = cartItem.getCart();
        cart.setTotalQuantity(cart.getTotalQuantity() - cartItem.getQuantity() + quantity);
        cart.setTotalPrice(cart.getTotalPrice() - cartItem.getTotalPrice() + cartItem.getProduct().getPrice() * quantity);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getProduct().getPrice() * quantity);
        cartRepository.save(cart);
        cartItemRepository.save(cartItem);
    }
}
