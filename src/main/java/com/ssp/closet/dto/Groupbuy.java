package com.ssp.closet.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.PrimaryKeyJoinColumn;

@SuppressWarnings("serial")
@Entity
@SecondaryTable(name="PRODUCT1", 
pkJoinColumns=@PrimaryKeyJoinColumn(
		name="productId", referencedColumnName="productId"))
public class Groupbuy extends Product implements Serializable {

	/* Private Fields */
	@Id
	private int productId;
	private int price;
	private int peopleNum; // 공동구매가 이뤄지기 위한 최소 인원
	private int[] userId; // 공동구매 참여자의 userId

	/* JavaBeans Properties */
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}
	public int[] getUserId() {
		return userId;
	}
	public void setUserId(int[] userId) {
		this.userId = userId;
	}
}