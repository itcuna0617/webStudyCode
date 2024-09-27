package com.ohgiraffers.section01.persistence;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/* EMPLOYEE 테이블에 매칭될 Employee 엔티티 클래스도 만들어 보기 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable{

	private static final long serialVersionUID = -316462327003547891L;

	@Id
    @Column(name = "EMP_ID")
	private String empId;
    
    @Column(name = "EMP_NAME")
	private String empName;
    
    @Column(name = "EMP_NO")
	private String empNo;
    
    @Column(name = "EMAIL")
	private String email;
    
    @Column(name = "PHONE")
	private String phone;
    
    @Column(name = "DEPT_CODE")
	private String deptCode;
    
    @Column(name = "JOB_CODE")
	private String jobCode;
    
    @Column(name = "SAL_LEVEL")
	private String salLevel;
    
    @Column(name = "SALARY")
	private int salary;
    
    @Column(name = "BONUS", nullable = true)	// null 값이 들어올 수 있을 경우 nullable 속성을 true로 주어야 한다.
	private Double bonus;						// null 값이 들어올 수 있는 경우 타입을 기본 자료형이 아닌 Wrapper 클래스로 써야 한다.
    
    @Column(name = "MANAGER_ID")
	private String managerId;
    
    @Column(name = "HIRE_DATE")
	private java.sql.Date hireDate;
    
    @Column(name = "ENT_DATE")
	private java.sql.Date entDate;
    
    @Column(name = "ENT_YN")
	private String entYn;

	public Employee() {
	}
	public Employee(String empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, String salLevel, int salary, Double bonus, String managerId, Date hireDate, Date entDate,
			String entYn) {
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salLevel = salLevel;
		this.salary = salary;
		this.bonus = bonus;
		this.managerId = managerId;
		this.hireDate = hireDate;
		this.entDate = entDate;
		this.entYn = entYn;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getSalLevel() {
		return salLevel;
	}
	public void setSalLevel(String salLevel) {
		this.salLevel = salLevel;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public java.sql.Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}
	public java.sql.Date getEntDate() {
		return entDate;
	}
	public void setEntDate(java.sql.Date entDate) {
		this.entDate = entDate;
	}
	public String getEntYn() {
		return entYn;
	}
	public void setEntYn(String entYn) {
		this.entYn = entYn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Member [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", email=" + email + ", phone="
				+ phone + ", deptCode=" + deptCode + ", jobCode=" + jobCode + ", salLevel=" + salLevel + ", salary="
				+ salary + ", bonus=" + bonus + ", managerId=" + managerId + ", hireDate=" + hireDate + ", entDate="
				+ entDate + ", entYn=" + entYn + "]";
	}
}
