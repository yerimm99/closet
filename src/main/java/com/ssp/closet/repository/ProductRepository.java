package com.ssp.closet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	Product findByProductId(int productId);
}