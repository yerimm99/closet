package com.ssp.closet.controller.groupbuy;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Meet;
import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes("groupbuy")
public class MeetController {
	
	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setClosetStore(ClosetFacade closet) {
		this.closet = closet;
	}
	
	@RequestMapping("/meet/newMeet.do")
	public String initMeet(HttpServletRequest request,
			@RequestParam("productId") int productId,
			@ModelAttribute("meetForm") MeetForm meetForm) 
			throws ModelAndViewDefiningException {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			Groupbuy groupbuy = closet.getGroupbuyDetail(productId);
			
			Meet existingMeet = closet.getMeet(account.getUserId(), productId);
			if (existingMeet != null) {
				meetForm.setNewMeet(false);
				return "main/groupbuy";
			} else {
				meetForm.setNewMeet(true);
				Meet meet =new Meet();
				meet.initMeet(account, groupbuy);
				closet.createMeet(meet);
				return "main/myPage";
			}
		} else {
			return "account/SignonForm";
		}
	}
}
