package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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

	@MapsId("userId")
	@ManyToOne
    @JoinColumn(name = "USERID")
    private Account bidder;
	
	@MapsId("productId")
	@ManyToOne
	@JoinColumn(name = "PRODUCTID", referencedColumnName = "productId")
    private Auction auction;
	
	public String getBidderUserId() {
        return bidder.getUserId();
    }
	
	public void initBid(Account account, Auction ac) {
		setBidder(account);
		setAuction(ac);
		id = new BidId(account.getUserId(), ac.getProductId());
		bidResult = 0;
		signDate = new Date();
		
	}
}
