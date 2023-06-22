package com.ssp.closet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.AccountForm;
import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.service.AccountFormValidator;
import com.ssp.closet.service.ClosetFacade;

@Controller
@RequestMapping({"/account/registerForm.do","/account/updateForm.do"})
public class AccountFormController {

    @Value("account/registerForm") // 수정: properties 파일에 form.view.name 값 설정 필요
    private String formViewName;
    
    @Value("index") // 수정: properties 파일에 success.view.name 값 설정 필요
    private String successViewName;

    private final ClosetFacade closet;
    private final AccountFormValidator validator;

    @Autowired
    public AccountFormController(ClosetFacade closet, AccountFormValidator validator) {
        this.closet = closet;
        this.validator = validator;
    }

    @ModelAttribute("accountForm")
    public AccountForm formBackingObject(HttpServletRequest request) throws Exception {
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        if (userSession != null) {    // edit an existing account
            return new AccountForm(closet.getAccount(userSession.getAccount().getUserId()));
        } else {    // create a new account
            return new AccountForm();
        }
    }

    @GetMapping
    public String showForm() {
        return formViewName;
    }

    @PostMapping
    public String onSubmit(HttpServletRequest request, HttpSession session,
            @ModelAttribute("accountForm") AccountForm accountForm,
            @RequestParam("sample4_postcode") String postCode,
            @RequestParam("address1") String address1,
            @RequestParam("address2") String address2,
            BindingResult result, Model model,
            SessionStatus sessionStatus) throws Exception {

        System.out.println("command" + accountForm); //한번 확인
        
        try {
			if (accountForm.isNewAccount()) {
				String sAddress = postCode + " " + address1 + " " + address2;
		        accountForm.getAccount().setAddress(sAddress);
		        accountForm.getAccount().setRating(0);
		        Account existUser = closet.getAccount(accountForm.getAccount().getUserId());
		        accountForm.isExistAccount(existUser);
		        
		        validator.validate(accountForm, result);
		        if (result.hasErrors()) {
		            return formViewName;
		        }
		        
				closet.createAccount(accountForm.getAccount());
			}
			else {
		        if(postCode.length() > 0) {
		        	String sAddress = postCode + " " + address1 + " " + address2;
			        accountForm.getAccount().setAddress(sAddress);
		        }
		       
		        validator.validate(accountForm, result);
		        if (result.hasErrors()) {
		            return formViewName;
		        }
		        
				closet.createAccount(accountForm.getAccount());
				
				UserSession userSession = 
						(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
				if (userSession != null) {
					Account account = closet.getAccount(userSession.getAccount().getUserId());
					model.addAttribute("account", account);
					return "/main/myPage";
				} else {
					return "redirect:/account/SignonForm.do";
				}
			}
		}catch (DataIntegrityViolationException ex) {
			result.rejectValue("account.userId", "USER_ID_ALREADY_EXISTS",
					"User ID already exists: choose a different ID.");
			return formViewName;
		}

        return successViewName;
    }
}
