package com.ssp.closet.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.ssp.closet.dto.Auction;

@Component
public class AuctionFormValidator implements Validator{
	
	public boolean supports(Class<?> clazz) {
		return Auction.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object obj, Errors errors) {
	
	}

}
