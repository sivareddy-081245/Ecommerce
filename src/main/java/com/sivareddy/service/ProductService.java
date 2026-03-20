package com.sivareddy.service;

import com.sivareddy.model.Products;
import com.sivareddy.repository.ProductsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepo productsRepo;

    public Products createProducts(Products dto){
        Products data = Products.builder()
                .productName(dto.getProductName())
                .productCost(dto.getProductCost())
                .createdDtTime(LocalDateTime.now())
                .expireDtTime(LocalDateTime.now().plusDays(10))
                .skuCode(dto.getSkuCode())
                .quantity(dto.getQuantity())
                .build();
        productsRepo.save(data);
        return data;
    }
}
