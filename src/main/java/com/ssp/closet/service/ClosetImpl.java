package com.ssp.closet.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ssp.closet.dao.AccountDao;
import com.ssp.closet.dao.AuctionDao;
import com.ssp.closet.dao.BookmarkDao;
import com.ssp.closet.dao.GroupbuyDao;
import com.ssp.closet.dao.ProductDao;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.Bookmark;
import com.ssp.closet.dto.Category;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.Order;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Review;

import com.ssp.closet.repository.AuctionRepository;
import com.ssp.closet.repository.BidRepository;
import com.ssp.closet.repository.ProductRepository;
import com.ssp.closet.service.ClosetFacade;
@Service
@Transactional
public class ClosetImpl implements ClosetFacade{


	@Autowired  
	private ProductRepository productRepository;
//	public List<Product> getProductListByType(int type, int status) {
//		return productRepository.findByTypeAndStatus(type, status);
//	}
	
	public List<Product> getProductList() {
		return productRepository.findAll();
	}

//	public Product getProductDetail(int productId) {
//		return productRepository.findByProductId(productId);
//	}
//
//	public void updateProduct(String description, String picture1, String picture2, String picture3, String picture4, int productId) {
//		productRepository.updateByProductId(description, picture1, picture2, picture3, picture4, productId);
//	};
//	public void deleteProduct(int productId) {
//		productRepository.deleteByProductIdIfGroupBuyPeopleNumIsOne(productId);
//	};
	@Autowired
	private AuctionRepository aucRepository;

	public void insertAuction(Auction auction) {
		aucRepository.save(auction);
	}
	public Auction getAuctionDetail(int productId) {
		return aucRepository.getReferenceById(productId); 
	}
	public void updateMaxPrice(Auction auction) {
		aucRepository.updatePrice(auction.getProductId(), findMaxPrice(auction.getProductId()));
	}
	
	@Autowired
	private BidRepository bidRepository;
	
	public void createBid(Bid bid) {
		bidRepository.save(bid);
	}

	public void updateBidPrice(int bidId, int newPrice) {
		bidRepository.updatePrice(bidId, newPrice);
	}

	public void deleteBid(int bidId) {
		bidRepository.deleteByBidId(bidId);
	}
	  
	public void updateSuccessResult(int productId) {
		bidRepository.updateSuccessResult(productId);
	}
	  
	public void updateFailResult(int productId) {
		bidRepository.updateFailResult(productId);
	}
	  
	public int findMaxPrice(int productId) {
		return bidRepository.findMaxBidPrice(productId);
	}	 
	  
	public List<Bid> getBidResultList(String userId) {
		return bidRepository.findResultByUserId(userId);
	}
	
	public Bid getBid(String userId) {
		return bidRepository.findByUserId(userId);
	}


	@Autowired
	private BookmarkDao bookmarkDao;

	public void createMark(Bookmark bookmark) {
		bookmarkDao.createMark(bookmark);
	}
	public void deleteMark(String userId, int productId) {
		bookmarkDao.deleteMark(userId, productId);
	}
	
	@Autowired
	@Qualifier("jpaAuctionDao")
	private AuctionDao auctionDao;
	
	public List<Auction> getAuctionList() {
		return auctionDao.getAuctionList();
	}
	

	@Autowired
	@Qualifier("jpaGroupbuyDao")
	private GroupbuyDao groupbuyDao;
	
	public void insertGroupbuy(Groupbuy groupbuy) {
		groupbuyDao.insertGroupbuy(groupbuy);
	}
	
	public Groupbuy getGroupbuyDetail(int productId) {
		return groupbuyDao.getGroupbuyDetail(productId);
	}
	
	public List<Groupbuy> getGroupbuyList(){
		return groupbuyDao.getGroupbuyList();
	}

	
//	@Autowired
//	private MeetDao meetDao;
//
//	public int countPeopleNum(int productId) {
//		return meetDao.countPeopleNum(productId);
//	}
//
//	public Meet getMeetDetail(int meetId) {
//		return meetDao.getMeetDetail(meetId);
//	}
//
//	public List<Account> getMeetList(int productId) {
//		return meetDao.getMeetList(productId);
//	}
	
	
	@Override
	public void insertOrder(Order order) {
		// TODO Auto-generated method stub

	}
	@Override
	public List<Order> getBuyList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Order> getSellList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void insertReview(Review review) {
		// TODO Auto-generated method stub

	}
	@Override
	public void deleteReview(int orderId) {
		// TODO Auto-generated method stub

	}
	@Override
	public List<Review> readReviewListByMe() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Review> readReviewListToMe() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Category getCategory(String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Autowired
	private AccountDao accountDao;
	
	@Override
	public Account getAccount(String name) {
		return accountDao.getAccount(name);
	}
	@Override
	public Account getAccount(String userId, String password) {
		return accountDao.getAccount(userId, password);
	}
	@Override
	public void insertAccount(Account account) {
		// TODO Auto-generated method stub

	}
	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub

	}
}