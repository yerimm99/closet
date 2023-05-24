package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Auction;

public interface AuctionRepository extends JpaRepository<Auction, String> {
	List<Auction> getAuctionResultList (String userId);

}
