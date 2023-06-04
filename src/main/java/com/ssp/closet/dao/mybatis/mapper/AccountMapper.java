package com.ssp.closet.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssp.closet.dto.Account;

@Mapper
public interface AccountMapper {

	Account getAccountByUsername(String userId);

	@Select("SELECT USERID, PASSWORD, EMAIL, NAME, ADDRESS, PHONE, RATING"
			+ " FROM ACCOUNT WHERE ACCOUNT.USERID = #{userId}"
			+ " AND PASSWORD = #{password}")
	Account getAccountByUsernameAndPassword(String userId, String password);
	
	@Insert("INSERT INTO ACCOUNT (USERID, PASSWORD, EMAIL, NAME, ADDRESS, PHONE, RATING)"
			+ " VALUES ({userid}, #{password}, #{email}, #{name}, #{address}, #{phone}, #{rating}")
	int  insertAccount(Account account);
	
	@Insert("UPDATE ACCOUNT SET USERID = #{userId}, PASSWORD = #{password}, "
			+ "EMAIL = #{email}, NAME = #{name}, ADDRESS = #{address}, "
			+ "PHONE = #{phone}, RATING = #{rating}")
	int  updateAccount(Account account);

	@Delete("DELETE FROM ACCOUNT WHERE USERID = #{userId}")
	int  removeAccount(String userId);

	@Select("SELECT USERID FROM ACCOUNT WHERE ACCOUNT.USERID = #{userId}")
	int  exisingUser(String userId);	
	
//	@Update("UPDATE ACCOUNT set MILEAGE = MILEAGE - #{mileage} WHERE USERID = #{userId}")
//	int  useMileage(int mileage, String userId);
//	
//	@Update("UPDATE ACCOUNT set MILEAGE = MILEAGE + #{mileage} WHERE USERID = #{userId}")
//	int  getMileage(int mileage, String userId);

}