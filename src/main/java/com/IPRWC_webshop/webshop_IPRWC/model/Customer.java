package com.IPRWC_webshop.webshop_IPRWC.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String email;
    // Other customer-related fields (name, address, etc.)
    @OneToOne(optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    private User optionalRegisteredUser;

    // Getters and setters, constructors
}
