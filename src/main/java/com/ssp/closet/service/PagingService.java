package com.ssp.closet.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.PagingVO;

@Service
public class PagingService {
	
	public PagingVO pagingInfoG(Page<Groupbuy> pageList) {
		PagingVO pagingVO = null;
		
		//페이지 그룹당 페이지 수
		int pageGroupPerPageCnt = 5; 
		
		//현재 페이지(jpa는 페이지를 0부터 시작하기 때문에 +1을 해서 보여줌)
		int nowPage = pageList.getNumber()+1;
		
		//현재 페이지 그룹
		int nowPageGroup = 0; 
		if(nowPage%pageGroupPerPageCnt==0) nowPageGroup = nowPage/pageGroupPerPageCnt;
		else nowPageGroup = nowPage/pageGroupPerPageCnt+1;
		
		//현재페이지 그룹에서 시작 페이지
		int nowPageGroupStartPage = (nowPageGroup-1)*pageGroupPerPageCnt+1;
		
		//현재페이지 그룹에서 마지막 페이지
		int nowPageGroupEndPage = nowPageGroup*pageGroupPerPageCnt; 
		if(pageList.getTotalPages()<nowPageGroupEndPage)
			nowPageGroupEndPage = pageList.getTotalPages();
		
		//현재 페이지그룹에서 이전 페이지 그룹 있으면 true
		boolean hasPrevious = true;
		if(nowPageGroupStartPage==1) hasPrevious = false;
		
		//현재 페이지그룹에서 다음 페이지 그룹 있으면 true 
		boolean hasNext = true;
		if(nowPageGroupEndPage==pageList.getTotalPages()) hasNext = false;
		
		//마지막 페이지그룹
		int endPageGroup = 0;
		if(pageList.getTotalPages()%pageGroupPerPageCnt==0) 
			endPageGroup = pageList.getTotalPages()/pageGroupPerPageCnt;
		else endPageGroup = pageList.getTotalPages()/pageGroupPerPageCnt+1;
		
		//이전페이지 그룹의 첫번째 페이지
		int previousPageGroupOfPage = 0;
		if(nowPageGroup!=1) previousPageGroupOfPage = (nowPageGroup-2)*pageGroupPerPageCnt;
		
		//다음페이지 그룹의 첫번째 페이지
		int nextPageGroupOfPage = 1;
		if(nowPageGroup!=endPageGroup) nextPageGroupOfPage = nowPageGroup*pageGroupPerPageCnt;
		
		//페이징 객체 생성
		pagingVO = new PagingVO(
				pageGroupPerPageCnt, nowPage, nowPageGroup, nowPageGroupStartPage, 
				nowPageGroupEndPage, hasPrevious, hasNext,
				previousPageGroupOfPage, nextPageGroupOfPage);
		return pagingVO;
	}
	
	public PagingVO pagingInfoA(Page<Auction> pageList) {
		PagingVO pagingVO = null;
		
		//페이지 그룹당 페이지 수
		int pageGroupPerPageCnt = 5; 
		
		//현재 페이지(jpa는 페이지를 0부터 시작하기 때문에 +1을 해서 보여줌)
		int nowPage = pageList.getNumber()+1;
		
		//현재 페이지 그룹
		int nowPageGroup = 0; 
		if(nowPage%pageGroupPerPageCnt==0) nowPageGroup = nowPage/pageGroupPerPageCnt;
		else nowPageGroup = nowPage/pageGroupPerPageCnt+1;
		
		//현재페이지 그룹에서 시작 페이지
		int nowPageGroupStartPage = (nowPageGroup-1)*pageGroupPerPageCnt+1;
		
		//현재페이지 그룹에서 마지막 페이지
		int nowPageGroupEndPage = nowPageGroup*pageGroupPerPageCnt; 
		if(pageList.getTotalPages()<nowPageGroupEndPage)
			nowPageGroupEndPage = pageList.getTotalPages();
		
		//현재 페이지그룹에서 이전 페이지 그룹 있으면 true
		boolean hasPrevious = true;
		if(nowPageGroupStartPage==1) hasPrevious = false;
		
		//현재 페이지그룹에서 다음 페이지 그룹 있으면 true 
		boolean hasNext = true;
		if(nowPageGroupEndPage==pageList.getTotalPages()) hasNext = false;
		
		//마지막 페이지그룹
		int endPageGroup = 0;
		if(pageList.getTotalPages()%pageGroupPerPageCnt==0) 
			endPageGroup = pageList.getTotalPages()/pageGroupPerPageCnt;
		else endPageGroup = pageList.getTotalPages()/pageGroupPerPageCnt+1;
		
		//이전페이지 그룹의 첫번째 페이지
		int previousPageGroupOfPage = 0;
		if(nowPageGroup!=1) previousPageGroupOfPage = (nowPageGroup-2)*pageGroupPerPageCnt;
		
		//다음페이지 그룹의 첫번째 페이지
		int nextPageGroupOfPage = 1;
		if(nowPageGroup!=endPageGroup) nextPageGroupOfPage = nowPageGroup*pageGroupPerPageCnt;
		
		//페이징 객체 생성
		pagingVO = new PagingVO(
				pageGroupPerPageCnt, nowPage, nowPageGroup, nowPageGroupStartPage, 
				nowPageGroupEndPage, hasPrevious, hasNext,
				previousPageGroupOfPage, nextPageGroupOfPage);
		return pagingVO;
	}
}