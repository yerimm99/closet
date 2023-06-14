package com.ssp.closet.dao;


import java.util.List;
import org.springframework.dao.DataAccessException;
import com.ssp.closet.dto.Account;

public interface AccountDao {
	Account getAccount(String userId) throws DataAccessException;

	Account getAccount(String userId, String password) throws DataAccessException;

//	void insertAccount(Account account) throws DataAccessException;

//	void updateAccount(Account account) throws DataAccessException;
//
//	List<String> getUsernameList() throws DataAccessException;
}
