package com.ssp.closet.dto;


@SuppressWarnings("serial")
public class Auction extends Product {
	
	private int productId;
	private String userId;
	private int startPrice;
	private int maxPrice;
	private int used;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
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
