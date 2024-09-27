package com.ohgiraffers.bootrestapi.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohgiraffers.bootrestapi.member.entity.MemberRole;
import com.ohgiraffers.bootrestapi.member.entity.MemberRolePk;

public interface MemberRoleRepository extends JpaRepository<MemberRole, MemberRolePk>{

}
