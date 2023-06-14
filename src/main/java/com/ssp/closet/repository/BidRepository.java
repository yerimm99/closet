package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.BidId;

public interface BidRepository extends JpaRepository<Bid, BidId>{
	
	//void insertBid(Bid bid); save(bid)?
	
	@Query("update Bid b " + 
			"set b.bidPrice = :newPrice " +
			"where b.bidId = :bidId")			// JPQL 이용
	void updatePrice(@Param("bidId")BidId bidId, @Param("newPrice")int price);
	
	
	//void deleteBid(int bidId);
	void deleteByBidId(BidId bidId);
	  
	@Query("update Bid b " + 
			"set b.bidResult = 1 " +
			"where b.bidId = :bidId")			// JPQL 이용
	void updateSuccessResult(BidId bidId);
	  
	@Query("update Bid b " + 
			"set b.bidResult = 2 " +
			"where b.bidId = :bidId")
	void updateFailResult(BidId bidId); //나눠야 할지 result 값을 받아서 쓰는 걸로 합칠
	  
	@Query("select max(b.bidPrice) from Bid b " + 
			"where b.productId = :productId")
	int findMaxBidPrice(int productId);		 
	  
	//List<Bid> getBidResultList(String userId);
	List<Bid> findResultByUserId(String userId);
	
	Bid findByUserId(String userId);
	Bid findByUserIdAndProductId(String userId, int productId);

}
