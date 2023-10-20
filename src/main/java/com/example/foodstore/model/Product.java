package com.example.foodstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long price;
    private String img;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subCategory;

    private boolean isDeleted;
    private boolean isAvailable;
//    private boolean isPromoted;
//    private boolean isDiscounted;
//    private boolean isRecommended;
//    private boolean isNew;
//    private boolean isPopular;
    public Product(String name, String description, Long price, String img, Category category, Subcategory subCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.category = category;
        this.subCategory = subCategory;
    }
}
