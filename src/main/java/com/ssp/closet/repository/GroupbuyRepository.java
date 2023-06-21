package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Auction;
import com.ssp.closet.dto.Groupbuy;
import com.ssp.closet.dto.Product;

public interface GroupbuyRepository extends JpaRepository<Groupbuy, Integer>{

	
	List<Product> findTop20OrderByLike = null;
	List<Groupbuy> findByCategoryId(String categoryId);
	List<Groupbuy> findByNameIgnoreCaseContaining(String keywords);

	List<Groupbuy> findByCategoryIdOrderByRegisterDateDesc(String categoryId);
	void deleteByProductId(int productId);
	List<Groupbuy> findByAccountOrderByRegisterDateDesc(Account account);
	Groupbuy findByProductId(Integer productId);
	//List<Groupbuy> findTop10ByOrderByLikeMark();
	List<Groupbuy> findAllByOrderByRegisterDateDesc();
	//List<Groupbuy> findTop10ByOrderByMark();
	List<Groupbuy> findTop10ByOrderByMark();
}
