package com.ssp.closet.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ssp.closet.dto.Groupbuy;

@Component
public class GroupbuyFormValidator implements Validator {
	public boolean supports(Class<?> clazz) {
		return Groupbuy.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		validateGroupbuyForm((Groupbuy) obj, errors);
	}

	public void validateGroupbuyForm(Groupbuy groupbuy, Errors errors) {
		errors.setNestedPath("groupbuy");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NAME_REQUIRED", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "SIZE_REQUIRED", "Size is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "COLOR_REQUIRED", "Color is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "PRICE_REQUIRED", "Price is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "period", "PERIOD_REQUIRED", "Period is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryId", "CATEGORY_REQUIRED", "Category is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "DESCRIPTION_REQUIRED", "Description is required.");
		errors.setNestedPath("");
	}

}
