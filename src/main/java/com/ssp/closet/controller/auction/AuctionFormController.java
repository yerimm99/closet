package com.ssp.closet.controller.auction;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.ssp.closet.controller.UserSession;
import com.ssp.closet.controller.auction.AuctionForm;
import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.service.AuctionFormValidator;
import com.ssp.closet.service.ClosetFacade;

@Controller
@SessionAttributes("auctionForm")
public class AuctionFormController {
	
	@Autowired
	private ClosetFacade closet;
	
	@Autowired
	public void setClosetStore(ClosetFacade closet) {
		this.closet = closet;
	}
	@Autowired
	private AuctionFormValidator validator;
	public void setValidator(AuctionFormValidator validator) {
		this.validator = validator;
	}
	
	@ModelAttribute("auctionForm")
	public AuctionForm createAuctionForm() {
		return new AuctionForm();
	}
	
	
	@ModelAttribute("categories")
	public List<String> referenceData2() {
		ArrayList<String> categories = new ArrayList<String>();
		categories.add("신발");
		categories.add("아우터");
		categories.add("상의");
		categories.add("하의");
		categories.add("가방");
		categories.add("지갑");
		categories.add("시계");
		categories.add("패션잡화");
		return categories;			
	}
	
	
	@RequestMapping("/auction/newAuction.do")
	public String initNewAuction(HttpServletRequest request,
			@ModelAttribute("auctionForm") AuctionForm auctionForm
			) throws Exception {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			auctionForm.getAuction().initAuction(account);
			auctionForm.setNewAuction(true);
			return "auction/registerForm";
		} else {
			return "redirect:/account/SignonForm.do";
		}
	}
	
	@RequestMapping("/auction/update.do")
	public String editAuction(HttpServletRequest request,
			@RequestParam("productId") int productId,
			@ModelAttribute("auctionForm") AuctionForm auctionForm
			) throws ModelAndViewDefiningException {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Account account = closet.getAccount(userSession.getAccount().getUserId());
			Auction existingAuction = closet.getAuction(productId);
			auctionForm.getAuction().initAuction(account);
			if (existingAuction != null) {
				auctionForm.setAuction(existingAuction);
				auctionForm.setNewAuction(false);
			}
		}
		return "auction/registerForm";
	}
	
	
	@RequestMapping("/auction/confirmAuction.do")
	protected ModelAndView confirmAuction( //auction 등록 확인 
			@ModelAttribute("auctionForm") AuctionForm auctionForm, 
			@RequestParam("files") List<MultipartFile> files,
			SessionStatus status, BindingResult result) {

		validator.validateAuctionForm(auctionForm.getAuction(), result);
		ModelAndView mav1 = new ModelAndView("auction/registerForm");
		if (result.hasErrors()) return mav1;
		//이미지가 저장되는 경로
		String absolutePath = System.getProperty("user.dir")+
				"/src/main/webapp/upload/";
		File nfile = new File(absolutePath);
		
		if(!nfile.exists()) {
			boolean makeDirStatus = nfile.mkdirs();
			if(!makeDirStatus) System.out.println("디렉토리 생성실패");
		}
		List<String> picturePaths = new ArrayList<>(files.size()); // 파일 경로 리스트 초기화
		List<String> filee = new ArrayList<>();

		// 최소 2개 이상의 이미지를 업로드했는지 확인
	    if (files.size() < 2) {
	        ModelAndView mav = new ModelAndView("auction/registerForm");
	        return mav;
	    }
	    
	    // 업로드된 파일 처리
	    for (MultipartFile file : files) {
	        if (!file.isEmpty()) {
	            try {
	                // 파일 저장
	                String fileName = file.getOriginalFilename();
	                String filePath = absolutePath + fileName;
	                file.transferTo(new File(filePath));

	                // 파일 경로 저장
	                picturePaths.add(filePath);
	                filee.add(fileName);

	            } catch (IOException e) {
	                // 파일 저장 중 오류 발생
	                e.printStackTrace();
	                // 오류 처리
	                // ...
	                ModelAndView mav = new ModelAndView("index");
	    	        return mav;
	            }
	        }
	    }
	    
	 // 파일 경로들을 Product 엔티티의 picture1, picture2, picture3, picture4에 할당
	    Auction product = auctionForm.getAuction();
	    
	    if (picturePaths.size() >= 2) {
	    	product.setPicture1(filee.get(0));
	        product.setPicture2(filee.get(1));
	    }
	    product.setPicture3(picturePaths.size() >= 3 ? filee.get(2) : null);
	    product.setPicture4(picturePaths.size() >= 4 ? filee.get(3) : null);
	    
	    closet.insertAuction(product); // 등록 
	    closet.scheduleAuctionEnd(product);

	    ModelAndView mav = new ModelAndView("auction/detail");
	    mav.addObject("product", product);
	    status.setComplete();  // remove session
	    return mav;
	}
	
	@RequestMapping("/auction/delete.do")
	public String removeAuction(HttpServletRequest request,
			@RequestParam("productId") int productId
			) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			closet.deleteAuctionByProductId(productId);
		}
		return "redirect:/myPage/sellAuction.do";
	}
	
	@RequestMapping("/auction/successBySupp.do")
	public String successBySupp(HttpServletRequest request,
			@RequestParam("productId") int productId
			) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		if (userSession != null) {
			Auction auction = closet.getAuction(productId);
			closet.closedAuctionBySupp(auction);
		}
		return "redirect:/myPage/sellAuction.do";
	}
}