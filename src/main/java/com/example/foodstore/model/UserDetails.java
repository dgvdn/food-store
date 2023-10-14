package com.example.foodstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable=false)
    private String address;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
