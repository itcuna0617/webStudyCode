package com.ohgiraffers.bootrestapi.purchase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohgiraffers.bootrestapi.purchase.entity.OrderAndProduct;

public interface OrderAndProductRepository extends JpaRepository<OrderAndProduct, Integer> {

	List<OrderAndProduct> findByOrderMember(int memberId);
	

}
