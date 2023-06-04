package com.ssp.closet.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.service.ClosetFacade;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes("userSession")
public class SignonController { 

	private ClosetFacade closet;
	@Autowired
	public void setClosetStore(ClosetFacade closet) {
		this.closet = closet;
	}
	@RequestMapping("/login.do")
	public String handleRequest(
			ModelMap model
			) throws Exception {
		return "/account/login";
	}

	@RequestMapping("/account/login.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			@RequestParam(value="forwardAction", required=false) String forwardAction,
			Model model) throws Exception {
			Account account = closet.getAccount(userId, password);
		
			if (account == null) {
				return new ModelAndView("index");
			}
			else {
				if (forwardAction != null) 
					return new ModelAndView("redirect:" + forwardAction);
				else 
					return new ModelAndView("main/auction");
			}
		
	}
}
