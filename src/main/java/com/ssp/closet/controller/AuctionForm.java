package com.ssp.closet.controller;

import java.io.Serializable;

import com.ssp.closet.dto.Auction;

@SuppressWarnings("serial")
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
