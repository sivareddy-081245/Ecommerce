package com.sivareddy.controller;

import com.sivareddy.dto.OrdersDTO;
import com.sivareddy.model.Orders;
import com.sivareddy.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v4/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping("/placeOrders")
    public ResponseEntity<Orders> placeOrders(@RequestBody OrdersDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.placeOrders(dto));
    }
    @ExceptionHandler
    public ResponseEntity<Map> handleGlobalException(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("errorMsg", ex.getMessage()));
    }

    @PostMapping("/fetchOrdersList")
    public ResponseEntity<List<Orders>> fetchOrdersList(@RequestBody OrdersDTO dto){
        return ResponseEntity.ok(ordersService.fetchOrdersList(dto));
    }
}
