package com.ssp.closet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingVO {
	private int pageGroupPerPageCnt; //페이지 그룹당 페이지 개수
	private int nowPage; //현재 페이지
	private int nowPageGroup; //현재 페이지 그룹
	private int nowPageGroupStartPage; //현재 페이지 그룹에서 시작 페이지
	private int nowPageGroupEndPage; //현재 페이지 그룹에서 마지막 페이지
	private boolean hasPrevious; //이전 페이지 그룹이 있나?
	private boolean hasNext; //다음 페이지 그룹이 있나?
	private int previousPageGroupOfPage; //이전 페이지 그룹에서 첫번째 페이지
	private int nextPageGroupOfPage; //다음 페이지 그룹에서 마지막 페이지
	
}