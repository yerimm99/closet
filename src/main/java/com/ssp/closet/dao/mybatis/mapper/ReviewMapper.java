package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import com.ssp.closet.dto.Review;

public interface ReviewMapper {

	void insertReview(Review review);
	
	void deleteReview(int orderId);
	
	List<Review> readReviewListByMe(String userId);
	
	List<Review> readReviewListToMe(String suppId);
}
