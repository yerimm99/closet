package com.ssp.closet.repository;

import com.ssp.closet.dto.Review;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	List<Review> findByUserId(String userId);
	
	@Query("SELECT r.rating FROM Review r WHERE r.userId = :userId")
    List<Float> getRatingsByUserId(@Param("userId") String userId);
}
