package com.greedy.section02.constInjection;

public class RandomNumberGenerator implements RandomGenerator{

	private int startNum;
	private int endNum;
	
	public RandomNumberGenerator() {
	}
	public RandomNumberGenerator(int startNum, int endNum) throws Exception {
		if(startNum >= endNum) {
			throw new Exception("시작 값이 종료 값보다 크거나 같을 수 없습니다.");
		} else if(startNum <= 0) {
			throw new Exception("양의 정수만 입력해 주세요");
		}
		this.startNum = startNum;
		this.endNum = endNum;
	}

	@Override
	public int getRandomNumber() {
		return (int)(Math.random() * (endNum - startNum + 1)) + 1;
	}

	@Override
	public int getStartNum() {
		return this.startNum;
	}

	@Override
	public int getEndNum() {
		return this.endNum;
	}

}
