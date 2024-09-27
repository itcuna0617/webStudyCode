package com.greedy.section02.preparedStatement;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.EmployeeDTO;

/* 사용 할 쿼리를 XML 파일에서 불러온 뒤 사용해 보기 */
public class Application6 {

	public static void main(String[] args) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		EmployeeDTO row = null;
		List<EmployeeDTO> empList = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("조회 할 이름에 포함 될 글자를 입력하시오: ");
		String word = sc.next();
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("src/com/greedy/section02/preparedStatement/employee-query.xml"));
//			System.out.println("entry 태그의 키와 밸류들이 담겼는지 확인: " + prop);
//			System.out.println("key가 selectEmpByWord인 쿼리 꺼내기: " + prop.getProperty("selectEmpByWord"));
			
			String query = prop.getProperty("selectEmpByWord");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, word);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				row = new EmployeeDTO();
			
				row.setEmpId(rset.getString("EMP_ID"));
				row.setEmpName(rset.getString("EMP_NAME"));
				row.setEmpNo(rset.getString("EMP_NO"));
				row.setEmail(rset.getString("EMAIL"));
				row.setPhone(rset.getString("PHONE"));
				row.setDeptCode(rset.getString("DEPT_CODE"));
				row.setJobCode(rset.getString("JOB_CODE"));
				row.setSalLevel(rset.getString("SAL_LEVEL"));
				row.setSalary(rset.getInt("SALARY"));
				row.setBonus(rset.getDouble("BONUS"));
				row.setManagerId(rset.getString("MANAGER_ID"));
				row.setHireDate(rset.getDate("HIRE_DATE"));
				row.setEntDate(rset.getDate("ENT_DATE"));
				row.setEntYn(rset.getString("ENT_YN"));
				
				empList.add(row);
			}
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(con);
		}
		
		for(EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
	}
}





