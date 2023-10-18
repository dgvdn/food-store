package com.example.foodstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subcategory")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subcategory_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private boolean isActive;
    private boolean isDeleted;

    public Subcategory(String name, Category category) {
        this.name = name;
        this.category = category;
        this.isActive = true;
        this.isDeleted = false;
    }
}
