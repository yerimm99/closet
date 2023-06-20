package com.ssp.closet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/account/registerForm.do")
public class AccountFormController {

    @Value("${form.view.name}") // 수정: properties 파일에 form.view.name 값 설정 필요
    private String formViewName;
    
    @Value("${success.view.name}") // 수정: properties 파일에 success.view.name 값 설정 필요
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
            @ModelAttribute("accountForm") AccountForm accountForm, BindingResult result, Model model,
            SessionStatus sessionStatus) throws Exception {

        System.out.println("command" + accountForm);

        validator.validate(accountForm, result);

        if (result.hasErrors()) {
            return formViewName;
        }

        Account user = accountForm.getAccount();
        closet.insertAccount(user);
        model.addAttribute("userid", user);

        return successViewName;
    }
}
