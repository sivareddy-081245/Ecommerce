package com.sivareddy.service;

import com.sivareddy.dto.OrdersDTO;
import com.sivareddy.model.Customer;
import com.sivareddy.model.Orders;
import com.sivareddy.model.Products;
import com.sivareddy.repository.OrdersRepo;
import com.sivareddy.repository.ProductsRepo;
import com.sivareddy.utils.EcommerceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepo ordersRepo;
    private final ProductService productService;
    private final ProductsRepo productsRepo;
    private final CustomerService customerService;

    public Orders placeOrders(OrdersDTO dto) {
        Customer customerInfo = customerService.getCustomer(dto.getCustomerId());
        List<Products> products = checkProductInventory(dto);
        Orders data = new Orders();
        data.setOrderId(EcommerceUtils.generateOrderId());
        data.setCreatedDtTime(LocalDateTime.now());
        data.setProductsList(products);
        data.setAmount(products.stream().mapToDouble(i -> i.getQuantity() * i.getProductCost()).sum());
        data.setCustomerInfo(customerInfo);
        ordersRepo.save(data);
        return data;
    }
    public List<Products> checkProductInventory(OrdersDTO dto){
        List<Products> response = new ArrayList<>();
        List<Products> products = productService.getProductsBasedOnIds(dto.getProducts().stream().map(Products::getId).toList());
        for(Products prod : products){
            int quantity = dto.getProducts().stream().filter(i -> i.getId() == prod.getId()).map(Products::getQuantity).findFirst().orElse(0);
            if(prod.getQuantity() >= quantity){
                Products data = Products.builder()
                        .id(prod.getId())
                        .productCost(prod.getProductCost())
                        .productName(prod.getProductName())
                        .skuCode(prod.getSkuCode())
                        .quantity(quantity)
                        .build();
                response.add(data);
                prod.setQuantity(prod.getQuantity() - quantity);
                productsRepo.save(prod);
            }else {
                throw new RuntimeException(prod.getProductName() + " Out Of Stack,Please Try After Sometime!");
            }
        }
        return response;
    }
    public List<Orders> fetchOrdersList(OrdersDTO dto){
        if(dto.getOrderId() != null){
            return ordersRepo.findByOrderId(dto.getOrderId());
        }else if(dto.getCustomerId() != null){
            return ordersRepo.findByCustomerId(dto.getCustomerId());
        }else if(dto.getProductId() != null){
            return ordersRepo.findByProductId(dto.getProductId());
        }else{
            return ordersRepo.findAll(Sort.by(Sort.Direction.DESC,"createdDtTime"));
        }
    }
}
