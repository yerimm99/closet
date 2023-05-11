package com.ssp.closet.service;
//package ssp.closet.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import ssp.closet.dao.BookmarkDao;
//import ssp.closet.dao.ReviewDao;
//import ssp.closet.dto.Review;
//
//public class ReviewServiceImpl implements ReviewService {
//
//	@Autowired
//	private ReviewDao reviewDao;
//	
//	@Override
//	public void insertReview(Review review) {
//		reviewDao.insertReview(review);
//	}
//	
//	@Override
//	public void deleteReview(int orderId) {
//		reviewDao.deleteReview(orderId);
//	}
//	
//	@Override
//	public List<Review> readReviewListByMe() {
//		List<Review> list = reviewDao.readReviewListByMe();
//		return list;
//	}
//	
//	@Override
//	public List<Review> readReviewListToMe() {
//		List<Review> list = reviewDao.readReviewListToMe();
//		return list;
//	}	
//}
