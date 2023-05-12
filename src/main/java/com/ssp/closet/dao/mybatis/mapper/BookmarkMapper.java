package com.ssp.closet.dao.mybatis.mapper;

import com.ssp.closet.dto.Bookmark;

public interface BookmarkMapper {

	void createMark(Bookmark Bookmark);
	
	void deleteMark(String userId, int productId);
}
