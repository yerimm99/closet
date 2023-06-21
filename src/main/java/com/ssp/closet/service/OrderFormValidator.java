package com.ssp.closet.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ssp.closet.dto.Delivery;

@Component
public class OrderFormValidator implements Validator {
	public boolean supports(Class<?> clazz) {
		return Delivery.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		validateOrderForm((Delivery) obj, errors);
	}


	public void validateOrderForm(Delivery order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billToName", "BILLTONAME_REQUIRED", "BillToName is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipToName", "SHIPTONAME_REQUIRED", "ShipToName is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipAddress", "SHIPADDRESS_REQUIRED", "ShipAddress is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardType", "CARDTYPE_REQUIRED", "CardType is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard", "CREDITCARD_REQUIRED", "CreditCard is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "EXPIRYDATE_REQUIRED", "ExpiryDate is required.");
		
		if (!order.getCreditCard().equals("") && !order.getCreditCard().matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")) {
            errors.rejectValue("creditCard", "INVALID_CREDITCARD", "Invalid CreditCard format.");
        }
		
		errors.setNestedPath("");
	}
}