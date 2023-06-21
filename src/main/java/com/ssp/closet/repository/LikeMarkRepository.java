package com.ssp.closet.repository;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.LikeMark;
import com.ssp.closet.dto.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMarkRepository extends JpaRepository<LikeMark, Long> {

	LikeMark findByProductAndAccount(Product product, Account user);
    // 추가적인 커스텀 메소드가 필요한 경우 여기에 선언할 수 있습니다.
}