package com.sivareddy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Products {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String productName;
    private double productCost;
    private int quantity;
    private String skuCode;
    private LocalDateTime createdDtTime;
    private LocalDateTime expireDtTime;
}
