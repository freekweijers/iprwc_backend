package com.IPRWC_webshop.webshop_IPRWC.controller;

import com.IPRWC_webshop.webshop_IPRWC.exception.OrderNotFoundException;
import com.IPRWC_webshop.webshop_IPRWC.model.ProductOrder;
import com.IPRWC_webshop.webshop_IPRWC.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    @Autowired
    public ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

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
    public ResponseEntity<ProductOrder> createOrder(@RequestBody ProductOrder productOrder) {
        ProductOrder createdProductOrder = productOrderService.createOrder(productOrder);
        return new ResponseEntity<>(createdProductOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ProductOrder> updateOrder(@PathVariable Long orderId, @RequestBody ProductOrder updatedProductOrder) {
        try {
            ProductOrder updated = productOrderService.updateOrder(orderId, updatedProductOrder);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        productOrderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

