package com.ssp.closet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dto.Review;

public interface ReviewDao {
	
	//리뷰 등록
	void insertReview(Review review);
	
	//리뷰 삭제
	void deleteReview(int orderId);
	
	//내가 작성한 리뷰 조회
	List<Review> readReviewListByMe(String userId) throws DataAccessException;
	
	//내가 판매한 상품에 대한 리뷰 조회
	List<Review> readReviewListToMe(String suppId) throws DataAccessException;
}
