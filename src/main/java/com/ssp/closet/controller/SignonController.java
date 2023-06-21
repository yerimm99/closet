package com.ssp.closet.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.ssp.closet.dto.Account;
//import com.ssp.closet.dto.Product;
import com.ssp.closet.service.ClosetFacade;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;

@Controller
@SessionAttributes("userSession")
public class SignonController { 

	private ClosetFacade closetStore;
	@Autowired
	public void setClosetStore(ClosetFacade closetStore) {
		this.closetStore = closetStore;
	}
	
	@Value("account/SignonForm")
	private String formViewName;
	
//	@RequestMapping("/account/SignonForm.do")
//	public String showForm() {
//		return formViewName;
//	}

	@RequestMapping("/account/signon.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			@RequestParam(value="forwardAction", required=false) String forwardAction,
			Model model) throws Exception {
		Account account = closetStore.getAccount(userId, password);
		if (account == null) {
			return new ModelAndView("account/SignonForm", "message", 
					"잘못된 아이디나 패스워드입니다.");
		}

		else {
			UserSession userSession = new UserSession(account);
			
			model.addAttribute("userSession", userSession);
			if (forwardAction != null) 
				return new ModelAndView("redirect:" + forwardAction);
			else 
				return new ModelAndView("index");
		}
	}
}
