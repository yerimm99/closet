package com.ssp.closet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.MeetId;

public interface MeetRepository  extends JpaRepository<Meet, MeetId>{
	@Query("SELECT m FROM Meet m WHERE m.meeter.userId = :userId and m.groupbuy.productId = :productId")
	Meet findByUserIdAndProductId(String userId, int productId);
}
