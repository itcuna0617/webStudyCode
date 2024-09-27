package com.greedy.section02.preparedStatement;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* 쿼리만 따로 저장할 외부 리소스(xml파일)를 만들기 위한 예제 */
public class Test {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		prop.setProperty("keyString", "valueString");
		System.out.println(prop);
		
		try {
			prop.storeToXML(
					new FileOutputStream("src/com/greedy/section02/preparedStatement/employee-query.xml"), "title");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
