package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Bid implements Serializable{
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bidId;
	private int productId;
	private String userId;
	private int bidPrice;
	private int bidResult;
	@Temporal(TemporalType.TIMESTAMP)
	private Date signDate;

	
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
	
	public void initBid(Account account, int pId) {
		userId = account.getUserId();
		productId = pId;
		bidResult = 0;
		signDate = new Date();
	}
}
