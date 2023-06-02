package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssp.closet.dto.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer>{
	
	//void insertBid(Bid bid); save(bid)?

	@Query("update Bid b " + 
			"set b.bidPrice = :bidPrice " +
			"where b.bidId = :bidId")			// JPQL 이용
	void updateBidPrice(@Param("bidId")int bidId, @Param("bidPrice")int price);

	//void deleteBid(int bidId);
	void deleteByBidId(int bidId);
	  
	@Query("update Bid b " + 
			"set b.bidResult = 1 " +
			"where b.bidId = :bidId")			// JPQL 이용
	void updateSuccessResult(int bidId);
	  
	@Query("update Bid b " + 
			"set b.bidResult = 2 " +
			"where b.bidId = :bidId")
	void updateFailResult(int bidId); //나눠야 할지 result 값을 받아서 쓰는 걸로 합칠
	  
	@Query("select MAX(b.bidPrice) from Bid b, Auction a" + 
			"where b.productId = a.productId and b.productId = :productId")
	int findMaxBidPrice(int productId);		 
	  
	//List<Bid> getBidResultList(String userId);
	List<Bid> getByUserId(String userId);
	  

}
