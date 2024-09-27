package com.greedy.jsp.member.model.dao;

import static com.greedy.jsp.common.jdbc.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.greedy.jsp.common.config.ConfigLocation;
import com.greedy.jsp.member.model.dto.MemberDTO;

public class MemberDAO {
	
	private final Properties prop;
	
	public MemberDAO() {
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream(ConfigLocation.MAPPER_LOCATION + "member/member-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* MEMBER TABLE 신규 회원 insert용 메소드 */
	public int insertMember(Connection con, MemberDTO requestMember) {
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("insertMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, requestMember.getId());
			pstmt.setString(2, requestMember.getPwd());
			pstmt.setString(3, requestMember.getNickname());
			pstmt.setString(4, requestMember.getPhone());
			pstmt.setString(5, requestMember.getEmail());
			pstmt.setString(6, requestMember.getAddress());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 암호화 처리 된 비밀번호 조회용 메소드(로그인 확인용) */
	public String selectEncryptedPwd(Connection con, MemberDTO requestMember) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String encPwd = null;
		
		String query = prop.getProperty("selectEncryptedPwd");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, requestMember.getId());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				encPwd = rset.getString("MEMBER_PWD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return encPwd;
	}

	public MemberDTO selectLoginMember(Connection con, MemberDTO requestMember) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		MemberDTO loginMember = null;
		
		String query = prop.getProperty("selectLoginMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, requestMember.getId());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginMember = new MemberDTO();
				loginMember.setNo(rset.getInt("MEMBER_NO"));
				loginMember.setId(rset.getString("MEMBER_ID"));
				loginMember.setNickname(rset.getString("NICKNAME"));
				loginMember.setPhone(rset.getString("PHONE"));
				loginMember.setEmail(rset.getString("EMAIL"));
				loginMember.setAddress(rset.getString("ADDRESS"));
				loginMember.setEnrollDate(rset.getDate("ENROLL_DATE"));
				loginMember.setRole(rset.getString("MEMBER_ROLE"));
				loginMember.setStatus(rset.getString("MEMBER_STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginMember;
	}
	
}












