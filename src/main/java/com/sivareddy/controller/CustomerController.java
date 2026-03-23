package com.sivareddy.controller;

import com.sivareddy.model.Customer;
import com.sivareddy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v4/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(dto));
    }
    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id){
        return ResponseEntity.ok(customerService.getCustomer(id));
    }
    @PutMapping("/updateCustomer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.updateCustomer(dto));
    }
}
