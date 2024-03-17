package com.IPRWC_webshop.webshop_IPRWC.controller;

import com.IPRWC_webshop.webshop_IPRWC.dao.UserDAO;
import com.IPRWC_webshop.webshop_IPRWC.exception.OrderNotFoundException;
import com.IPRWC_webshop.webshop_IPRWC.model.ProductOrder;
import com.IPRWC_webshop.webshop_IPRWC.model.User;
import com.IPRWC_webshop.webshop_IPRWC.service.CustomerService;
import com.IPRWC_webshop.webshop_IPRWC.service.JwtService;
import com.IPRWC_webshop.webshop_IPRWC.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class ProductOrderController {

    private final ProductOrderService productOrderService;
    private final JwtService jwtService;
    private final CustomerService customerService;


    @Autowired
    public ProductOrderController(ProductOrderService productOrderService, JwtService jwtService, UserDAO userDAO, CustomerService customerService) {
        this.productOrderService = productOrderService;
        this.jwtService = jwtService;
        this.customerService = customerService;
    }

    @GetMapping("/logged-in")
    public ResponseEntity<List<ProductOrder>> getLoggedInCustomerOrders(@RequestHeader("Authorization") String request) {
        String jwt = jwtService.getJwtFromToken(request);
        String userId = jwtService.extractUserId(jwt);
        List<ProductOrder> productOrders = productOrderService.getOrdersByUserId(UUID.fromString(userId));
        return new ResponseEntity<>(productOrders, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ProductOrder>> getAllOrders() {
        List<ProductOrder> productOrders = productOrderService.getAllOrders();
        return new ResponseEntity<>(productOrders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ProductOrder> getOrderById(@PathVariable Long orderId) {
        return productOrderService.getOrderById(orderId)
                .map(productOrder -> new ResponseEntity<>(productOrder, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProductOrder> createOrder(@RequestBody ProductOrder productOrder, @RequestHeader("Authorization") String request) {
        String jwt = jwtService.getJwtFromToken(request);
        String userId = jwtService.extractUserId(jwt);
//        productOrder.getCustomer().setOptionalRegisteredUser(userDAO.findById(UUID.fromString(userId)).orElseThrow());
        productOrder.setCustomer(customerService.getCustomerByUserId(UUID.fromString(userId)));
        ProductOrder createdProductOrder = productOrderService.createOrder(productOrder);
        return new ResponseEntity<>(createdProductOrder, HttpStatus.CREATED);
    }

    @PostMapping("/no-account")
    public ResponseEntity<ProductOrder> createOrderWithoutAccount(@RequestBody ProductOrder productOrder) {
        ProductOrder createdProductOrder = productOrderService.createOrderWithoutAccount(productOrder);
        return new ResponseEntity<>(createdProductOrder, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{orderId}")
    public ResponseEntity<ProductOrder> updateOrder(@PathVariable Long orderId, @RequestBody ProductOrder updatedProductOrder) {
        try {
            ProductOrder updated = productOrderService.updateOrder(orderId, updatedProductOrder);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<ProductOrder> updateOrderStatus(@PathVariable Long orderId, @RequestBody String status) {
        try {
            ProductOrder updated = productOrderService.updateOrderStatus(orderId, status);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        productOrderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

