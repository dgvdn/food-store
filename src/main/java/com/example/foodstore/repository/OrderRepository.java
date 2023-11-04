package com.example.foodstore.repository;

import com.example.foodstore.model.Order;
import com.example.foodstore.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserDetails(UserDetails userDetails);
}
