package com.ssp.closet.dao;

import java.util.List;

import com.ssp.closet.dto.Review;

public interface ReviewDao {
	
	void insertReview(Review review);
	
	void deleteReview(int orderId);
	
	List<Review> readReviewListByMe();
	
	List<Review> readReviewListToMe();
}
