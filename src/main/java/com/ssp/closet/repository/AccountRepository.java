package com.ssp.closet.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

	Account findByUserId(String userId);

	Account findByUserIdAndPassword(String userId, String password);
	
	void insertAccount(Account account);

	void updateAccount(Account account);

	//List<String> getUsernameList();

}
