package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.Review;
@Mapper
public interface SequenceMapper {

	void insertReview(Review review);
	
	void deleteReview(int orderId);
	
	List<Review> readReviewListByMe();
	
	List<Review> readReviewListToMe();
}
