package com.greedy.section01.advice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.greedy.section01.advice.config.ContextConfiguration;

public class Application {

	public static void main(String[] args) throws Exception {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(ContextConfiguration.class);
		
//		String[] beanNames = context.getBeanDefinitionNames();
//		for(String beanName : beanNames) {
//			System.out.println(beanName);
//		}
		
		/* NormalStudent의 경우 */
		Student normalStudent = context.getBean("normalStudent", Student.class); 
		
		System.out.println("=========== NormalStudent ===========");
		AchievementResult normalResult = normalStudent.study(new Passion(5));
		
		System.out.println("NormalStudent Result: " + normalResult);
		
		/* GreedyStudent의 경우 */
		Student greedyStudent = context.getBean("greedyStudent", Student.class); 
		
		System.out.println("=========== GreedyStudent ===========");
		AchievementResult greedyResult = greedyStudent.study(new Passion(10));
		
		System.out.println("GreedyStudent Result: " + greedyResult);
	}

}
