package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "DELIVERY")
public class Delivery implements Serializable {
	
	/* Private Fields */
	@Id
	@SequenceGenerator(name = "ORDERID_SEQ_GENERATOR", sequenceName="ORDERID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ORDERID_SEQ_GENERATOR")
	@Column(name = "ORDERID")
	private int orderId;

	@Column(name = "USERID")
	private String userId; // 구매자

	@Column(name="PRODUCTID")
	private int productId;

	@Temporal(TemporalType.DATE)
	@Column(name = "ORDERDATE")
	private Date orderDate;

	@Column(name = "SHIPADDRESS")
	private String shipAddress;

	@Column(name = "PRICE")
	private int price; // 결제 금액

	@Column(name = "BILLTONAME")
	private String billToName;

	@Column(name = "SHIPTONAME")
	private String shipToName;

	@Column(name = "CREDITCARD")
	private String creditCard;

	@Column(name = "EXPIRYDATE")
	private String expiryDate;

	@Column(name = "CARDTYPE")
	private String cardType;

	@ManyToOne
	@JoinColumn(name = "USERID", referencedColumnName = "USERID", insertable = false, updatable = false)
	private Account account;

	@ManyToOne
	@JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID", insertable = false, updatable = false)
	private Product product;


	/* Public Methods */
	public void initOrder(Account account, Product product) {
		orderDate = new Date();

		shipToName = account.getUsername();
		shipAddress = account.getAddress();

		billToName = account.getUsername();
		productId = product.getProductId();
		price = product.getPrice();
	}

	public void initOrder(Account account, Auction auction) {
		userId = account.getUserId();
		orderDate = new Date();

		shipToName = account.getUsername();
		shipAddress = account.getAddress();

		billToName = account.getUsername();

		productId = auction.getProductId();
		//price = auction.getMaxPrice();
	}

	public void initOrder(Account account, Groupbuy groupBuy) {
		userId = account.getUserId();
		orderDate = new Date();

		shipToName = account.getUsername();
		shipAddress = account.getAddress();

		billToName = account.getUsername();

		productId = groupBuy.getProductId();
		price = groupBuy.getPrice();
	}
}