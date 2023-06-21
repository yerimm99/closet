package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Groupbuy;

public interface GroupbuyRepository extends JpaRepository<Groupbuy, Integer>{
	List<Groupbuy> findByNameIgnoreCaseContainingOrderByStatusDescRegisterDateDesc(String keywords);

	List<Groupbuy> findByCategoryIdOrderByStatusDescRegisterDateDesc(String categoryId);
	void deleteByProductId(int productId);
	List<Groupbuy> findByAccountOrderByStatusDescRegisterDateDesc(Account account);
	Groupbuy findByProductId(Integer productId);
	List<Groupbuy> findAllByOrderByStatusDescRegisterDateDesc();
	@Query("SELECT g FROM Groupbuy g WHERE rownum <= 4 Order by g.registerDate desc")
	List<Groupbuy> findTop4OrderByRegisterDate();
}
