package com.ssp.closet.dto;

public class Bookmark {
	private int productId;
	private String userId;
	
	public int getProductId() {
		return productId;
	}
	
	public Bookmark(String userId, int productId) {
		super();
		this.productId = productId;
		this.userId = userId;
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
