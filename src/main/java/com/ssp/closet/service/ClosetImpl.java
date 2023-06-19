package com.ssp.closet.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssp.closet.dao.AccountDao;
import com.ssp.closet.dao.AuctionDao;
import com.ssp.closet.dao.BookmarkDao;
import com.ssp.closet.dao.GroupbuyDao;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Bid;
import com.ssp.closet.dto.Bookmark;
import com.ssp.closet.dto.Category;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.dto.Delivery;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Review;

import com.ssp.closet.repository.AuctionRepository;
import com.ssp.closet.repository.BidRepository;
import com.ssp.closet.repository.DeliveryRepository;
import com.ssp.closet.repository.GroupbuyRepository;
import com.ssp.closet.repository.MeetRepository;
import com.ssp.closet.repository.ProductRepository;

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
	public Auction getAuction(int productId) {
		return aucRepository.findByProductId(productId); 
	}
	public void updateMaxPrice(int productId) {
		aucRepository.updatePrice(productId, findMaxPrice(productId).getBidPrice());
	}
	
	public Page<Auction> getAuctionByCategoryId(String categoryId, Pageable pageable) {
        return aucRepository.findByCategoryId(categoryId, pageable);
    }
	
	public Page<Auction> findSellAuctionByAccount(Account account, Pageable pageable){
		 return aucRepository.findByAccount(account, pageable);
	}
	
	public Auction findBuyAuctionByProductId(int productId) {
		return aucRepository.findByProductId(productId);
	}
	
	public void deleteAuctionByProductId(int productId) {
		aucRepository.deleteByProductId(productId);
	}
	
	@Transactional
	public Page<Auction> getAuctionList(Pageable pageable) {
		return aucRepository.findAll(pageable);//페이징 객체만들어서 반환
	}
	
	@Autowired
	private BidRepository bidRepository;
	
	public void createBid(Bid bid) {
		bidRepository.save(bid);
		updateMaxPrice(bid.getProductId());
	}

	public void updateBidPrice(int productId, String userId, int newPrice) {
		bidRepository.updatePrice(productId, userId, newPrice);
		updateMaxPrice(productId);
	}

	public boolean isBidPriceExists(int productId, int bidPrice) {
		return bidRepository.existsByProductIdAndBidPrice(productId, bidPrice);
	}
	public void deleteBid(int productId) {
		bidRepository.deleteByProductId(productId);
	}
	public Integer countBidByProductId(int productId) {
		return bidRepository.countByProductId(productId);
	}
	  
//	public void updateSuccessResult(BidId bidId) {
//		bidRepository.updateSuccessResult(bidId);
//	}
//	  
//	public void updateFailResult(BidId bidId) {
//		bidRepository.updateFailResult(bidId);
//	}
	  
	public Bid findMaxPrice(int productId) {
		return bidRepository.findTopByProductIdOrderByBidPriceDesc(productId);
	}	 
	  
//	public List<Bid> getBidResultList(String userId) {
//		return bidRepository.findResultByBidder(userId);
//	}
	
	public List<Bid> getBid(String userId) {
		return bidRepository.findByUserId(userId);
	}

	public Bid getBid(String userId, int productId) {
		return bidRepository.findByUserIdAndProductId(userId, productId);
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
	private GroupbuyRepository groupbuyRepository;
	
	public void insertGroupbuy(Groupbuy groupbuy) {
		groupbuyRepository.save(groupbuy);
	}
	
	public Page<Groupbuy> getGroupbuyByCategoryId(String categoryId, Pageable pageable) {
        return groupbuyRepository.findByCategoryId(categoryId, pageable);
    }
	
	public Groupbuy getGroupbuyDetail(int productId) {
		return groupbuyRepository.getReferenceById(productId); 
	}
	
	public void deleteGroupbuyByProductId(int productId) {
		groupbuyRepository.deleteByProductId(productId);
	}
	
	public Page<Groupbuy> findSellGroupbuyByAccount(Account account, Pageable pageable){
		return groupbuyRepository.findByAccount(account, pageable);
	}
	
	public Groupbuy findBuyGroupbuyByProductId(int productId){
		return groupbuyRepository.findByProductId(productId);
	}
	
	@Transactional
	public Page<Groupbuy> getGroupbuyList(Pageable pageable) {
		return groupbuyRepository.findAll(pageable);//페이징 객체만들어서 반환
	}

	
	@Autowired
	@Qualifier("jpaGroupbuyDao")
	private GroupbuyDao groupbuyDao;
	
	public List<Groupbuy> getGroupbuyList(){
		return groupbuyDao.getGroupbuyList();
	}
	
	
	@Autowired
	private MeetRepository meetRepository;
	
	public void createMeet(Meet meet) {
		meetRepository.save(meet);
	}
	
	public Meet findMeetByUserIdAndProductId(String userId, int productId) {
	    return meetRepository.findByUserIdAndProductId(userId, productId);
	}
	
	public List<Meet> findMeetByProductId(int productId){
		return meetRepository.findByProductId(productId);
	}

	public List<Meet> findMeetByUserId(String userId){
		return meetRepository.findByUserId(userId);
	}
	
	public Integer getMeetCountByProductId(int productId) {
		return meetRepository.getMeetCountByProductId(productId);
	}
	
	public void deleteByUserIdAndProductId(String userId, int productId) {
		meetRepository.deleteByUserIdAndProductId(userId, productId);
	}
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	public void createDelivery(Delivery delivery) {
		deliveryRepository.save(delivery);
	}
	
	
	@Override
	public List<Delivery> getBuyList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Delivery> getSellList(String userId) {
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