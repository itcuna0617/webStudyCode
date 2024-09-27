package com.ohgiraffers.bootrestapi.exception;

public class DuplicatedMemberEmailException extends RuntimeException{
	public DuplicatedMemberEmailException(String message) {
		super(message);
	}
}
