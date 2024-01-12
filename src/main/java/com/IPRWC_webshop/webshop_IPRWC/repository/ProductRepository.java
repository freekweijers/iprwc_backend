package com.IPRWC_webshop.webshop_IPRWC.repository;

import com.IPRWC_webshop.webshop_IPRWC.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Define custom queries if needed
}
