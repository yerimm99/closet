package com.ssp.closet.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
public interface AuctionRepository extends JpaRepository<Auction, Integer> {

	Auction findByProductId(int productId);
	List<Auction> findByAccountOrderByRegisterDateDesc(Account account);
	
	@Modifying
	@Transactional
	@Query("update Auction a " + 
			"set a.price = :maxPrice " +
			"where a.productId = :productId")
	void updatePrice(@Param("productId")int productId, @Param("maxPrice")int price);
	
	List<Auction> findByCategoryIdOrderByRegisterDateDesc(String categoryId);
	
	List<Auction> findAllByOrderByRegisterDateDesc();
	
	void deleteByProductId(int productId);
	

	@Query("SELECT a FROM Auction a WHERE a.endDate <= :currentTime")
    List<Auction> findEndedAuctions(@Param("currentTime") LocalDateTime currentTime);
	
	//나현 추가
	
	@Query("SELECT a FROM Auction a WHERE rownum <= 4 Order by a.registerDate desc")
	List<Auction> findTop4OrderByRegisterDate();
	
	List<Auction> findByUsedOrderByRegisterDateDesc(int used);
	List<Auction> findByCategoryIdAndUsedOrderByRegisterDateDesc(String categoryId, int used);
}
