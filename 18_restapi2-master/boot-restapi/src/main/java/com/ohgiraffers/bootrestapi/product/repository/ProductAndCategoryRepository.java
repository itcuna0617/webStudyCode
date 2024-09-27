package com.ohgiraffers.bootrestapi.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohgiraffers.bootrestapi.product.entity.ProductAndCategory;

public interface ProductAndCategoryRepository extends JpaRepository<ProductAndCategory, Integer>{

}
