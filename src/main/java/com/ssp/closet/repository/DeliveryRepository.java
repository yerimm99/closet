package com.ssp.closet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssp.closet.dto.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer>{

	List<Delivery> findAllByUserId(String userId);
	Delivery findByOrderId(int orderId);
	Delivery findByUserIdAndProductId(String userId, int productId);
}
