package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.BidId;
import com.ssp.closet.dto.Meet;

public interface BidRepository extends JpaRepository<Bid, BidId>{

	@Modifying
	@Query("update Bid b " + 
			"set b.bidPrice = :newPrice " +
			"where b.productId = :productId and b.userId = :userId")
	void updatePrice(@Param("productId")int productId, @Param("userId")String userId, @Param("newPrice")Integer price);

	@Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Bid b " +
			"WHERE b.productId = :productId AND b.bidPrice = :bidPrice")
	boolean existsByProductIdAndBidPrice(@Param("productId") int productId, @Param("bidPrice") int bidPrice);

	void deleteByProductId(int productId);
	  
	@Query("update Bid b " + 
			"set b.bidResult = 1 " +
			"where b.userId = :userId")
	void updateSuccessResult(String userId);
	  
	@Query("update Bid b " + 
			"set b.bidResult = 2 " +
			"where b.userId != :userId")
	void updateFailResult(String userId); 
	  
	Bid findTopByProductIdOrderByBidPriceDesc(int productId);
	  
	//List<Bid> getBidResultList(String userId);
//	@Query("SELECT a FROM Auction a WHERE b.bidder.userId = :userId")
//	List<Bid> findResultByBidderUserId(String userId);
//	
	//이거 List 로 받는게 맞는거 같아요!!
//	Bid findByUserId(String userId);
	List<Bid> findByUserId(String userId);
	
	Bid findByUserIdAndProductId(String userId, int productId);
	
	Integer countByProductId(int productId);
	

}
