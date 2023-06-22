package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	List<Product> findByPtypeAndStatus(int ptype, int status);
	List<Product> findByNameIgnoreCaseContaining(String keywords);
	//List<Product> findAll();
		
	@Query("SELECT p FROM Product p ORDER BY p.rank DESC")
	List<Product> findTopRankingProducts();
		
	Product findByProductId(int productId);
}