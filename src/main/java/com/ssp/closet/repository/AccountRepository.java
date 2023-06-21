package com.ssp.closet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Account;
import com.ssp.closet.dto.Product;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	Optional<Account> findByUserId(Integer userId);

}
