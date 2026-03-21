package com.sivareddy.service;

import com.sivareddy.model.Products;
import com.sivareddy.repository.ProductsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public Products updateProducts(Products dto){
        Products data = getProducts(dto.getId());
        productsRepo.save(data);
        return data;
    }
    public void updateProductsFromDto(Products data,Products dto){
        BeanUtils.copyProperties(dto,data,"id");
    }
    public Products getProducts(int id){
        return productsRepo.findById(id).orElseThrow(() -> new RuntimeException("DATA NOT FOUND!"));
    }
    public List<Products> fetchProducts(){
        return productsRepo.findAll(Sort.by(Sort.Direction.DESC,"createdDtTime"));
    }

}
