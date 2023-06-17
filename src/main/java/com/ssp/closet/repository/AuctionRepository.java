package com.ssp.closet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssp.closet.dto.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Integer> {
	//List<Auction> getAuctionResultList (String userId);
	
	Auction findByProductId(int productId);
	
	@Modifying
	@Query("update Auction a " + 
			"set a.price = :maxPrice " +
			"where a.productId = :productId")
	void updatePrice(@Param("productId")int productId, @Param("maxPrice")int price);
}
