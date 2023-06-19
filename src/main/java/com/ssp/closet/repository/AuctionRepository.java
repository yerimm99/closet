package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
public interface AuctionRepository extends JpaRepository<Auction, Integer> {
	//List<Auction> getAuctionResultList (String userId);
	
	Auction findByProductId(int productId);
	
	@Modifying
	@Query("update Auction a " + 
			"set a.price = :maxPrice " +
			"where a.productId = :productId")
	void updatePrice(@Param("productId")int productId, @Param("maxPrice")int price);
	
	Page<Auction> findByCategoryId(String categoryId, Pageable pageable);
	
	void deleteByProductId(int productId);
	
	Page<Auction> findByAccount(Account account, Pageable pageable);
	
	Auction findByProductId(Integer productId);
}
