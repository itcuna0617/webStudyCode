package com.ohgiraffers.bootrestapi.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohgiraffers.bootrestapi.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	/* count는 반환형이 long이다. */
	long countByProductCode(int productCode);

}
