package com.greedy.section02.constInjection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.greedy.section02.constInjection.RandomGenerator;
import com.greedy.section02.constInjection.RandomNumberGenerator;

@Configuration
@ComponentScan("com.greedy.section02.constInjection")
public class ContextConfiguration {
	@Bean
	public RandomGenerator randomGenerator() throws Exception {
		return new RandomNumberGenerator(1, 10);
	}
}
