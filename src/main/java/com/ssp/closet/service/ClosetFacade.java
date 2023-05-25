package com.ssp.closet.service;

import java.util.List;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Bookmark;
import com.ssp.closet.dto.GroupBuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Review;

public interface ClosetFacade {
	
	int getPeopleNum(int productId);
	GroupBuy getGroupBuyDetail(int productId);
	
	void createMark(Bookmark bookmark);
	void deleteMark(String userId, int productId);
	

	int countPeopleNum(int productId);
	Meet getMeetDetail(int meetId);
	List<Account> getMeetList(int productId);
	

	List<Product> getProductList();
	Product getProductDetail(int productId);
	

	void insertReview(Review review);
	void deleteReview(int orderId);
	List<Review> readReviewListByMe();
	List<Review> readReviewListToMe();
}
