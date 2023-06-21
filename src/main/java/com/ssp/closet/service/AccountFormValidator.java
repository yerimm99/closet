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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.username", "USER_NAME_REQUIRED", "사용자명를 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.email", "EMAIL_REQUIRED", "이메일을 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.phone", "PHONE_REQUIRED", "전화번호를 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.address", "ADDRESS_REQUIRED", "주소를 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.password", "PASSWORD_REQUIRED", "비밀번호를 입력해주세요.");

		if (accountForm.isNewAccount()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.userId", "USER_ID_REQUIRED", "사용자 아이디를 입력해주세요.");
			if (account.getPassword() == null || account.getPassword().length() < 1 || !account.getPassword().equals(accountForm.getRepeatedPassword())) {
				errors.reject("USER_MISMATCH","사용자가 일치하지 않습니다. 다시 입력해주세요.");
			}
		}
		else if (account.getPassword() != null && account.getPassword().length() > 0) {
			if (!account.getPassword().equals(accountForm.getRepeatedPassword())) {
				System.out.println("패스워드 문제");
				errors.reject("PASSWORD_MISMATCH", "비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			}
		}
	}
}