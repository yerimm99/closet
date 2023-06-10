package com.ssp.closet.service;

import java.util.List;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bookmark;
import com.ssp.closet.dto.Category;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.Order;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Review;

public interface ClosetFacade {
	
//	void updateProduct(String description, String picture1, String picture2, String picture3, String picture4, int productId);
//	void deleteProduct(int productId);
	//List<Product> getProductListByType(int type, int status);
	List<Product> getProductList();
//	Product getProductDetail(int productId);
	
	void insertAuction(Auction auction);
	Auction getAuctionDetail(String productId);
//	
//	int countPeopleNum(int productId);
//	Meet getMeetDetail(int meetId);
//	List<Account> getMeetList(int productId);
  
	List<Category> getCategoryList();
	Category getCategory(String categoryId);

	
	void insertGroupbuy(Groupbuy groupbuy);
	Groupbuy getGroupbuyDetail(int productId);
	
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

	Account getAccount(String userId, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);

}
