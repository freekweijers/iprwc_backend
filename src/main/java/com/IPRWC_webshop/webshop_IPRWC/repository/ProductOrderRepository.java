package com.IPRWC_webshop.webshop_IPRWC.repository;

import com.IPRWC_webshop.webshop_IPRWC.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    // Define custom queries if needed
}
