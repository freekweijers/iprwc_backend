package com.IPRWC_webshop.webshop_IPRWC.repository;

import com.IPRWC_webshop.webshop_IPRWC.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByOptionalRegisteredUser_Id(UUID userId);
    // Define custom queries if needed
}
