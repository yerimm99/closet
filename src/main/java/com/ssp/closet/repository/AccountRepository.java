package com.ssp.closet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

	//Optional<Account> findByUserId(String userId);
	

}
