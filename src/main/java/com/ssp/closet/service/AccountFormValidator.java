package com.ssp.closet.service;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.ssp.closet.controller.AccountForm;
import com.ssp.closet.dto.Account;

@Component
public class AccountFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		AccountForm accountForm = (AccountForm)obj; 
		Account account = accountForm.getAccount();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.name", "USER_NAME_REQUIRED", "User Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.email", "EMAIL_REQUIRED", "Email address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.phone", "PHONE_REQUIRED", "Phone number is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.address", "ADDRESS_REQUIRED", "Address (1) is required.");

		if (accountForm.isNewAccount()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.username", "USER_ID_REQUIRED", "User ID is required.");
			if (account.getPassword() == null || account.getPassword().length() < 1 ||
					!account.getPassword().equals(accountForm.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH",
					 "Passwords did not match or were not provided. Matching passwords are required.");
			}
		}
		else if (account.getPassword() != null && account.getPassword().length() > 0) {
			if (!account.getPassword().equals(accountForm.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH", "Passwords did not match. Matching passwords are required.");
			}
		}
	}
}