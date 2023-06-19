package com.ssp.closet.controller.groupbuy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

	@RequestMapping("/groupbuy/enjoy.do")
	public String initMeet(HttpServletRequest request,
			@RequestParam("productId") int productId,
			ModelMap model) 
					throws ModelAndViewDefiningException {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			Groupbuy groupbuy = closet.getGroupbuyDetail(productId);
			if(groupbuy.getStatus() == 1) {
				Meet existingMeet = closet.findMeetByUserIdAndProductId(account.getUserId(), productId);
				if (existingMeet != null) {
				} else {
					Meet meet = new Meet();
					meet.initMeet(account, groupbuy);
					closet.createMeet(meet);
					groupbuy.setPeopleSum(groupbuy.getPeopleSum() + 1);
					closet.insertGroupbuy(groupbuy); //변경사항 저장

					if(groupbuy.getPeopleSum() == groupbuy.getPeopleNum()) {
						groupbuy.setStatus(0);
						closet.insertGroupbuy(groupbuy); //변경사항 저장

						List<Meet> meets = closet.findMeetByProductId(productId);
						for (Meet m : meets) {
							m.setMeetResult(1); // 값을 1로 변경
							// 변경된 엔티티 저장
							closet.createMeet(m);
						}
					}
				}
			}
		}
		else {
			return "redirect:/account/SignonForm.do";
		}
		return "redirect:/closet/groupbuy.do";
	}

	//url로 확인해봤는데 삭제 작동 잘 됩니다!
	@RequestMapping("/myPage/deleteMeet.do")
	public String removeBid(HttpServletRequest request,
			@RequestParam("productId") int productId
			) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			Groupbuy groupbuy = closet.getGroupbuyDetail(productId);
			closet.deleteByUserIdAndProductId(account.getUserId(), productId);

			groupbuy.setPeopleSum(groupbuy.getPeopleSum() - 1);
			closet.insertGroupbuy(groupbuy); //변경사항 저장
		}
		else {
			return "redirect:/account/SignonForm.do";
		}
		return "redirect:/closet/mypage.do";
	}
}