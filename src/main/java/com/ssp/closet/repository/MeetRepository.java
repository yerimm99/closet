package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.MeetId;

public interface MeetRepository  extends JpaRepository<Meet, MeetId>{
	Meet findByUserIdAndProductId(String userId, int productId);
	List<Meet> findByProductId(int productId);
	Integer getMeetCountByProductId(int productId);
	void deleteByUserIdAndProductId(String userId, int productId);
	
}
