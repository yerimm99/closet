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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NAME_REQUIRED", "상품명을 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "SIZE_REQUIRED", "사이즈를 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "COLOR_REQUIRED", "색상을 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startPrice", "START_PRICE_REQUIRED", "경매 시작가를 입력해주세요.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "END_DATE_REQUIRED", "종료일을 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryId", "CATEGORY_REQUIRED", "카테고리를 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "DESCRIPTION_REQUIRED", "상품 설명을 입력해주세요.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "picture1", "PICTURE_REQUIRED", "사진을 첨부해주세요.");
		errors.setNestedPath("");
	}

}
