package com.ssp.closet.repository;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.LikeMark;
import com.ssp.closet.dto.Product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMarkRepository extends JpaRepository<LikeMark, Long> {
    
	List<LikeMark> findByAccount(Account account);
	void deleteByProductAndAccount(Product product, Account account);
	@Query("SELECT SUM(l.mark) FROM LikeMark l WHERE l.product.productId = :productId")
	Integer getMarkSumByProduct(@Param("productId") int productId);
	LikeMark findByProductAndAccount(Product product, Account account);
	
}
