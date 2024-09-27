package com.greedy.session.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.greedy.session.member.model.dto.AuthorityDTO;
import com.greedy.session.member.model.dto.MemberDTO;
import com.greedy.session.member.model.dto.MemberRoleDTO;

@Repository
public class MemberDAO {
	
	private JdbcTemplate jdbcTemplate;
	private Properties prop;
	
	@Autowired
	public MemberDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
		prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("C:\\springbootWorkspace\\chap03-security-session-lecture-source\\src\\main\\resources\\mappers\\member-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MemberDTO findMemberById(String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		MemberDTO selectedMember = null; 	
		
		String query = prop.getProperty("findMemberById");
		
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				selectedMember = new MemberDTO();
				selectedMember.setNo(rset.getInt("MEMBER_NO"));
				selectedMember.setId(rset.getString("MEMBER_ID"));
				selectedMember.setPwd(rset.getString("MEMBER_PWD"));
				selectedMember.setTempPwdYn(rset.getString("TEMP_PWD_YN"));
				selectedMember.setPwdChangedDatetime(rset.getDate("PWD_CHANGED_DATETIME"));
				selectedMember.setPwdExpDate(rset.getString("PWD_EXP_DATE"));
				selectedMember.setName(rset.getString("MEMBER_NAME"));
				selectedMember.setRegistDatetime(rset.getDate("MEMBER_REGIST_DATETIME"));
				selectedMember.setAccumLoginCount(rset.getInt("ACCUM_LOGIN_COUNT"));
				selectedMember.setLoginFailedCount(rset.getInt("LOGIN_FAILED_COUNT"));
				selectedMember.setAccLockYn(rset.getString("ACC_LOCK_YN"));
				selectedMember.setAccInactiveYn(rset.getString("ACC_INACTIVE_YN"));
				selectedMember.setAccExpDate(rset.getString("ACC_EXP_DATE"));
				selectedMember.setAccExpYn(rset.getString("ACC_EXP_YN"));
				selectedMember.setAccSecessionDatetime(rset.getDate("ACC_SECESSION_DATETIME"));
				selectedMember.setAccSecessionYn(rset.getString("ACC_SECESSION_YN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset!=null && pstmt!=null) {
					rset.close();
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return selectedMember;
	}

	public List<MemberRoleDTO> findMemberRoleByMemberNo(int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<MemberRoleDTO> memberRoleList = new ArrayList<>(); 	
		
		String query = prop.getProperty("findMemberRoleByMemberNo");
		
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MemberRoleDTO memberRole = new MemberRoleDTO();
				memberRole.setMemberNo(rset.getInt("MEMBER_NO"));
				memberRole.setAuthorityCode(rset.getInt("AUTHORITY_CODE"));
				
				memberRoleList.add(memberRole);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset!=null && pstmt!=null) {
					rset.close();
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return memberRoleList;
	}
	
	public AuthorityDTO findAuthorityByAuthorityCode(int authorityCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		AuthorityDTO authority = null; 	
		
		String query = prop.getProperty("findAuthorityByAuthorityCode");
		
		try {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, authorityCode);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				authority = new AuthorityDTO();
				authority.setCode(rset.getInt("AUTHORITY_CODE"));
				authority.setName(rset.getString("AUTHORITY_NAME"));
				authority.setDesc(rset.getString("AUTHORITY_DESC"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset!=null && pstmt!=null) {
					rset.close();
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return authority;
	}
}




