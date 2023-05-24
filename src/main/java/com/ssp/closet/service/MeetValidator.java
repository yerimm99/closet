package com.ssp.closet.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ssp.closet.dto.Meet;

@Component
public class MeetValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Meet.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		//검증해야 할 목록 작성
		// 예시 : validateCreditCard((Order) obj, errors);
	}
	
	// 위에서 작성한 메소드 구현 
	// 예시 : validateCreditCard(Order order, Errors errors) {...};
}