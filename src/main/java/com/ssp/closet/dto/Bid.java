package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "BID")
public class Bid {
	
	@EmbeddedId
	private BidId id;

	@Column(name = "BIDPRICE")
	private int bidPrice;
	@Column(name = "BIDRESULT")
	private int bidResult;
	@Column(name = "SIGNDATE")
	@Temporal(TemporalType.DATE)
	private Date signDate;

	@ManyToOne
	@MapsId("userId")
    @JoinColumn(name = "USERID")
    private Account bidder;
	
	@ManyToOne
	@MapsId("productId")
    @JoinColumn(name = "PRODUCTID")
    private Auction auction;
	
	
	public void initBid(Account account, Auction ac) {
		bidder = account;
		auction = ac;
		bidResult = 0;
		signDate = new Date();
	}
}
