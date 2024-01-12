package com.IPRWC_webshop.webshop_IPRWC.service;

import com.IPRWC_webshop.webshop_IPRWC.exception.OrderNotFoundException;
import com.IPRWC_webshop.webshop_IPRWC.model.ProductOrder;
import com.IPRWC_webshop.webshop_IPRWC.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;

    @Autowired
    public ProductOrderService(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    public List<ProductOrder> getAllOrders() {
        return productOrderRepository.findAll();
    }

    public Optional<ProductOrder> getOrderById(Long orderId) {
        return productOrderRepository.findById(orderId);
    }

    public ProductOrder createOrder(ProductOrder productOrder) {
        // Additional logic before saving (if needed)
        return productOrderRepository.save(productOrder);
    }

    public ProductOrder updateOrder(Long orderId, ProductOrder updatedProductOrder) throws OrderNotFoundException {
        // Check if the order with given ID exists
        if (productOrderRepository.existsById(orderId)) {
            updatedProductOrder.setId(orderId); // Ensure the ID is set for update
            return productOrderRepository.save(updatedProductOrder);
        } else {
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }
    }

    public void deleteOrder(Long orderId) {
        productOrderRepository.deleteById(orderId);
    }
}
