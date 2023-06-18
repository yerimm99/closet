package com.ssp.closet.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ssp.closet.dto.Auction;

@Component
public class AuctionFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Auction.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		validateAuctionForm((Auction) obj, errors);
	}

	public void validateAuctionForm(Auction auction, Errors errors) {
		errors.setNestedPath("auction");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NAME_REQUIRED", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "SIZE_REQUIRED", "Size is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "COLOR_REQUIRED", "Color is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startPrice", "START_PRICE_REQUIRED", "StartPrice is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "END_DATE_REQUIRED", "EndDate is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryId", "CATEGORY_REQUIRED", "Category is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "DESCRIPTION_REQUIRED", "Description is required.");
		errors.setNestedPath("");
	}

}
