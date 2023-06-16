package com.ssp.closet.controller.auction;

import java.io.Serializable;

import com.ssp.closet.dto.Bid;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class BidForm implements Serializable{
	private Bid bid;

	private boolean newBid;
	
	public BidForm(Bid bid) {
		this.bid = bid;
		this.newBid = false;
	}
	
	public BidForm() {
		this.bid = new Bid();
		this.newBid = true;
	}
	
	public boolean isNewBid() {
		return newBid;
	}
}
