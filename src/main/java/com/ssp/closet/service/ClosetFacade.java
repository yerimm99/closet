package com.ssp.closet.service;

import java.util.List;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.BidId;
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
	Auction getAuction(int productId);
	void updateMaxPrice(int productId);
	public List<Auction> getAuctionList();
	List<Auction> getAuctionByCategoryId(String categoryId);
	
	void createBid(Bid bid);
	void updateBidPrice(int productId, String userId, int newPrice);
	boolean isBidPriceExists(int productId, int bidPrice);
	void deleteBid(int productId);
//	void updateSuccessResult(BidId bidId);
//	void updateFailResult(BidId bidId);
	Bid findMaxPrice(int productId);		 	  
//	List<Bid> getBidResultList(String userId);
	Bid getBid(String userId);
	Bid getBid(String userId, int productId);
//	
//	int countPeopleNum(int productId);
//	Meet getMeetDetail(int meetId);
//	List<Account> getMeetList(int productId);
  
	List<Category> getCategoryList();
	Category getCategory(String categoryId);

	
	void insertGroupbuy(Groupbuy groupbuy);
	Groupbuy getGroupbuyDetail(int productId);
	List<Groupbuy> getGroupbuyList();
	List<Groupbuy> getGroupbuyByCategoryId(String categoryId);
	void deleteGroupbuyByProductId(int productId);
	
	void createMeet(Meet meet);
	Meet findMeetByUserIdAndProductId(String userId, int productId);
	List<Meet> findByProductId(int productId);
	Integer getMeetCountByProductId(int productId);
	void deleteByUserIdAndProductId(String userId, int productId);
	
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
	
	Account getAccount(String userId);

	Account getAccount(String userId, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);

}
