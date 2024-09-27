-- 동의어(SYNONYM)
-- 다른 데이터베이스가 가진 객체에 대한 별명 혹은 줄임말
-- 여러 사용자가 테이블을 공유 할 경우
-- 다른 사용자가 테이블에 접근할 경우 '사용자명.테이블명'으로 표현함
-- 동의어를 사용하면 간단하게 사용할 수 있다.
 
-- 생성 방법
-- CREATE SYNONYM 줄임말 FOR [사용자명.]객체명;

-- <SYNONYM 객체를 생성하기 위해 관리자 계정에서 실행 할 내용> 
-- GRANT CREATE SYNONYM TO C##EMPLOYEE;
 
CREATE SYNONYM EMP FOR EMPLOYEE;

SELECT
       *
  FROM EMP;
  
SELECT
       *
  FROM EMPLOYEE;
  
-- 동의어 구분
-- 1. 비공개 동의어
--    : 객체에 대한 접근 권한을 부여받은 사용자가 정의한 동의어
-- 2. 공개 동의어
--    : 모든 권한을 주는 사용자(DBA)가 정의한 동의어
--      모든 사용자가 사용할 수 있음(PUBLIC)

-- <관리자 계정에서 공개 동의어 설정을 위해 실행할 내용>
--SELECT
--       A.*
--  FROM C##EMPLOYEE.DEPARTMENT A;   -- 관리자 계정은 일반 계정들의 테이블에 접근이 가능하다.
--  
--CREATE PUBLIC SYNONYM DEPT FOR C##EMPLOYEE.DEPARTMENT;

SELECT
       A.*
  FROM DEPT A;


