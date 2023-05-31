package com.ssp.closet.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssp.closet.dto.Bookmark;

@Mapper
public interface BookmarkMapper {

	void createMark(Bookmark Bookmark);
	
	void deleteMark(String userId, int productId);
}
