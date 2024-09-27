package com.greedy.section01.advice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.greedy.section01.advice")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ContextConfiguration {

}
