package com.IPRWC_webshop.webshop_IPRWC.service;

import com.IPRWC_webshop.webshop_IPRWC.dao.UserDAO;
import com.IPRWC_webshop.webshop_IPRWC.model.Customer;
import com.IPRWC_webshop.webshop_IPRWC.model.User;
import com.IPRWC_webshop.webshop_IPRWC.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserDAO userDAO;


    public CustomerService(CustomerRepository customerRepository, UserDAO userDAO) {
        this.customerRepository = customerRepository;
        this.userDAO = userDAO;
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

    public Customer getCustomerByUserId(UUID userId) {
        return customerRepository.findByOptionalRegisteredUser_Id(userId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with user ID: " + userId));
    }

    // Create a new customer
    public Customer createCustomer(Customer customer) {
        // Add additional validation or logic if needed before saving
//        if(customer.getOptionalRegisteredUser() != null) {
//            UUID optionalRegisteredUserUUID = customer.getOptionalRegisteredUser().getId();
//            customer.setOptionalRegisteredUser(userDAO.findById(optionalRegisteredUserUUID).orElseThrow());
//        }
        return customerRepository.save(customer);
    }

    public Customer createCustomerWithUser(Customer customer, UUID userId) {
        customer.setOptionalRegisteredUser(userDAO.findById(userId).orElseThrow());
        return customerRepository.save(customer);
    }

    // Update an existing customer
    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        Customer existingCustomer = getCustomerById(customerId);

        // Update customer details based on changes
        existingCustomer.setName(customerDetails.getName());
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
