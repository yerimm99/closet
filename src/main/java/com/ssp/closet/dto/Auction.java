package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.List;

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
	private Integer startPrice; //경매 시작가
	@Column(name = "USED")
	private int used; //중고,새 상품 구분
	@Column(name = "WINNER")
	private String winner; //낙찰자
	
	
	@OneToMany(mappedBy = "auction")
	private List<Bid> bids;

	public Auction() {}

	public void initAuction(Account account) {
		super.setDTYPE("Auction");
		super.setStatus(1);
		super.setAccount(account);
	}
}
