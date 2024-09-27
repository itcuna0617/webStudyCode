package com.greedy.section01;

public class PersonalAccount implements Account{

	private int bankCode;			// 은행번호
	private String accNo;			// 계좌번호
	private String accPwd;			// 계좌비번
	private int balance;			// 계좌잔액
	
	public PersonalAccount() {}
	public PersonalAccount(int bankCode, String accNo, String accPwd, int balance) {
		this.bankCode = bankCode;
		this.accNo = accNo;
		this.accPwd = accPwd;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "PersonalAccount [bankCode=" + bankCode + ", accNo=" + accNo + ", accPwd=" + accPwd + ", balance="
				+ balance + "]";
	}
	
	/* 현재 잔액을 문자열로 반환 해주는 메소드 */
	@Override
	public String getBalance() {
		return this.accNo + " 계좌의 현재 잔액은 " + this.balance + "원 입니다.";
	}

	/* 금액을 매개변수로 전달 받아 잔액을 증가(입금) 시켜주는 메솓, */
	@Override
	public String deposit(int money) {
		String str = "";
		
		if(money >= 0) {
			this.balance += money;
			str = money + "원이 입금 되었습니다.";
		} else {
			str = "금액을 잘못 입력하셨습니다.";
		}
		
		return str;
	}

	/* 금액을 매개변수로 전달 받아 잔액을 감소(출금) 시켜주는 메소드 */
	@Override
	public String withDraw(int money) {
		String str = "";
		
		if(money >= 0 && this.balance >= money) {
			this.balance -= money;
			str = money + "원이 출금 되었습니다.";
		} else {
			str = "금액을 잘못 입력 하셨거나 잔액이 부족합니다.";
		}
		
		return str;
	}

}




