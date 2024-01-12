package com.IPRWC_webshop.webshop_IPRWC.repository;

import com.IPRWC_webshop.webshop_IPRWC.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String categoryName);
    // Define custom queries if needed
}