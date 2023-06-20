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
import com.ssp.closet.dto.Delivery;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Review;

public interface ClosetFacade {

	List<Category> getCategoryList();
	Category getCategory(String categoryId);


	void insertAuction(Auction auction);
	Auction getAuction(int productId);
	void updateMaxPrice(int productId);
	public List<Auction> getAuctionList();
	List<Auction> getAuctionByCategoryId(String categoryId);
	List<Auction> findSellAuctionByAccount(Account account);
	public Auction findBuyAuctionByProductId(int productId);
	void deleteAuctionByProductId(int productId);

	void createBid(Bid bid);
	void updateBidPrice(int productId, String userId, int newPrice);
	boolean isBidPriceExists(int productId, int bidPrice);
	void deleteBid(int productId);
	//	void updateSuccessResult(BidId bidId);
	//	void updateFailResult(BidId bidId);
	Bid findMaxPrice(int productId);		 	  
	//	List<Bid> getBidResultList(String userId);
	List<Bid> getBid(String userId);
	Bid getBid(String userId, int productId);
	Integer countBidByProductId(int productId);


	void insertGroupbuy(Groupbuy groupbuy);
	Groupbuy getGroupbuyDetail(int productId);
	List<Groupbuy> getGroupbuyList();
	List<Groupbuy> getGroupbuyByCategoryId(String categoryId);
	List<Groupbuy> findSellGroupbuyByAccount(Account account);
	public Groupbuy findBuyGroupbuyByProductId(int productId);
	void deleteGroupbuyByProductId(int productId);
	

	void createMeet(Meet meet);
	Meet findMeetByUserIdAndProductId(String userId, int productId);
	List<Meet> findMeetByProductId(int productId);
	List<Meet> findMeetByUserId(String userId);
	Integer getMeetCountByProductId(int productId);
	void deleteByUserIdAndProductId(String userId, int productId);

	void createMark(Bookmark bookmark);
	void deleteMark(String userId, int productId);

	void insertOrder(Delivery order);
	List<Delivery> getBuyList(String userId);
	List<Delivery> getSellList(String userId);

	void insertReview(Review review);
	void deleteReview(int orderId);
	List<Review> readReviewListByMe();
	List<Review> readReviewListToMe();

	//Account Connect

	Account getAccount(String userId);

	Account getAccount(String userId, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);
	
	/*
	 * public static final List<Account> accountList = null;
	 * 
	 * public static Account getAccountByUserId(String userId) { // userId에 해당하는 계정을
	 * accountList에서 검색하여 반환 for (Account account : accountList) { if
	 * (account.getUserId().equals(userId)) { return account; } } return null; //
	 * 검색된 계정이 없는 경우 null 반환 }
	 * 
	 * public static Account getAccountByEmail(String email) { // email에 해당하는 계정을
	 * accountList에서 검색하여 반환 for (Account account : accountList) { if
	 * (account.getEmail().equals(email)) { return account; } } return null; // 검색된
	 * 계정이 없는 경우 null 반환 }
	 */
	List<Product> getTopRankingProducts();
}
