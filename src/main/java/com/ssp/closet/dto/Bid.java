package com.ssp.closet.dto;

import java.util.Date;

public class Bid {
	
	int bidId;
	int productId;
	String userId;
	int bidPrice;
	int bidResult;
	Date signDate;
	
	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
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
	public int getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}
	public int getBidResult() {
		return bidResult;
	}
	public void setBidResult(int bidResult) {
		this.bidResult = bidResult;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	
}
