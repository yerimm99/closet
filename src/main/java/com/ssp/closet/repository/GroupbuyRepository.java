package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Groupbuy;

public interface GroupbuyRepository extends JpaRepository<Groupbuy, Integer>{
	List<Groupbuy> findByNameIgnoreCaseContainingOrderByStatusDescRegisterDateDesc(String keywords);

	List<Groupbuy> findByCategoryIdOrderByStatusDescRegisterDateDesc(String categoryId);
	void deleteByProductId(int productId);
	List<Groupbuy> findByAccountOrderByStatusDescRegisterDateDesc(Account account);
	Groupbuy findByProductId(Integer productId);
	List<Groupbuy> findAllByOrderByStatusDescRegisterDateDesc();
}
