package com.ssp.closet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssp.closet.dto.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Integer> {
	//List<Auction> getAuctionResultList (String userId);
	
	@Query("update AUCTION a " + 
			"set a.PRICE = :maxPrice " +
			"where a.PRODUCTID = :productId")
	void updatePrice(@Param("productId")int productId, @Param("maxPrice")int price);
}
