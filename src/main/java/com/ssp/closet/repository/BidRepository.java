package com.ssp.closet.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.BidId;

public interface BidRepository extends JpaRepository<Bid, BidId>{

	
	@Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Bid b " +
			"WHERE b.productId = :productId AND b.bidPrice = :bidPrice")
	boolean existsByProductIdAndBidPrice(@Param("productId") int productId, @Param("bidPrice") int bidPrice);

	void deleteByProductIdAndUserId(int productId, String userId);
	
	@Modifying
	@Transactional
	@Query("update Bid b " + 
			"set b.bidResult = 1 " +
			"where b.userId = :userId")
	void updateSuccessResult(String userId);
	  
	@Modifying
	@Transactional
	@Query("update Bid b " + 
			"set b.bidResult = 2 " +
			"where b.userId != :userId")
	void updateFailResult(String userId); 
	  
	Bid findTopByProductIdOrderByBidPriceDesc(int productId);

	List<Bid> findByUserId(String userId);
	
	Bid findByUserIdAndProductId(String userId, int productId);
	
	Integer countByProductId(int productId);
	

}
