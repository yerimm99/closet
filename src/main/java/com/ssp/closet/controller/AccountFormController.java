package com.ssp.closet.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;
import com.ssp.closet.dto.Category;
//import com.ssp.closet.dto.Product;
import com.ssp.closet.service.AccountFormValidator;
import com.ssp.closet.service.ClosetFacade;

@Controller
@RequestMapping({"/account/login.jsp","/account/registerForm.jsp", "/account/updateForm.jsp"})
public class AccountFormController { 

	@Value("EditAccountForm")
	private String formViewName;
	@Value("index")
	private String successViewName;
	private static final String[] LANGUAGES = {"english", "korean"};

	@Autowired
	private ClosetFacade closet;
	public void setCloset(ClosetFacade closet) {
		this.closet = closet;
	}

	@Autowired
	private AccountFormValidator validator;
	public void setValidator(AccountFormValidator validator) {
		this.validator = validator;
	}

	@ModelAttribute("accountForm")
	public AccountForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession != null) {	// edit an existing account
			return new AccountForm(
					closet.getAccount(userSession.getAccount().getUserId()));
		}
		else {	// create a new account
			return new AccountForm();
		}
	}

	@ModelAttribute("languages")
	public String[] getLanguages() {
		return LANGUAGES;
	}

	@ModelAttribute("categories")
	public List<Category> getCategoryList() {
		return closet.getCategoryList();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			HttpServletRequest request, HttpSession session,
			@ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult result) throws Exception {

		if (request.getParameter("account.listOption") == null) {
			accountForm.getAccount().setListOption(false);
		}
		if (request.getParameter("account.bannerOption") == null) {
			accountForm.getAccount().setBannerOption(false);
		}

		validator.validate(accountForm, result);

		if (result.hasErrors()) return formViewName;
		try {
			if (accountForm.isNewAccount()) {
				closet.insertAccount(accountForm.getAccount());
			}
			else {
				closet.updateAccount(accountForm.getAccount());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("account.username", "USER_ID_ALREADY_EXISTS",
					"User ID already exists: choose a different ID.");
			return formViewName; 
		}

		UserSession userSession = new UserSession(
				closet.getAccount(accountForm.getAccount().getUserId()));
		//PagedListHolder<Product> myList = new PagedListHolder<Product>(
				//closet.getProductListByCategory(accountForm.getAccount().getFavouriteCategoryId()));
		//myList.setPageSize(4);
		//userSession.setMyList(myList);
		session.setAttribute("userSession", userSession);
		return successViewName;  
	}
}