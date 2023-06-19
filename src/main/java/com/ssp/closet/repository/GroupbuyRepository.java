package com.ssp.closet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Groupbuy;

public interface GroupbuyRepository extends JpaRepository<Groupbuy, Integer>{
	Page<Groupbuy> findByCategoryId(String categoryId, Pageable pageable);
	void deleteByProductId(int productId);
	Page<Groupbuy> findByAccount(Account account, Pageable pageable);
	Groupbuy findByProductId(Integer productId);
}
