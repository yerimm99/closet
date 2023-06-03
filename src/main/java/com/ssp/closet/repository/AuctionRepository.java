package com.ssp.closet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssp.closet.dto.Auction;

public interface AuctionRepository extends JpaRepository<Auction, String> {
	//List<Auction> getAuctionResultList (String userId);
	
	@Query("update AUCTION a " + 
			"set a.PRICE = :maxPrice " +
			"where a.PRODUCTID = :productId")
	void updatePrice(Auction auction);
}
