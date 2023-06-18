package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Groupbuy;

public interface GroupbuyRepository extends JpaRepository<Groupbuy, Integer>{
	List<Groupbuy> findByCategoryId(String categoryId);
	void deleteByProductId(int productId);
	List<Groupbuy> findByAccount(Account account);
	Groupbuy findByProductId(Integer productId);
}
