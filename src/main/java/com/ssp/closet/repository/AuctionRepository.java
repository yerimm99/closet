package com.ssp.closet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Auction;

public interface AuctionRepository extends JpaRepository<Auction, String> {
	//List<Auction> getAuctionResultList (String userId);

}
