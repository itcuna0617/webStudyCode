package com.greedy.level03.hard;

import com.greedy.level02.normal.PrintInput;

public class RandomMaker {

	/*
	 * "앞면" 또는 "뒷면"이라는 문자열 중에 하나를 랜덤하게 반환하는 메소드를 작성하시오.
	 * 
	 * 삼항연산자를 사용해서 풀기
	 */
	public static String tossCoin() {
		
//		int random = (int) (Math.random() * 2);
		int random = PrintInput.makeRandom(0, 1);
		
		return random == 0? "앞면" : "뒷면";
	}
	
	/*
	 * "가위", "바위", "보"이라는 문자열 중에 하나를 랜덤하게 반환하는 메소드를 작성하시오.
	 * 
	 * 삼항연산자를 사용해서 풀기
	 */
	public static String rockPaperScissors() {
		
//		int random = (int) (Math.random() * 3);
		int random = PrintInput.makeRandom(0, 2);
		
		return random == 0? "가위": random == 1? "바위" : "보";
	}

	/*
	 * 매개변수로 넘어온 숫자 갯수만큼의 임의의 대문자 영어 알파벳들을
	 * String으로 반환하는 메소드
	 * 
	 * ex)
	 * "XAHEIJGHBI"와 같은 문자열이 반환 됨 
	 */
	public static String randomUpperAlphabet(int length) {
		
		String randomAlpha = "";
		for(int i = 0; i < length; i++) {
//			randomAlpha += (char) ((int) (Math.random() * 26 + 'A'));
			
			randomAlpha += (char) PrintInput.makeRandom('A', 'Z');
		}
		
		return randomAlpha;
	}
	
	/*
	 * 위의 randomLowerAlphabet으로 발생하는 대문자 영단어를
	 * 소문자로 바꿔서 String으로 반환하는 메소드
	 * 
	 * ex)
	 * "xaheijghbi"와 같은 문자열이 반환 됨 
	 */
	public static String toLowerAlphabet(int length) {
//		int amount = 0;
//		
//		amount = 'z' - 'a' + 1;
//		
//		String randomAlpha = "";
//		for(int i = 0; i < length; i++) {
//			
//			randomAlpha += (char) ((int)(Math.random() * amount) + 'a');
//		}
//			
//		return randomAlpha;
		
		String randomAlpha = randomUpperAlphabet(length);
		String resultAlpha = "";
		
		for(int i = 0; i < length; i++) {
			resultAlpha += (char)(randomAlpha.charAt(i) + 32);
		}
		
		return resultAlpha;
	}
	
	
	
}
