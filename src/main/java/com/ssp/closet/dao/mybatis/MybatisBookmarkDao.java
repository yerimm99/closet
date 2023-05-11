package com.ssp.closet.dao.mybatis;

import com.ssp.closet.dao.BookmarkDao;
import com.ssp.closet.dao.mybatis.mapper.BookmarkMapper;
import com.ssp.closet.dto.Bookmark;

public class MybatisBookmarkDao implements BookmarkDao {
	
	protected BookmarkMapper bookmarkMapper;
	
	public void createMark(Bookmark bookmark) {
		bookmarkMapper.createMark(bookmark);
	}
	
	public void deleteMark(String userId, int productId) {
		bookmarkMapper.deleteMark(userId, productId);
	}
}
