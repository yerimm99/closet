package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BidId.class)
@Table(name = "BID")
public class Bid {
	@Id
	@Column(name = "USERID")
	private String userId;

	@Id
	@Column(name = "PRODUCTID")
	private int productId;
	
	@Column(name = "BIDPRICE")
	private Integer bidPrice;
	@Column(name = "BIDRESULT")
	private int bidResult;
	@Column(name = "SIGNDATE")
	@Temporal(TemporalType.DATE)
	private Date signDate;

	@ManyToOne
    @JoinColumn(name = "USERID", referencedColumnName = "USERID", insertable = false, updatable = false)
    private Account bidder;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCTID", referencedColumnName = "", insertable = false, updatable = false)
    private Auction auction;

	public void initBid(Account account, Auction ac) {
		userId = account.getUserId();
		productId = ac.getProductId();
		setBidder(account);
		setAuction(ac);
		bidResult = 0;
		signDate = new Date();
		
	}
}
