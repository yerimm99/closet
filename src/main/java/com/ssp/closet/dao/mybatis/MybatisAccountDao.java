package com.ssp.closet.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssp.closet.dao.AccountDao;
import com.ssp.closet.dao.mybatis.mapper.AccountMapper;
import com.ssp.closet.dto.Account;

@Repository
public class MybatisAccountDao implements AccountDao {

	@Autowired
	private AccountMapper accountMapper;
	 
	@Override
	public Account getAccount(String userId) throws DataAccessException {
		return accountMapper.getAccountByUsername(userId);
	}
	
	@Override
	public Account getAccount(String userId, String password) 
	throws DataAccessException {
		return accountMapper.getAccountByUsernameAndPassword(userId, password);
	}
	
	@Override
	public int insertAccount(Account account) throws DataAccessException {
		return accountMapper.insertAccount(account);
	}
	
	@Override
	public int updateAccount(Account account) throws DataAccessException {
		return accountMapper.updateAccount(account);
	}	
	
	@Override
	public int removeAccount(String userId) throws DataAccessException {
		return accountMapper.removeAccount(userId);
	}

	@Override
	public int existingUser(String userId) {
		return accountMapper.exisingUser(userId);
	}	
}
