package com.ohgiraffers.bootrestapi.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import com.ohgiraffers.bootrestapi.review.entity.ReviewAndMember;

public interface ReviewAndMemberRepository extends JpaRepository<ReviewAndMember, Integer> {

	Page<ReviewAndMember> findByProductCode(int searchValue, Pageable paging);

}
