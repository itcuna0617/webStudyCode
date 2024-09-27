package com.greedy.section03.map.run;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Application2 {
	public static void main(String[] args) {

		/* Properties */
		/*
		 * 설정 파일의 값을 읽어서 어플리케이션에 적용할 때 주로 사용
		 * hashMap처럼 key와 value를 저장하는데 key와 value 모두
		 * String으로만 되어 있어야 한다.
		 * 
		 * 입출력을 통한 설정 파일 생성 가능(메소드 제공)
		 */
		Properties prop = new Properties();
		
		/* put이 아닌 setProperty(key, value)를 활용한다. */
		prop.setProperty("driver", "oracle.jdbc.driver.OracleDriver");
		prop.setProperty("url", "jdbc:oracle:thin:@127.0.0.1:1521:xe");
		prop.setProperty("user", "student");
		prop.setProperty("password", "pass01");
		
		try {
//			prop.store(new FileOutputStream("driver.dat"), "jdbc driver");
//			prop.store(new FileOutputStream("driver.txt"), "jdbc driver");
			prop.storeToXML(new FileOutputStream("driver.xml"), "jdbc driver");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println("파일에 저장 전 Properties: " + prop);
//		System.out.println("driver: " + prop.getProperty("driver"));
//		System.out.println("url: " + prop.getProperty("url"));
//		System.out.println("user: " + prop.getProperty("user"));
//		System.out.println("password: " + prop.getProperty("password"));
		
		/* 파일로부터 읽어와서 새로운 Properties 객체에 기록 */
		Properties prop2 = new Properties();
		
		try {
//			prop2.load(new FileInputStream("driver.dat"));
//			prop2.load(new FileInputStream("driver.txt"));
			prop2.loadFromXML(new FileInputStream("driver.xml"));
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
		System.out.println("저장된 파일로부터 읽어온 Properties: " + prop2);
		System.out.println("driver: " + prop2.getProperty("driver"));
		System.out.println("url: " + prop2.getProperty("url"));
		System.out.println("user: " + prop2.getProperty("user"));
		System.out.println("password: " + prop2.getProperty("password"));
	}
}




