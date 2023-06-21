package com.ssp.closet.controller.auction;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ssp.closet.dto.Auction;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class AuctionForm implements Serializable {

	private Auction auction;
	private boolean newAuction;
	private String endDate;
	
	public AuctionForm(Auction auction) {
		this.auction = auction;
		this.newAuction = false;
	}
	
	public AuctionForm() {
		this.auction = new Auction();
	}

	public boolean isNewAuction() {
		return newAuction;
	}

}
