package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.BidId;

public interface BidRepository extends JpaRepository<Bid, BidId>{

	@Modifying
	@Query("update Bid b " + 
			"set b.bidPrice = :newPrice " +
			"where b.id.productId = :productId")			// JPQL 이용
	void updatePrice(@Param("productId")int productId, @Param("newPrice")int price);

	void deleteById(int productId);
	  
	@Query("update Bid b " + 
			"set b.bidResult = 1 " +
			"where b.id = :bidId")			// JPQL 이용
	void updateSuccessResult(BidId bidId);
	  
	@Query("update Bid b " + 
			"set b.bidResult = 2 " +
			"where b.id = :bidId")
	void updateFailResult(BidId bidId); //나눠야 할지 result 값을 받아서 쓰는 걸로 합칠
	  
	@Modifying
	@Query("select max(b.bidPrice) from Bid b " + 
			"where b.id.productId = :productId")
	int findMaxBidPrice(int productId);		 
	  
	//List<Bid> getBidResultList(String userId);
//	@Query("SELECT a FROM Auction a WHERE b.bidder.userId = :userId")
//	List<Bid> findResultByBidderUserId(String userId);
//	
	@Query("SELECT b FROM Bid b WHERE b.id.userId = :userId")
	Bid findByBidderUserId(String userId);
	
	@Query("SELECT b FROM Bid b WHERE b.id.userId = :userId and b.id.productId = :productId")
	Bid findByUserIdAndProductId(String userId, int productId);

}
