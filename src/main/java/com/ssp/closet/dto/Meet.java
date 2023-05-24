package com.ssp.closet.dto;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Meet implements Serializable {
	/* Private Fields */
	private int meetId;
	private Date signDate;
	private int meetResult;
	private int productId;
	private String userId;
	
	/* JavaBeans Properties */
	public int getMeetId() {
		return meetId;
	}
	public void setMeetId(int meetId) {
		this.meetId = meetId;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public int getMeetResult() {
		return meetResult;
	}
	public void setMeetResult(int meetResult) {
		this.meetResult = meetResult;
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
}
