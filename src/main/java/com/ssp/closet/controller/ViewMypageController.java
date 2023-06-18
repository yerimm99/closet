package com.ssp.closet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		public ModelAndView handleRequest(HttpServletRequest request) throws Exception {
			UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/main/myPage");
			if(userSession != null) {
				mav.addObject("account", account);
			}
			return mav;
		}

}
