-- 시스템 계정으로 시작하기
-- <사용자 관리(관리자 계정의 역할)>
-- : 사용자의 계정과 암호설정, 권한부여

-- 오라클 데이터베이스를 설치하면 기본적으로 제공되는 계정
-- 1. SYS(관리자 계정)
-- 2. SYSTEM(관리자 계정)

-- 1. 시스템 권한: 데이터베이스 관리자가 가지고 있는 권한으로
--               오라클 접속, 테이블, 뷰, 인덱스 등의 생성 권한
--               CREATE USER(사용자 계정 만들기)
--               DROP USER(사용자 계정 삭제)
--               DROP ANY TABLE(임의의 테이블 삭제)
--               QUERY REWRITE(함수 기반 인덱스 생성 권한)
--               BACKUP ANY TABLE(테이블 백업)
               
--    시스템 관리자가 사용자에게 부여하는 권한
--               CREATE SESSION(데이터베이스에 접속)
--               CREATE TABLE(테이블 생성)
--               CREATE VIEW(뷰 생성)
--               CREATE SEQUENCE(시퀀스 생성)
--               CREATE SYNONYM(동의어 생성)
--               CREATE PROCEDURE(프로시저 생성)

-- <SYSTEM 계정으로 가서 실행>
CREATE USER C##SAMPLE IDENTIFIED BY SAMPLE;
GRANT CREATE SESSION, CREATE TABLE TO C##SAMPLE;

-- 생성한 SAMPLE 계정으로 접속해 보자.
-- 접속 후 테이블 생성
 CREATE TABLE TEST_TABLE(
   COL1 VARCHAR2(20),
   COL2 NUMBER
 );

-- WITH ADMIN OPTION
-- : 사용자에게 시스템 권한을 부여할 때 사용함
--   권한을 부여 받은 사용자는 다른 사용자에게 해당 권한을 지정할 수 있음
-- <SYSTEM 계정으로 권한 부여>
GRANT CREATE SESSION TO C##SAMPLE
WITH ADMIN OPTION;

-- <SYSTEM 계정으로 SAMPLE2 계정 생성하기>
CREATE USER C##SAMPLE2 IDENTIFIED BY SAMPLE2;

-- <SAMPLE 계정으로 SAMPLE2 계정에게 권한 부여 가능함을 확인하자>
GRANT CREATE SESSION TO C##SAMPLE2
WITH ADMIN OPTION;

-- 2. 객체 권한: 사용자가 특정 객체(테이블, 뷰, 시퀀스, 함수)를 조작하거나 접근할 수 있는 권한
--              DML(SELECT/INSERT/UPDATE/DELETE)
--              GRANT 권한종류[(컬럼명)] | ALL
--              ON 객체명 | ROLE 이름 | PUBLIC
--              TO 사용자 이름;
--
-- 객체를 DML 처리하는 권한 종류
--              ALTER TABLE, SEQUENCE
--              DELETE TABLE, VIEW
--              EXECUTE PROCEDURE
--              INDEX TABLE
--              REFERENCES TABLE
--              INSERT TABLE, VIEW
--              SELECT TABLE, VIEW, SEQUENCE
--              UPDATE TABLE, VIEW

-- WITH GRANT OPTION
-- : 사용자가 특정 객체를 조작하거나 접근 할 수 있는 권한을 부여 받으면서
--   그 권한을 다른 사용자에게 다시 부여할 수 있는 옵션

-- <SYSTEM 계정으로 하기>
 GRANT SELECT ON C##EMPLOYEE.EMPLOYEE TO C##SAMPLE
 WITH GRANT OPTION;

-- <SAMPLE 계정으로 하기>
 SELECT * FROM C##EMPLOYEE.EMPLOYEE;

-- SAMPLE 계정이 SAMPLE2계정에게 권한 부여 가능함을 확인
 GRANT SELECT ON C##EMPLOYEE.EMPLOYEE TO C##SAMPLE2
 WITH GRANT OPTION;
 
-- 권한 철회
-- : REVOKE 명령어 사용
-- <SYSTEM 계정으로 하기>
 REVOKE SELECT ON C##EMPLOYEE.EMPLOYEE FROM C##SAMPLE;
 
-- 참고(WITH GRANT OPTION과 WITH ADMIN OPTION의 차이점)
-- WITH GRANT OPTION은 REVOKE 시 다른 사용자에게도 부여했던 권한을 모두 같이 회수
-- WITH ADMIN OPTION은 특정 사용자의 권한만 회수가 되고 나머지 다른 사용자에게 부여한
-- 권한은 회수가 되지 않음
 
-- 롤(ROLE)
-- : 사용자에게 보다 간편하게 권한을 부여할 수 있도록
--   여러 개의 권한을 묶어 놓은 것
--   => 사용자 권한 관리를 보다 간편하고 효율적으로 할 수 있게 함
--      다수의 사용자에게 공통적으로 필요한 권한들을 하나의 롤로 묶고
--      사용자에게는 특정 롤에 대한 권한을 부여할 수 있도록 함
--      
--      사용자에게 부여한 권한을 수정하고자 할 때도
--      롤만 수정하면 그롤에 대한 권한을 부여받은 사용자들의 권한이
--      자동으로 수정된다.
--      롤을 활성화 하거나 비활성화 해서 일시적으로 권한을 부여하고
--      철회할 수 있음
      
-- 롤의 종류
-- 1. 사전 정의된 롤
-- : 오라클 설치 시 시스템에서 기본적으로 제공됨
--   CONNECT ROLE
--   RESOURCE ROLE
-- 
-- 2. 사용자가 정의하는 롤
-- : CREATE ROLE 명령으로 롤 생성
-- 롤 생성은 반드시 DBA권한이 있는 사용자(SYS, SYSTEM)만 할 수 있음
-- 18C버전은 롤 이름에 반드시 C##을 붙여야 한다.
-- CREATE ROLE 롤이름;       -- 1. 롤 생성
-- GRANT 권한종류 TO 롤이름; -- 2. 생성 된 롤에 권한들 추가
-- GRANT 롤이름 TO 계정명;   -- 3. 사용자에게 만들어진 롤 부여

-- <SYSTEM 계정에서 하기>
 CREATE ROLE C##MYROLE;

 GRANT CREATE VIEW, CREATE SEQUENCE TO C##MYROLE;
 REVOKE CREATE SEQUENCE FROM C##MYROLE;
 GRANT C##MYROLE TO C##SAMPLE;
   
-- SAMPLE 계정은 ROLE을 부여 받으면 반드시!! 접속 해제 했다가 다시 접속해야 ROLE의 권한들이 적용된다.
   
 
 
 
 
 
 
 
 