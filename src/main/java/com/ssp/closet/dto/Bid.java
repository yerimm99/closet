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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "BID")
public class Bid implements Serializable{
	
	@Id
	@Column(name = "BIDID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bidId;
	@Column(name = "PRODUCTID")
	private int productId;
	@Column(name = "USERID")
	private String userId; //입찰자 얘를 bidId로 써도 될듯 
	@Column(name = "BIDPRICE")
	private int bidPrice;
	@Column(name = "BIDRESULT")
	private int bidResult;
	@Column(name = "SIGNDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date signDate;

	@ManyToOne
    @JoinColumn(name = "USERID", insertable = false, updatable = false)
    private Account bidder;
	
	@ManyToOne
    @JoinColumn(name = "PRODUCTID", insertable = false, updatable = false)
    private Auction auction;
	
	
	public void initBid(Account account, Auction ac) {
		bidder = account;
		auction = ac;
		bidResult = 0;
		signDate = new Date();
	}
}
