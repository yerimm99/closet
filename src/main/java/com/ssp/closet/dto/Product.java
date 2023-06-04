package com.ssp.closet.dto;

import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name = "PRODUCT")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn // 하위 테이블의 구분 컬럼 생성(default = DTYPE)
public class Product implements Serializable {

	/* Private Fields */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String categoryId; // 상의? 하의? ....
	private String name;
	private String description; // 상품 설명
	private int type; // 경매? 공동구매?
	private int status; // 판매 상태
	private Date registerDate; // 등록 날짜
	private int period; // 판매 기간
	@Column(name="userId")
	private String suppId; //판매자 또는 등록자의 userId
	private String color;
	private String size;
	private String picture1;
	private String picture2;
	private String picture3;
	private String picture4;
	private int price;

	/* JavaBeans Properties */
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getSuppId() {
		return suppId;
	}
	public void setSuppId(String suppId) {
		this.suppId = suppId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
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
	public String getPicture3() {
		return picture3;
	}
	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}
	public String getPicture4() {
		return picture4;
	}
	public void setPicture4(String picture4) {
		this.picture4 = picture4;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void initGroupBuy(Account account) {
		suppId = account.getUserId();
		name = account.getName();
		registerDate = new Date();
	}
}