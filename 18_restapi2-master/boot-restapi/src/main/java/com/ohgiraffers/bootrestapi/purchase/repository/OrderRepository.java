package com.ohgiraffers.bootrestapi.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohgiraffers.bootrestapi.purchase.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
