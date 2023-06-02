package com.ssp.closet.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
@SuppressWarnings("serial")
@Entity
@SecondaryTable(name="Bid", 
pkJoinColumns=@PrimaryKeyJoinColumn(name="productId", referencedColumnName="productId"))
public class Auction extends Product {
	
	@Id
	private int productId;
	@Column(name="userId")
	private String suppId;
	private int startPrice;
	private int maxPrice;
	private int used;
	private String picture1;
	private String picture2;
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="userId")
	private Bid bid;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getSuppId() {
		return suppId;
	}
	public void setUserId(String suppId) {
		this.suppId = suppId;
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
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	public String getPicture2() {
		return picture2;
	}
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
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
