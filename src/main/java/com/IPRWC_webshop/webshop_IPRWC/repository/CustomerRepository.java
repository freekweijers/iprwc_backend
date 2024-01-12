package com.IPRWC_webshop.webshop_IPRWC.repository;

import com.IPRWC_webshop.webshop_IPRWC.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Define custom queries if needed
}
