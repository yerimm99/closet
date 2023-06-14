package com.ssp.closet.dto;

import java.io.Serializable;
import java.util.Date;
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
    private int startPrice;
    @Column(name = "USED")
    private int used;
	
    @OneToMany(mappedBy = "auction")
    private List<Bid> bids;


    public Auction() {}

	public void initAuction(Account account) {
		super.setType(1);
		super.setStatus(1);
		super.setRegisterDate(new Date());
		super.setAccount(account);
	  }
	
	
//	public boolean deadline() {
//		Calendar getToday = Calendar.getInstance();
//		getToday.setTime(new Date()); //금일 날짜
//		
//		Calendar cmpDate = Calendar.getInstance();
//		cmpDate.setTime(signDate); //특정 일자
//		
//		long diffSec = (getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000;
//		long diffDays = diffSec / (24*60*60); //일자수 차이
//		
//		System.out.println(diffDays + "일 차이");
//		
//		if (diffDays >= auctionPeriod) {
//			System.out.println("기한만료");
//			return true;
//		}
//		else {
//			System.out.println("기한남음");
//			return false;
//		}
//	}

}
