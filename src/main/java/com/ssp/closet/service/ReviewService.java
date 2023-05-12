package com.ssp.closet.service;

import java.util.List;

import com.ssp.closet.dto.Review;

public interface ReviewService {

	void insertReview(Review review);
	void deleteReview(int orderId);
	List<Review> readReviewListByMe();
	List<Review> readReviewListToMe();
}
