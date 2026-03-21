package com.sivareddy.controller;

import com.sivareddy.model.Products;
import com.sivareddy.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v4/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/createProducts")
    public ResponseEntity<Products> createProducts(@RequestBody Products dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProducts(dto));
    }
    @PutMapping("/updateProducts")
    public ResponseEntity<Products> updateProducts(@RequestBody Products dto){
        return ResponseEntity.ok(productService.updateProducts(dto));
    }
    @GetMapping("/getProducts/{id}")
    public ResponseEntity<Products> getProducts(@PathVariable int id){
        return ResponseEntity.ok(productService.getProducts(id));
    }
    @GetMapping("/fetchProducts")
    public ResponseEntity<List<Products>> fetchProducts(){
        return ResponseEntity.ok(productService.fetchProducts());
    }
    @ExceptionHandler
    public ResponseEntity<Map> handleGlobalException(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("errorMsg", ex.getMessage()));
    }
}
