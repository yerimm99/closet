package com.ssp.closet.service;

import java.util.List;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bookmark;
import com.ssp.closet.dto.Category;
import com.ssp.closet.dto.GroupBuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.Order;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Review;

public interface ClosetFacade {
	
	void updateProduct(int productId);
	void deleteProduct(int productId);
	List<Product> getProductList();
	Product getProductDetail(int productId);
	
	void insertAuctionProduct(Auction auction);
	Auction getAuctionDetail(String productId);
	
	int countPeopleNum(int productId);
	Meet getMeetDetail(int meetId);
	List<Account> getMeetList(int productId);
  
	List<Category> getCategoryList();
	Category getCategory(String categoryId);

	
	int getPeopleNum(int productId);
	GroupBuy getGroupBuyDetail(int productId);
	
	void createMark(Bookmark bookmark);
	void deleteMark(String userId, int productId);

	void insertOrder(Order order);
	List<Order> getBuyList(String userId);
	List<Order> getSellList(String userId);
	
	void insertReview(Review review);
	void deleteReview(int orderId);
	List<Review> readReviewListByMe();
	List<Review> readReviewListToMe();
	
	//Account Connect
	
	Account getAccount(String username);

	Account getAccount(String username, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);

}
