package com.ssp.closet.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name = "AUCTION")
public class Auction extends Product {

	private int productId;
	private int startPrice;
	private int used;
	
//	public int getProductId() {
//		return productId;
//	}
//	public void setProductId(int productId) {
//		this.productId = productId;
//	}
//	public String getBidder() {
//		return bidder;
//	}
//	public void setBidder(String bidder) {
//		this.bidder = bidder;
//	}
	public int getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}
//	public int getMaxPrice() {
//		return maxPrice;
//	}
//	public void setMaxPrice(int maxPrice) {
//		this.maxPrice = maxPrice;
//	}
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}

	public void initAuction(Account account) {
		super.setType(1);
		super.setStatus(1);
		super.setRegisterDate(new Date());
		super.setSuppId(account.getUserId());
		super.setPrice(startPrice);

	  }
	
	
//	public boolean deadline() {
//		Calendar getToday = Calendar.getInstance();
//		getToday.setTime(new Date()); //금일 날짜
//		
//		Calendar cmpDate = Calendar.getInstance();
//		cmpDate.setTime(signDate); //특정 일자
//		
//		long diffSec = (getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000;
//		long diffDays = diffSec / (24*60*60); //일자수 차이
//		
//		System.out.println(diffDays + "일 차이");
//		
//		if (diffDays >= auctionPeriod) {
//			System.out.println("기한만료");
//			return true;
//		}
//		else {
//			System.out.println("기한남음");
//			return false;
//		}
//	}

}
