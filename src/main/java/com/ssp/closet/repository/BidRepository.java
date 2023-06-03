package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssp.closet.dto.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer>{
	
	//void insertBid(Bid bid); save(bid)?
	
	@Query("update BID b " + 
			"set b.BIDPRICE = :newPrice " +
			"where b.BIDID = :bidId")			// JPQL 이용
	void updatePrice(@Param("bidId")int bidId, @Param("newPrice")int price);
	
	
	//void deleteBid(int bidId);
	void deleteByBidId(int bidId);
	  
	@Query("update BID b " + 
			"set b.BIDRESULT = 1 " +
			"where b.BIDID = :bidId")			// JPQL 이용
	void updateSuccessResult(int bidId);
	  
	@Query("update BID b " + 
			"set b.BIDRESULT = 2 " +
			"where b.BIDID = :bidId")
	void updateFailResult(int bidId); //나눠야 할지 result 값을 받아서 쓰는 걸로 합칠
	  
	@Query("select max(b.BIDPRICE) from BID b " + 
			"where b.PRODUCTID = :productId")
	int findMaxBidPrice(int productId);		 
	  
	//List<Bid> getBidResultList(String userId);
	List<Bid> findResultByUserId(String userId);
	
	Bid findByUserId(String userId);
	  

}
