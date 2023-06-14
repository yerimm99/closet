package com.ssp.closet.controller.auction;

import java.io.Serializable;

import com.ssp.closet.dto.Bid;

@SuppressWarnings("serial")
public class BidForm implements Serializable{
	private Bid bid;

	private boolean newBid;
	
	public BidForm(Bid bid) {
		this.bid = bid;
		this.newBid = false;
	}
	
	public BidForm() {
		this.bid = new Bid();
	}

	public Bid getBid() {
		return bid;
	}
	public boolean isNewBid() {
		return newBid;
	}
}
