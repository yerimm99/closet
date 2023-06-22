package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
@Table(name = "AUCTION")
@DiscriminatorValue("Auction")
@Getter
@Setter
public class Auction extends Product implements Serializable {

	@Column(name = "STARTPRICE")
	private Integer startPrice;
	@Column(name = "USED")
	private int used;
	@Column(name = "WINNER")
	private String winner;
	
	
	@OneToMany(mappedBy = "auction")
	private List<Bid> bids;

	public Auction() {}

	public void initAuction(Account account) {
		super.setStatus(1);
		super.setAccount(account);
	}
}
