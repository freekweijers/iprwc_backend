package com.IPRWC_webshop.webshop_IPRWC.controller;

import com.IPRWC_webshop.webshop_IPRWC.dao.UserDAO;
import com.IPRWC_webshop.webshop_IPRWC.model.Customer;
import com.IPRWC_webshop.webshop_IPRWC.model.User;
import com.IPRWC_webshop.webshop_IPRWC.service.CustomerService;
import com.IPRWC_webshop.webshop_IPRWC.service.JwtService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final JwtService jwtService;

    public CustomerController(CustomerService customerService, JwtService jwtService, UserDAO userDAO) {
        this.customerService = customerService;
        this.jwtService = jwtService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/logged-in")
    public ResponseEntity<Customer> getLoggedInCustomer(@RequestHeader("Authorization") String request) {
        String jwt = jwtService.getJwtFromToken(request);
        String userId = jwtService.extractUserId(jwt);
        Customer customer = customerService.getCustomerByUserId(UUID.fromString(userId));
        customer.setOptionalRegisteredUser(null);
        return ResponseEntity.ok(customer);
    }



    // Endpoint to create a new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") String request) {
        String jwt = jwtService.getJwtFromToken(request);
        String userId = jwtService.extractUserId(jwt);
//        customer.setOptionalRegisteredUser(userDAO.findById(UUID.fromString(userId)).orElseThrow());
        Customer createdCustomer = customerService.createCustomerWithUser(customer, UUID.fromString(userId));
        createdCustomer.setOptionalRegisteredUser(null);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/no-account")
    public ResponseEntity<Customer> createCustomerWithoutAccount(@RequestBody Customer customer) {
        customer.setOptionalRegisteredUser(null);
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody Customer customerDetails
    ) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
