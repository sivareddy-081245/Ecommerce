package com.sivareddy.dto;

import com.sivareddy.model.Customer;
import com.sivareddy.model.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {

    private Integer id;
    private String orderId;
    private List<Products> products;
    private Integer customerId;
    private Integer productId;
}
