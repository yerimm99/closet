package com.ssp.closet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.dto.Account;
import com.ssp.closet.service.ClosetFacade;

@Controller
public class ViewMypageController {
		private ClosetFacade closet;
		
		@Autowired
		public void setCloset(ClosetFacade closet) {
			this.closet = closet;
		}
		
		@RequestMapping("/closet/mypage.do")
		public String handleRequest(HttpServletRequest request,
				ModelMap model
				) throws Exception {
			
			UserSession userSession = 
					(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
			if (userSession != null) {
				Account account = closet.getAccount(userSession.getAccount().getUserId());
				model.put("account", account);
				return "/main/myPage";
			} else {
				return "redirect:/account/SignonForm.do";
			}
		}
}
