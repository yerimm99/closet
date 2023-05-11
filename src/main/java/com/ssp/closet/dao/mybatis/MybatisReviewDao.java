package com.ssp.closet.dao.mybatis;

import java.util.List;

import com.ssp.closet.dao.ReviewDao;
import com.ssp.closet.dao.mybatis.mapper.ReviewMapper;
import com.ssp.closet.dto.Review;

public class MybatisReviewDao implements ReviewDao {
	
	private ReviewMapper reviewMapper;

	@Override
	public void insertReview(Review review) {
		reviewMapper.insertReview(review);
	}

	@Override
	public void deleteReview(int orderId) {
		reviewMapper.deleteReview(orderId);
	}

	@Override
	public List<Review> readReviewListByMe() {
		List<Review> list = reviewMapper.readReviewListByMe();
		return list;
	}

	@Override
	public List<Review> readReviewListToMe() {
		List<Review> list = reviewMapper.readReviewListToMe();
		return list;
	}
}
