package com.ssp.closet.repository;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.LikeMark;
import com.ssp.closet.dto.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMarkRepository extends JpaRepository<LikeMark, Long> {
    
	List<LikeMark> findByAccount(Account account);
	void deleteByProductAndAccount(Product product, Account account);
	int getLikeCountByProduct(Product product);
	LikeMark findByProductAndAccount(Product product, Account account);
	
}
