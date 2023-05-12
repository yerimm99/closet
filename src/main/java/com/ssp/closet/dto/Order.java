package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("serial")
public class Order implements Serializable {

	/* Private Fields */

	private int orderId;
	private String userId;
	private int productId;
	private String name;
	private Date orderDate;
	private String shipAddress;
	private int price;
	private String billToName;
	private String shipToName;
	private String creditCard;
	private String expiryDate;
	private String cardType;

	/* JavaBeans Properties */


	public int getOrderId() { return orderId; }

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
	public void setOrderId(int orderId) { this.orderId = orderId; }

	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public Date getOrderDate() { return orderDate; }
	public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

	public String getShipAddress() { return shipAddress; }
	public void setShipAddress(String shipAddress) { this.shipAddress = shipAddress; }

	public String getBillToName() { return billToName; }
	public void setBillToName(String billToName) { this.billToName = billToName; }

	public String getShipToName() { return shipToName; }
	public void setShipToName(String shipToName) { this.shipToName = shipToName; }

	public String getCreditCard() { return creditCard; }
	public void setCreditCard(String creditCard) { this.creditCard = creditCard; }

	public String getExpiryDate() { return expiryDate; }
	public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

	public String getCardType() { return cardType; }
	public void setCardType(String cardType) { this.cardType = cardType; }


	/* Public Methods */

	public void initOrder(Account account) {
		name = account.getName();
		orderDate = new Date();

		shipToName = account.getName();
		shipAddress = account.getAddress();

		billToName = account.getName();


		creditCard = "999 9999 9999 9999";
		expiryDate = "12/03";
		cardType = "Visa";
	}
}