package com.ssp.closet.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssp.closet.dto.Auction;
public interface AuctionRepository extends JpaRepository<Auction, Integer> {
	//List<Auction> getAuctionResultList (String userId);
	
	Auction findByProductId(int productId);
	List<Auction> findByUserId(String suppId);
	
	@Modifying
	@Transactional
	@Query("update Auction a " + 
			"set a.price = :maxPrice " +
			"where a.productId = :productId")
	void updatePrice(@Param("productId")int productId, @Param("maxPrice")int price);
	
	List<Auction> findByCategoryId(String categoryId);
	
	void deleteByProductId(int productId);
	
	@Query("SELECT a FROM Auction a WHERE a.endDate <= :currentTime")
    List<Auction> findEndedAuctions(@Param("currentTime") LocalDateTime currentTime);
	
}
