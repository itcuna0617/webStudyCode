package com.ohgiraffers.section04.enumtype;

/* JPA에서는 enum(열거형) 클래스(enumeration type)를 사용할 수 있다. */
/* enum클래스로 빼서 관리하는 것은 사실상 도메인의 성격을 지닌다. */
public enum RoleType {
	ADMIN, MEMBER		// ADMIN과 MEMBER는 정의된 순서대로 각각 0과 1이라는 숫자로 정의된 값으로 인식하고 반환 받을 수도 있다.
}
