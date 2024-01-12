package com.IPRWC_webshop.webshop_IPRWC.service;

import com.IPRWC_webshop.webshop_IPRWC.model.Customer;
import com.IPRWC_webshop.webshop_IPRWC.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Retrieve all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Retrieve a specific customer by ID
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + customerId));
    }

    // Create a new customer
    public Customer createCustomer(Customer customer) {
        // Add additional validation or logic if needed before saving
        return customerRepository.save(customer);
    }

    // Update an existing customer
    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        Customer existingCustomer = getCustomerById(customerId);

        // Update customer details based on changes
        existingCustomer.setUsername(customerDetails.getUsername());
        existingCustomer.setEmail(customerDetails.getEmail());
        // Update other fields as needed

        return customerRepository.save(existingCustomer);
    }

    // Delete a customer by ID
    public void deleteCustomer(Long customerId) {
        Customer existingCustomer = getCustomerById(customerId);
        customerRepository.delete(existingCustomer);
    }
}
