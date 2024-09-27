package com.greedy.section02.template;

/* 기존의 import들은 클래스명까지 작성 */
import java.sql.Connection;

/*
 * static 메소드가 같은 클래스에 있으면 클래스명을 앞에 달지 않고 static 메소드를 호출 해도 된다.
 * 다른 클래스에 선언한 static 메소드도 클래스명을 앞에 달지 않고 쓸려면 import static을 해 주면 된다.
 * 
 * import static은 메소드명까지 작성
 */
import static com.greedy.section02.template.JDBCTemplate.getConnection;
import static com.greedy.section02.template.JDBCTemplate.close;


public class Application {

	public static void main(String[] args) {
		
		/* static import 전 */
//		Connection con = JDBCTemplate.getConnection();
		
		/* static import 후 */
		Connection con = getConnection();
		System.out.println("main에서 받은 값: " + con);
		
		/* Connection 인스턴스를 활용한 DB와의 연동(데이터와 관련된 CRUD작업)이 진행되는 공간 */
		
		/* close 메소드를 JDBCTemplate에 빼기 전 */
//		try {
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		/* static import 전 */
//		JDBCTemplate.close(con);
		
		/* static import 후 */
		close(con);
	}

}
