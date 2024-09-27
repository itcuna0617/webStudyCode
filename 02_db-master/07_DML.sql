-- DML(Data Manipulation Language)
-- INSERT, UPDATE, DELETE, SELECT(DQL)
-- : 데이터 조작언어, 테이블에 값을 삽입하거나, 수정하거나,
--   삭제하거나, 조회하는 언어
   
-- INSERT: 새로운 행을 추가하는 구문이다.
--         테이블의 행 갯수가 증가한다.
-- INSERT INTO 테이블명(컬럼명, 컬럼명, ...) VALUES(데이터, 데이터, ...)

DESC EMPLOYEE;

INSERT
  INTO EMPLOYEE
(
  EMP_ID, EMP_NAME, EMP_NO
, EMAIL, PHONE, DEPT_CODE
, JOB_CODE, SAL_LEVEL, SALARY
, BONUS, MANAGER_ID, HIRE_DATE
, ENT_DATE, ENT_YN
)
VALUES
(
  900, '하이유', '901123-1080503'
, 'jang_ch@greedy.or.kr', '01055556912', 'D1'
, 'J1', 'S5', 2800000
, 0.1, '200', SYSDATE
, NULL, DEFAULT    -- DEFAULT는 해당 컬럼에 DEFAULT 값으로 설정된 값이 INSERT 된다.
                   -- INSERT 시에 해당 컬럼을 INSERT 하지 않으면 DEFAULT 값이 해당 컬럼에 들어간다.(NULL대신)
);

INSERT
  INTO EMPLOYEE
(
  EMP_ID, EMP_NAME, EMP_NO      -- 나머지 컬럼은 기본적으로 NULL이 들어가고 DEFAULT 값이 설정된 컬럼은 DEFAULT 값이 들어간다.
, EMAIL, JOB_CODE, SAL_LEVEL
)
VALUES
(
  901, '로우유', '901123-1080504'
, 'jang_ch2@greedy.or.kr', 'J1', 'S5'
);

SELECT
       A.*
  FROM EMPLOYEE A
 WHERE A.EMP_ID = '901';

ROLLBACK;
COMMIT;

-- INSERT 시에 VALUES 대신 서브쿼리를 이용할 수 있다.
CREATE TABLE EMP_01(
  EMP_ID NUMBER,
  EMP_NAME VARCHAR2(30),
  DEPT_TITLE VARCHAR2(20)
);

INSERT
  INTO EMP_01
(
  SELECT A.EMP_ID
       , A.EMP_NAME
       , B.DEPT_TITLE
    FROM EMPLOYEE A
    LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
);

SELECT * FROM EMP_01;

-- INSERT ALL: INSERT 시에 사용하는 서브쿼리가 같은 경우
--             두 개 이상의 테이블에 INSERT ALL을 이용하여
--             한번에 데이터를 삽입할 수 있다.
--             단, 각 서브쿼리의 조건절이 같아야 한다.
DROP TABLE EMP_DEPT_D1;
CREATE TABLE EMP_DEPT_D1
AS
SELECT A.EMP_ID
     , A.EMP_NAME
     , A.DEPT_CODE
     , A.HIRE_DATE
  FROM EMPLOYEE A
 WHERE 1 = 0;   -- 데이터는 빼고 자료형과 NOT NULL 제약조건만 복사해서 만드는 테이블

SELECT * FROM EMP_DEPT_D1;

DROP TABLE EMP_MANAGER;
CREATE TABLE EMP_MANAGER
AS
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.MANAGER_ID
  FROM EMPLOYEE A
 WHERE 1 = 0;

INSERT
  INTO EMP_DEPT_D1
(
  SELECT A.EMP_ID
       , A.EMP_NAME
       , A.DEPT_CODE
       , A.HIRE_DATE
    FROM EMPLOYEE A
   WHERE A.DEPT_CODE = 'D1'
);

INSERT
  INTO EMP_MANAGER
(
  SELECT A.EMP_ID
       , A.EMP_NAME
       , A.MANAGER_ID
    FROM EMPLOYEE A
   WHERE A.DEPT_CODE = 'D1'
);

DELETE
  FROM EMP_DEPT_D1;
DELETE
  FROM EMP_MANAGER;
  
SELECT * FROM EMP_DEPT_D1;
SELECT * FROM EMP_MANAGER;

-- INSERT ALL을 활용한 하나의 쿼리문
INSERT ALL
  INTO EMP_DEPT_D1
VALUES
(
  EMP_ID
, EMP_NAME
, DEPT_CODE
, HIRE_DATE
)
  INTO EMP_MANAGER
VALUES
(
  EMP_ID
, EMP_NAME
, MANAGER_ID
)
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.DEPT_CODE
     , A.HIRE_DATE
     , A.MANAGER_ID
  FROM EMPLOYEE A
 WHERE A.DEPT_CODE = 'D1';

-- EMPLOYEE 테이블에서 입사일 기준으로 2000년 1월 1일 이전에 입사한
-- 사원의 사번, 이름, 입사일, 급여를 조회하여
-- EMP_OLD 테이블에 삽입하고
-- 그 이후에 입사한 사원은 EMP_NEW 테이블에 삽입하시오.
CREATE TABLE EMP_OLD
AS
SELECT A.EMP_ID
     , A.EMP_NAME
     , A.HIRE_DATE
     , A.SALARY
  FROM EMPLOYEE A
 WHERE 1 = 0;

CREATE TABLE EMP_NEW
AS
SELECT A.EMP_ID
     , A.EMP_NAME
     , A.HIRE_DATE
     , A.SALARY
  FROM EMPLOYEE A
 WHERE 1 = 0;

INSERT ALL
  WHEN HIRE_DATE < TO_DATE('2000/01/01', 'RRRR/MM/DD')
  THEN
  INTO EMP_OLD
VALUES
(
  EMP_ID
, EMP_NAME
, HIRE_DATE
, SALARY
)
  WHEN HIRE_DATE >= TO_DATE('2000/01/01', 'RRRR/MM/DD')
  THEN
  INTO EMP_NEW
VALUES
(
  EMP_ID
, EMP_NAME
, HIRE_DATE
, SALARY
)
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.HIRE_DATE
     , A.SALARY
  FROM EMPLOYEE A;

SELECT * FROM EMP_OLD;
SELECT * FROM EMP_NEW;

-- UPDATE: 테이블에 기록된 컬럼의 값을 수정하는 구문이다.
--         테이블의 전체 행 갯수는 변화가 없다.
CREATE TABLE DEPT_COPY
AS
SELECT A.*
  FROM DEPARTMENT A;

SELECT * FROM DEPT_COPY;
    
-- UPDATE 테이블명 SET 컬럼명 = 바꿀값, 컬럼명 = 바꿀값, ...
-- [WHERE 컬럼명 비교연산자 비교값]
UPDATE
       DEPT_COPY A
   SET A.DEPT_TITLE = '전략기획팀'
 WHERE A.DEPT_ID = 'D9';
SELECT * FROM DEPT_COPY;
ROLLBACK;
COMMIT;

-- UPDATE 시에도 서브쿼리를 이용할 수 있다.
-- UPDATE 테이블명
-- SET 컬럼명 = (서브쿼리)
CREATE TABLE EMP_SALARY
AS
SELECT A.EMP_ID
     , A.EMP_NAME 
     , A.DEPT_CODE
     , A.SALARY
     , A.BONUS
  FROM EMPLOYEE A;

SELECT
       A.*
  FROM EMP_SALARY A
 WHERE A.EMP_NAME IN ('유재식', '방명수');

-- 평상 시에 유재식 사원을 부러워 하던 방명수 사원의
-- 급여와 보너스율을 유재식 사원과 동일하게 변경해 주기로 했다.
-- 이를 반영하는 UPDATE 문을 작성해 보시오.
UPDATE
       EMP_SALARY A
   SET A.SALARY = (SELECT B.SALARY
                     FROM EMP_SALARY B
                    WHERE B.EMP_NAME = '유재식'
                  )
     , A.BONUS = (SELECT C.BONUS
                     FROM EMP_SALARY C
                    WHERE C.EMP_NAME = '유재식'
                 )
 WHERE A.EMP_NAME = '방명수';
 
SELECT
       A.*
  FROM EMP_SALARY A
 WHERE A.EMP_NAME IN ('유재식', '방명수');

-- 다중열 서브쿼리를 이용한 업데이트문
-- 방명수 사원의 급여 인상 소식을 전해들은 다른 직원들이
-- 단체로 파업을 진행했다.
-- 노옹철, 전형돈, 정중하, 하동운 사원의 급여와 보너스를
-- 유재식 사원의 급여 및 보너스와 같게 변경하는 UPDATE문을 작성
UPDATE
       EMP_SALARY A
   SET (A.SALARY, A.BONUS) = (SELECT B.SALARY
                                   , B.BONUS
                                FROM EMP_SALARY B
                               WHERE B.EMP_NAME = '유재식'
                             )
 WHERE A.EMP_NAME IN ('노옹철', '전형돈', '정중하', '하동운');

SELECT
       A.*
  FROM EMP_SALARY A
 WHERE EMP_NAME IN ('유재식', '노옹철', '전형돈', '정중하', '하동운');

-- 다중행 서브쿼리를 이용한 UPDATE
-- EMP_SALARY 테이블에서 아시아 지역에 근무하는 직원의
-- 보너스를 0.5로 변경하시오.
SELECT
       A.EMP_ID
     , C.LOCAL_NAME
  FROM EMP_SALARY A
  JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  JOIN LOCATION C ON (B.LOCATION_ID = C.LOCAL_CODE)
 WHERE C.LOCAL_NAME LIKE 'ASIA%';

UPDATE
       EMP_SALARY A
   SET A.BONUS = 0.5
 WHERE A.EMP_ID IN (SELECT A.EMP_ID
                      FROM EMP_SALARY A
                      JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
                      JOIN LOCATION C ON (B.LOCATION_ID = C.LOCAL_CODE)
                     WHERE C.LOCAL_NAME LIKE 'ASIA%'
                   );

-- UPDATE 시 변경할 값은 해당 컬럼에 대한 제약조건에 위배되지 않도록 해야 함
UPDATE
       EMPLOYEE A
   SET A.DEPT_CODE = '10'  -- FOREIGN KEY 제약조건 위배 됨
 WHERE A.DEPT_CODE = 'D6';
ROLLBACK;

UPDATE
       EMPLOYEE A
   SET A.EMP_NAME = NULL  -- NOT NULL 제약조건 위배 됨
 WHERE A.EMP_ID = '200';

UPDATE
       EMPLOYEE A
   SET A.EMP_NO = '631106-1548654'  -- UNIQUE 제약조건 위배 됨
 WHERE A.EMP_ID = '202';

-- DELETE: 테이블의 행을 삭제하는 구문이다.
--         테이블의 행의 갯수가 줄어든다.
-- DELETE FROM 테이블명 [WHERE 조건절]
-- 만약 WHERE 조건을 설정하지 않으면 모든 행이 다 삭제된다.
SELECT * FROM EMPLOYEE WHERE EMP_NAME = '하이유';
DELETE FROM EMPLOYEE;
ROLLBACK;

DELETE
  FROM EMPLOYEE A
 WHERE A.EMP_NAME = '하이유'; -- WHERE절로 행을 선택할 때는 PK컬럼으로 처리하자.
 
-- FOREIGN KEY 제약조건이 설정되어 있는 경우
-- 자식테이블에 의해 참조되고 있는 값에 대해서는 부모테이블에서 삭제할 수 없다.(기본적으로는)
DELETE
  FROM DEPARTMENT A
 WHERE A.DEPT_ID = 'D1';
  
-- 제약조건과 관련된 딕셔너리 뷰를 통해 제약조건을 확인하자.
SELECT * FROM USER_CONSTRAINTS;
SELECT * FROM USER_CONS_COLUMNS;

-- 원하는 테이블에서 제약조건과 관련된 필수적인 컬럼만 추출해서 조인한 SELECT문 ★★
SELECT
       A.TABLE_NAME
     , A.CONSTRAINT_NAME
     , A.SEARCH_CONDITION
     , A.CONSTRAINT_TYPE
     , B.COLUMN_NAME
  FROM USER_CONSTRAINTS A
  JOIN USER_CONS_COLUMNS B ON (A.CONSTRAINT_NAME = B.CONSTRAINT_NAME)
 WHERE A.TABLE_NAME = 'EMPLOYEE';

-- 제약조건 잠시 끄기
ALTER TABLE EMPLOYEE
DISABLE CONSTRAINT SYS_C007689; 

DELETE
  FROM DEPARTMENT A
 WHERE A.DEPT_ID = 'D1';

ROLLBACK;

-- 제약조건 다시 켜기
ALTER TABLE EMPLOYEE
ENABLE CONSTRAINT SYS_C007689;

-- TRUNCATE: 테이블의 전체 행을 삭제할 시 사용한다.
--           DELETE보다 수행 속도가 더 빠르다.
--           ROLLBACK을 통해 복구할 수 없다.
SELECT * FROM EMP_SALARY;

TRUNCATE TABLE EMP_SALARY; -- 테이블 초기화
ROLLBACK;

-- MERGE: 구조가 같은 두 개의 테이블을 하나의 테이블을 기준으로 합치기(병합) 기능을 한다.
--        테이블에서 지정하는 조건의 값이 존재하면 UPDATE, 조건의 값이 없으면 INSERT 된다.
CREATE TABLE EMP_M01
AS
SELECT A.*
  FROM EMPLOYEE A;
  
CREATE TABLE EMP_M02
AS
SELECT A.*
  FROM EMPLOYEE A
 WHERE A.JOB_CODE = 'J4';

INSERT
  INTO EMP_M02
VALUES
(
  999, '김용승', '000101-3123456', 'dlagon77@hanmail.net', '01092880100'
, 'D9', 'J4', 'S1', 9000000, 0.5
, NULL, SYSDATE, NULL, DEFAULT
);

UPDATE
       EMP_M02 A
   SET A.SALARY = 0;
ROLLBACK;

 MERGE
  INTO EMP_M01 A
 USING EMP_M02 B
    ON (A.EMP_ID = B.EMP_ID)
  WHEN MATCHED THEN
UPDATE
   SET A.EMP_NAME = B.EMP_NAME
     , A.EMP_NO = B.EMP_NO
     , A.EMAIL = B.EMAIL
     , A.PHONE = B.PHONE
     , A.DEPT_CODE = B.DEPT_CODE
     , A.JOB_CODE = B.JOB_CODE
     , A.SAL_LEVEL = B.SAL_LEVEL
     , A.SALARY = B.SALARY
     , A.BONUS = B.BONUS
     , A.MANAGER_ID = B.MANAGER_ID
     , A.HIRE_DATE = B.HIRE_DATE
     , A.ENT_DATE = B.ENT_DATE
     , A.ENT_YN = B.ENT_YN
  WHEN NOT MATCHED THEN
INSERT
(
  A.EMP_ID, A.EMP_NAME, A.EMP_NO, A.EMAIL, A.PHONE
, A.DEPT_CODE, A.JOB_CODE, A.SAL_LEVEL, A.SALARY, A.BONUS
, A.MANAGER_ID, A.HIRE_DATE, A.ENT_DATE, A.ENT_YN
)
VALUES
(
  B.EMP_ID, B.EMP_NAME, B.EMP_NO, B.EMAIL, B.PHONE
, B.DEPT_CODE, B.JOB_CODE, B.SAL_LEVEL, B.SALARY, B.BONUS
, B.MANAGER_ID, B.HIRE_DATE, B.ENT_DATE, B.ENT_YN
);

SELECT * FROM EMP_M01;
SELECT * FROM EMP_M02;












