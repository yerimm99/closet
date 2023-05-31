package com.ssp.closet.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssp.closet.dao.AuctionDao;
import com.ssp.closet.dao.BookmarkDao;
import com.ssp.closet.dao.GroupBuyDao;
import com.ssp.closet.dao.MeetDao;
import com.ssp.closet.dao.ProductDao;
import com.ssp.closet.dao.ReviewDao;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bookmark;
import com.ssp.closet.dto.GroupBuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Review;

@Service
@Transactional
public class ClosetImpl implements ClosetFacade{
	
	@Autowired
	private ProductDao productDao;
	
	public List<Product> getProductList() {
		return productDao.getProductList();
	}
	
	public Product getProductDetail(int productId) {
		return productDao.getProductDetail(productId);
	}
	
	public void updateProduct(int productId) {
		productDao.updateProduct(productId);
	};
	public void deleteProduct(int productId) {
		productDao.deleteProduct(productId);
	};
	
	@Autowired
	@Qualifier("jpaAuctionDao")
	private AuctionDao auctionDao;
	
	public void insertAuctionProduct(Auction auction) {
		auctionDao.insertAuction(auction);
	};
	public Auction getAuctionDetail(String productId) {
		return auctionDao.getAuctionDetail(productId);
	};

	@Autowired
	private BookmarkDao bookmarkDao;
	
	public void createMark(Bookmark bookmark) {
		bookmarkDao.createMark(bookmark);
	}
	public void deleteMark(String userId, int productId) {
		bookmarkDao.deleteMark(userId, productId);
	}
	
	@Autowired
	private GroupBuyDao groupBuyDao;
	
	public int getPeopleNum(int productId) {
		return groupBuyDao.getPeopleNum(productId);
	}

	public GroupBuy getGroupBuyDetail(int productId) {
		return groupBuyDao.getGroupBuyDetail(productId);
	}
	
	@Autowired
	private MeetDao meetDao;

	public int countPeopleNum(int productId) {
		return meetDao.countPeopleNum(productId);
	}

	public Meet getMeetDetail(int meetId) {
		return meetDao.getMeetDetail(meetId);
	}

	public List<Account> getMeetList(int productId) {
		return meetDao.getMeetList(productId);
	}
	

	
//	@Autowired
//	private ReviewDao reviewDao;
	
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
}
