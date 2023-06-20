package com.ssp.closet.controller.auction;

import java.io.Serializable;
import java.util.Date;

import com.ssp.closet.dto.Auction;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class AuctionForm implements Serializable {

	private Auction auction;
	private boolean newAuction;
	
	public AuctionForm(Auction auction) {
		this.auction = auction;
		this.newAuction = false;
	}
	
	public AuctionForm() {
		this.auction = new Auction();
	}

	public Auction getAuction() {
		return auction;
	}
	public boolean isNewAuction() {
		return newAuction;
	}

}
