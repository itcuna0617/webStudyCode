package com.ohgiraffers.bootrestapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/* 엔티티 및 레포지토리 인터페이스를 인식 시켜주기 위해 반드시 필요한 설정!(미리 만들어 두자) */
@Configuration
@EntityScan(basePackages = {"com.ohgiraffers.bootrestapi"})			 // 인지 할 엔티티 범위
@EnableJpaRepositories(basePackages = "com.ohgiraffers.bootrestapi") // 인지 할 레포지토리 범위
public class JpaConfiguration {

}
