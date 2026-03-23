package com.sivareddy.repository;

import com.sivareddy.model.Orders;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer> {
    @Query("SELECT q FROM Orders q WHERE q.orderId = :orderId ORDER BY q.createdDtTime DESC")
    List<Orders> findByOrderId(String orderId);

    @Query("SELECT q FROM Orders q WHERE q.customerInfo.id = :customerId ORDER BY q.createdDtTime DESC")
    List<Orders> findByCustomerId(@Param("customerId") Integer customerId);

    @Query("SELECT q FROM Orders q JOIN q.productsList p WHERE p.id = :productId ORDER BY q.createdDtTime DESC")
    List<Orders> findByProductId(@Param("productId") Integer productId);
}
