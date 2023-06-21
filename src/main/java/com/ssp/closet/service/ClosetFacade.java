package com.ssp.closet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.Bookmark;
import com.ssp.closet.dto.Category;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Delivery;
import com.ssp.closet.dto.Review;

public interface ClosetFacade {
	
	//카테고리
	List<Category> getCategoryList();
	Category getCategory(String categoryId);

	List<Product> getProductList(int type, int status);
	Product getProduct(int productId);
	
	
	//경매
	void insertAuction(Auction auction);
	Auction getAuction(int productId);
	void updateMaxPrice(int productId);
	List<Auction> getAuctionList();
	List<Auction> getAuctionByCategoryId(String categoryId);
	List<Auction> searchAuctionList(String keywords);
	//추가
	List<Auction> getAuctionByUsed(int used);
	List<Auction> getAuctionByCategoryIdAndUsed(String categoryId, int used);
	List<Auction> findSellAuctionByAccount(Account account);
	void deleteAuctionByProductId(int productId);

	void scheduleAuctionEnd(Auction auction);
	void closedAuctionBySupp(Auction auction);
	
	List<Auction> findTop4AuctionOrderByRegisterDate();
	
	
	//입찰
	void createBid(Bid bid);
	void insertBid(Bid bid);
	boolean isBidPriceExists(int productId, int bidPrice);
	void deleteBid(int productId, String userId);
	void updateResult(String userId, int productId);
	Bid findMaxPrice(int productId);		 	  
	List<Bid> getBid(String userId);
	Bid getBid(String userId, int productId);
	Integer countBidByProductId(int productId);

	
	//공동구매
	void insertGroupbuy(Groupbuy groupbuy);
	Groupbuy getGroupbuyDetail(int productId);
	List<Groupbuy> getGroupbuyList();
	List<Groupbuy> getGroupbuyByCategoryId(String categoryId);
	List<Groupbuy> findSellGroupbuyByAccount(Account account);
	Groupbuy findBuyGroupbuyByProductId(int productId);
	void deleteGroupbuyByProductId(int productId);
	List<Groupbuy> searchGroupbuyList(String keywords);

	void scheduleGroupbuyEnd (Groupbuy groupbuy);

	List<Groupbuy> findTop4GroupbuyOrderByRegisterDate();

	
	//공구참여
	void insertMeet(Meet meet);
	Meet findMeetByUserIdAndProductId(String userId, int productId);
	List<Meet> findMeetByProductId(int productId);
	List<Meet> findMeetByUserId(String userId);
	Integer getMeetCountByProductId(int productId);
	void deleteByUserIdAndProductId(String userId, int productId);
	
	
	//관심상품
	void createMark(Bookmark bookmark);
	void deleteMark(String userId, int productId);
	
	
	//주문
	public void createDelivery(Delivery delivery);
	List<Delivery> getOrderList(String userId);
	Delivery getOrder(int orderId);
	
	
	//리뷰
	void insertReview(Review review);
	void deleteReview(int orderId);
	List<Review> readReviewListByMe();
	List<Review> readReviewListToMe();

	
	//계정
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
	/* List<Product> getTopRankingProducts(); */
}
