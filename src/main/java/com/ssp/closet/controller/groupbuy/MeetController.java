package com.ssp.closet.controller.groupbuy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

	@RequestMapping("/groupbuy/enjoy.do")
	public String initMeet(HttpServletRequest request,
			@RequestParam("productId") int productId,
			@ModelAttribute("meetForm") MeetForm meetForm,
			ModelMap model) 
					throws ModelAndViewDefiningException {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			Groupbuy groupbuy = closet.getGroupbuyDetail(productId);
			Meet existingMeet = closet.findMeetByUserIdAndProductId(account.getUserId(), productId);
			meetForm.getMeet().initMeet(account, groupbuy);
			if (existingMeet != null) {
				meetForm.setMeet(existingMeet);
				meetForm.setNewMeet(false);
			} else {
				Meet meet = new Meet();
				meet.initMeet(account, groupbuy);
				meetForm.setMeet(meet);
				meetForm.setNewMeet(true);
				closet.createMeet(meet);
				groupbuy.setPeopleSum(groupbuy.getPeopleSum() + 1);
				closet.insertGroupbuy(groupbuy); //변경사항 저장
				
				if(groupbuy.getPeopleSum() == groupbuy.getPeopleNum()) {
					groupbuy.setStatus(0);
					closet.insertGroupbuy(groupbuy); //변경사항 저장

					List<Meet> meets = closet.findByProductId(productId);
					for (Meet m : meets) {
						m.setMeetResult(1); // 값을 1로 변경
						// 변경된 엔티티 저장
						closet.createMeet(m);
					}
				}
			}
		}
		else {
			return "redirect:/account/SignonForm.do";
		}
		PagedListHolder<Groupbuy> productList = new PagedListHolder<Groupbuy>(this.closet.getGroupbuyList());
		productList.setPageSize(4);
		model.put("productList", productList);
		model.put("isNewMeet", meetForm.isNewMeet());
		return "main/groupbuy"; 
	}
	
	 public String addMeet(@RequestParam("userId") String userId, @RequestParam("productId") int productId) {
	        MeetId meetId = new MeetId(userId, productId);
	        Meet meet = new Meet();
	        meet.setId(meetId);
	        meet.setSignDate(new Date());
	        meet.setMeetResult(0);

	        meetRepository.save(meet);

	        return "redirect:/meet/list"; // 적절한 리다이렉트 경로로 수정해주세요.
	    }
}