package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Groupbuy;

public interface GroupbuyRepository extends JpaRepository<Groupbuy, Integer>{
	List<Groupbuy> findByCategoryId(String categoryId);
}
