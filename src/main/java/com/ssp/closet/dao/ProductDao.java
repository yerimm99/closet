package com.ssp.closet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ssp.closet.dto.Product;

public interface ProductDao {

	//상품 등록
	void insertProduct(Product product) throws DataAccessException;
	
	//상품 수정
	void updateProduct(Product product) throws DataAccessException;

	//상품 삭제
	void deleteProduct(int productId) throws DataAccessException;

	//상품 리스트 조회
	List<Product> getProductList() throws DataAccessException;

	//상품 상세보기
	Product getProductDetail(int productId) throws DataAccessException;
}
