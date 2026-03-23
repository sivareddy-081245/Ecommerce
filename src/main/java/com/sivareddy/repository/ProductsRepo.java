package com.sivareddy.repository;

import com.sivareddy.model.Products;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<Products,Integer> {
    List<Products> findByIdIn(List<Integer> ids, Sort sort);
}
