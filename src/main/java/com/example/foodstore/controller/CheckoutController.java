package com.example.foodstore.controller;

import com.example.foodstore.model.Cart;
import com.example.foodstore.model.CartItem;
import com.example.foodstore.model.UserDetails;
import com.example.foodstore.service.UserDetailsService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/checkout")
public class CheckoutController {

    private final UserDetailsService userDetailsService;

    private static String sessionId;
    @PostMapping("/create-checkout-session")
    public ResponseEntity<String> checkout() throws StripeException {
        Stripe.apiKey = "sk_test_51MiqQ3AmreQjQSXI69Et0IFMRfyMteL0oNVEQJFC2bz5nQrXjQXfHeXdamduqeY8Mox5z2ukwYC4QmIXyX38X5kP00MsMUAA2Y";
        UserDetails userDetails = userDetailsService.findByUsername(UserDetailsService.getCurrentUsername());
        if (userDetails == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        if (userDetails.getCart().getCartItems().isEmpty()) {
            return ResponseEntity.badRequest().body("Cart is empty");
        }
        Cart userCart = userDetails.getCart();
        Set<CartItem> cartItemSet = userCart.getCartItems();
        List<CartItem> cart = new ArrayList<>(cartItemSet);
        List<SessionCreateParams.LineItem> items = new ArrayList<>();

        for (CartItem item : cart) {
            SessionCreateParams.LineItem lineItem = new SessionCreateParams.LineItem.Builder()
                    .setPriceData(
                            new SessionCreateParams.LineItem.PriceData.Builder()
                                    .setCurrency("VND")
                                    .setUnitAmount((long) (item.getProduct().getPrice()))
                                    .setProductData(
                                            new SessionCreateParams.LineItem.PriceData.ProductData.Builder()
                                                    .setName(item.getProduct().getName())
                                                    .build())
                                    .build())
                    .setQuantity((long) item.getQuantity())
                    .build();

            items.add(lineItem);
        }
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setLocale(SessionCreateParams.Locale.VI)
                        .setSuccessUrl("http://localhost:3000/success")
                        .setCancelUrl("http://localhost:3000/cancel")
                        .setCustomerEmail(userDetails.getEmail())
                        .addAllLineItem(items)
                        .build();
        try{
            Session session = Session.create(params);
            sessionId = session.getId();
            System.out.printf(sessionId);
            System.out.printf("Session URl: %s\n", session.getUrl());
           return ResponseEntity.ok().body(session.getUrl());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // check checkout completed
    @GetMapping("/check-checkout")
    public ResponseEntity<String> checkCheckout() throws StripeException {
        Stripe.apiKey = "sk_test_51MiqQ3AmreQjQSXI69Et0IFMRfyMteL0oNVEQJFC2bz5nQrXjQXfHeXdamduqeY8Mox5z2ukwYC4QmIXyX38X5kP00MsMUAA2Y";
        Session session = Session.retrieve(sessionId);
        if (session.getPaymentStatus().equals("paid")) {
            return ResponseEntity.ok().body("Checkout completed");
        }   
        return ResponseEntity.badRequest().body("Checkout not completed");
    }




}
