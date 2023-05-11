package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import com.ssp.closet.dto.Review;

public interface CategoryMapper {

	void insertReview(Review review);
	
	void deleteReview(int orderId);
	
	List<Review> readReviewListByMe();
	
	List<Review> readReviewListToMe();
}
