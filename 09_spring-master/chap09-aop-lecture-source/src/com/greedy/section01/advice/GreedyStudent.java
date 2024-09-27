package com.greedy.section01.advice;

import org.springframework.stereotype.Component;

@Component
public class GreedyStudent implements Student{

	@Override
	public AchievementResult study(Passion passion) throws Exception{
		
		if(passion.getScore() < 10) {
			throw new Exception("GreedyStudent는 열정이 MAX여야 합니다. "
					+ "열정이 부족하니 다시 열정을 가지고 오세요!");
		}
		
		int understandingScore = 0;
		int satisfactionScore = 0;
		double employeementRate = 0.0;
		
		int passionScore = passion.getScore();
		
		int difficulty = 10;
		
		
		for(int i = 1; i <= 9; i++) {
			if(i == 5) {
				System.out.println("잠시 밥을 먹으며 오전에 수업 들은 내용으로 토론을 합니다."
						+ "밥을 코로 먹는건지 입으로 먹는건지 잘 모르겠습니다.");
				employeementRate += difficulty * passionScore;
			}
			System.out.println(i + "교시 수업을 열심히 듣습니다. 하지만 너무 어렵습니다.");
			System.out.println("이해도와 만족도가 열정에 비례하여 자꾸 떨어집니다.");
			understandingScore -= difficulty * passionScore;
			satisfactionScore -= difficulty * passionScore;
			
			System.out.println("하지만 신기하게도 취업율은 잘 올라가고 있습니다.");
			employeementRate += difficulty * passionScore;
		}
		
		return new AchievementResult(understandingScore, satisfactionScore, employeementRate);
	}

}



