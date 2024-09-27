package com.greedy.section03.setterInjection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.greedy.section03.setterInjection.RandomGenerator;
import com.greedy.section03.setterInjection.RandomNumberGenerator;

@Configuration
@ComponentScan("com.greedy.section03.setterInjection")
public class ContextConfiguration {
	@Bean
	public RandomGenerator randomGenerator() throws Exception {
		return new RandomNumberGenerator(1, 10);
	}
}
