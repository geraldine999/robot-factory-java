package de.tech26.robotfactory.robotfactoryjava.controller;

import de.tech26.robotfactory.robotfactoryjava.dtos.OrderRequest;
import de.tech26.robotfactory.robotfactoryjava.dtos.OrderResponse;
import de.tech26.robotfactory.robotfactoryjava.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody  OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }



}
