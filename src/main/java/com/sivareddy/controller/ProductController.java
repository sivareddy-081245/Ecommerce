package com.sivareddy.controller;

import com.sivareddy.model.Products;
import com.sivareddy.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v4/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/createProducts")
    public ResponseEntity<Products> createProducts(@RequestBody Products dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProducts(dto));
    }
}
