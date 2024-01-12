package com.IPRWC_webshop.webshop_IPRWC.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private int stock;
    private String description;
    private String imageUri;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String name, double price, int stock, String description, String imageUri, Category category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.imageUri = imageUri;
        this.category = category;
    }


    // Getters and setters
}
