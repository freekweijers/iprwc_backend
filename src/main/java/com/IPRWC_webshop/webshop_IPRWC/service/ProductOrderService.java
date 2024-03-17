package com.IPRWC_webshop.webshop_IPRWC.service;

import com.IPRWC_webshop.webshop_IPRWC.exception.OrderNotFoundException;
import com.IPRWC_webshop.webshop_IPRWC.model.Customer;
import com.IPRWC_webshop.webshop_IPRWC.model.ProductOrder;
import com.IPRWC_webshop.webshop_IPRWC.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final CustomerService customerService;

    @Autowired
    public ProductOrderService(ProductOrderRepository productOrderRepository, CustomerService customerService) {
        this.productOrderRepository = productOrderRepository;
        this.customerService = customerService;
    }

    public List<ProductOrder> getAllOrders() {
        return productOrderRepository.findAll();
    }

    public Optional<ProductOrder> getOrderById(Long orderId) {
        return productOrderRepository.findById(orderId);
    }

    public List<ProductOrder> getOrdersByUserId(UUID userId) {
        Customer customerFromDatabase = customerService.getCustomerByUserId(userId);
        return productOrderRepository.findByCustomer(customerFromDatabase);
    }

    public ProductOrder createOrder(ProductOrder productOrder) {
        // Additional logic before saving (if needed)
        productOrder.setCurrentDateAndNewOrder();
        return productOrderRepository.save(productOrder);
    }

    public ProductOrder createOrderWithoutAccount(ProductOrder productOrder) {
        // Additional logic before saving (if needed)
        Customer customerFromDatabase = customerService.getCustomerById(productOrder.getCustomer().getId());
        if (customerFromDatabase.getOptionalRegisteredUser() == null) {
            productOrder.setCustomer(customerFromDatabase);
        } else {
            throw new IllegalArgumentException("Customer with ID: " + productOrder.getCustomer().getId() + " already has an account");
        }
        productOrder.setCurrentDateAndNewOrder();
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

    public ProductOrder updateOrderStatus(Long orderId, String status) throws OrderNotFoundException {
        ProductOrder productOrder = productOrderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
        productOrder.setStatus(status);
        return productOrderRepository.save(productOrder);
    }
}
