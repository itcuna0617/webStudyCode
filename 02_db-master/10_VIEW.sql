-- VIEW(뷰)
-- SELECT 쿼리문을 저장한 객체로 가상테이블이라고 불린다.
-- 실질적인 데이터를 물리적으로 저장하고 있지 않음
-- 테이블을 사용하는 것과 동일하게 사용할 수 있다.
-- CREATE [OR REPLACE] VIEW 뷰이름 AS 서브쿼리
 
-- EMPLOYEE 테이블로부터 사번, 이름, 직급코드, 부서코드를 조회하고
-- 그 결과를 V_RESULT_EMP라는 뷰를 생성해서 저장하자.
DROP VIEW V_RESULT_EMP;
CREATE OR REPLACE VIEW V_RESULT_EMP
AS
SELECT EMP_ID
     , EMP_NAME
     , JOB_CODE
     , DEPT_CODE
  FROM EMPLOYEE;
  
SELECT
       *
  FROM V_RESULT_EMP;
  
-- 베이스 테이블의 정보가 변경되면 VIEW의 결과도 같이 변경된다.
SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE '정%';
UPDATE
       EMPLOYEE
   SET EMP_NAME = '정중앙';

SELECT
       *
  FROM V_RESULT_EMP
 WHERE EMP_ID = '205';
ROLLBACK;

-- 데이터 딕셔너리 뷰(Data Dictionary View)
-- 자원을 효율적으로 관리하기 위해 다양한 정보를 저장하는 시스템 테이블(데이터 딕셔너리)
-- 사용자가 테이블을 생성하거나, 사용자를 변경하는 등의 작업을 할 때
-- 데이터베이스 서버에 의해 자동으로 갱신되는 테이블(데이터 딕셔너리)
 
-- 원본 테이블을 커스터마이징 해서 보여주는 베이스 테이블(데이터 딕셔너리)의 가상 테이블(데이터 딕셔너리 뷰)
 
-- 딕셔너리 뷰의 3가지 종류
-- 1. DBA_XXX: 데이터베이스 관리자만 접근이 가능한 객체들의 정보 조회
-- 2. ALL_XXX: 자신의 계정 + 권한을 부여받은 객체의 정보 조회
-- 3. USER_XXX: 자신의 계정이 소유한 객체 등에 관한 정보 조회

-- 계정이 가진 뷰 확인  
SELECT
       *
  FROM USER_VIEWS;

-- 계정이 가진 테이블 확인
SELECT
       *
  FROM USER_TABLES;
  
-- 계정이 가진 테이블과 컬럼을 확인
SELECT
       *
  FROM USER_TAB_COLUMNS
 WHERE TABLE_NAME = 'EMPLOYEE';

CREATE OR REPLACE VIEW V_RESULT_EMP
(
  사번
, 이름
, 직급명
, 부서명
, 지역명
)
AS
SELECT A.EMP_ID
     , A.EMP_NAME
     , B.JOB_NAME
     , C.DEPT_TITLE
     , D.LOCAL_NAME
  FROM EMPLOYEE A
  LEFT JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE)
  LEFT JOIN DEPARTMENT C ON (A.DEPT_CODE = C.DEPT_ID)
  LEFT JOIN LOCATION D ON (C.LOCATION_ID = D.LOCAL_CODE);

SELECT * FROM V_RESULT_EMP;

-- 뷰 서브쿼리 안에 연산 결과의 컬럼도 포함할 수 있다.
CREATE OR REPLACE VIEW V_EMP_JOB
(
  사번
, 이름
, 직급명
, 성별
, 근무년수
)
AS
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.JOB_NAME
     , DECODE(SUBSTR(A.EMP_NO, 8, 1), '1', '남', '여')
     , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM A.HIRE_DATE)
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE);
  
SELECT * FROM V_EMP_JOB;

-- 뷰로 INSERT 시
CREATE OR REPLACE VIEW V_JOB
AS
SELECT A.JOB_CODE
     , A.JOB_NAME
  FROM JOB A;

INSERT
  INTO V_JOB
VALUES
(
  'J8'
, '인턴'
);

SELECT * FROM JOB;

-- 뷰로 UPDATE 시
UPDATE
       V_JOB A
   SET A.JOB_NAME = '알바'
 WHERE A.JOB_CODE = 'J8';
 
-- 뷰로 DELETE 시
DELETE
  FROM V_JOB A
 WHERE A.JOB_CODE = 'J8';

-- DML 명령어로 조작이 불가능한 경우
-- 1. 뷰 정의에 포함되지 않은 컬럼을 조작하는 경우
-- 2. 뷰에 포함되지 않은 컬럼 중에,
--    베이스가 되는 테이블 컬럼이 NOT NULL 제약조건이 지정된 경우
-- 3. 산술표현식으로 정의 된 경우
-- 4. JOIN을 이용해 여러 테이블을 연결한 경우
-- 5. DISTINCT를 포함한 경우
-- 6. 그룹함수나 GROUP BY 절을 포함한 경우
 
-- 뷰 정의에 포함되지 않은 컬럼을 조작하는 경우
CREATE OR REPLACE VIEW V_JOB2
AS
SELECT A.JOB_CODE
  FROM JOB A;
  
INSERT
  INTO V_JOB2
(
  JOB_CODE
, JOB_NAME
)
VALUES
(
  'J8'
, '인턴'
);
  
UPDATE
       V_JOB2 A
   SET A.JOB_NAME = '인턴'
 WHERE A.JOB_CODE = 'J7';
 
-- DELETE는 해당컬럼을 통해 나머지 컬럼도 적용 됨
INSERT
  INTO V_JOB2
(
  JOB_CODE
)
VALUES
(
  'J8'
);

SELECT * FROM JOB;
 
DELETE
  FROM V_JOB2 A
 WHERE A.JOB_CODE = 'J8';
 
-- 산술 표현식으로 정의 된 경우
CREATE OR REPLACE VIEW V_SAL
AS
SELECT A.EMP_ID
     , A.EMP_NAME
     , A.SALARY
     , (A.SALARY + (A.SALARY * NVL(A.BONUS, 0))) * 12 연봉
  FROM EMPLOYEE A;

SELECT * FROM V_SAL;
 
INSERT
  INTO V_SAL
(
  EMP_ID
, EMP_NAME
, SALARY
, 연봉
)
VALUES
(
  '800'
, '정진훈'
, 3000000
, 4000000
);          -- 에러남

UPDATE
       V_SAL A
   SET A.연봉 = 80000000
 WHERE A.EMP_ID = '200';  -- 에러남

DELETE
  FROM V_SAL A
 WHERE A.연봉 = 124800000; -- DELETE 때는 가능

-- JOIN을 이용해 여러 테이블을 연결한 경우
CREATE OR REPLACE VIEW V_JOINEMP
AS
SELECT A.EMP_ID
     , A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);

INSERT
  INTO V_JOINEMP
(
  EMP_ID
, EMP_NAME
, DEPT_TITLE
)
VALUES
(
  '888'
, '조세오'
, '인사관리부'
);             -- 에러남

UPDATE
       V_JOINEMP A
   SET A.DEPT_TITLE = '인사관리부'
 WHERE A.EMP_ID = '219';   -- 에러남

-- 하나의 베이스 테이블에만 영향을 주며 삭제 됨
DELETE
  FROM V_JOINEMP A
 WHERE A.EMP_ID = '219';

SELECT * FROM EMPLOYEE;
ROLLBACK;

-- DISTINCT를 포함한 경우
CREATE OR REPLACE VIEW V_DT_EMP
AS
SELECT DISTINCT JOB_CODE
  FROM EMPLOYEE;

INSERT
  INTO V_DT_EMP
(
  JOB_CODE
)
VALUES
(
  'J8'
);    -- 에러남

UPDATE
       V_DT_EMP A
   SET A.JOB_CODE = 'J8'
 WHERE A.JOB_CODE = 'J7';  -- 에러남
 
DELETE
  FROM V_DT_EMP A
 WHERE A.JOB_CODE = 'J7';  -- 에러남
 
-- 그룹 함수나 GROUP BY 절을 포함한 경우
CREATE OR REPLACE VIEW V_GROUP_DEPT
AS
SELECT
       DEPT_CODE
     , SUM(SALARY) 합계
     , AVG(SALARY) 평균
  FROM EMPLOYEE
 GROUP BY DEPT_CODE;

INSERT
  INTO V_GROUP_DEPT
(
  DEPT_CODE
, 합계
, 평균
)
VALUES
(
  'D0'
, 6000000
, 400000
);    -- 에러남

UPDATE
       V_GROUP_DEPT A
   SET A.DEPT_CODE = 'D10'
 WHERE A.DEPT_CODE = 'D1';  -- 에러남

DELETE
  FROM V_GROUP_DEPT A
 WHERE A.DEPT_CODE = 'D1';  -- 에러남

-- VIEW 옵션
-- OR REPLACE: 기존에 동일한 뷰 이름이 존재하는 경우 덮어쓰라는 옵션
-- FORCE: 서브쿼리에 사용 된 테이블이 존재하지 않아도 뷰 생성
CREATE OR REPLACE FORCE VIEW V_EMP
AS
SELECT TCODE
     , TNAME
     , TCONTENTS
  FROM TT;
 
CREATE OR REPLACE /* NOFORCE */ VIEW V_EMP
AS
SELECT TCODE
     , TNAME
     , TCONTENTS
  FROM TT;

-- WITH CHECK OPTION: 조건절에 사용된 컬럼의 값을 수정하지 못하게 한다.
CREATE OR REPLACE VIEW V_EMP2
AS
SELECT A.*
  FROM EMPLOYEE A
 WHERE MANAGER_ID = '200'
  WITH CHECK OPTION;
 
UPDATE
       V_EMP2
   SET MANAGER_ID = '900'
 WHERE MANAGER_ID = '200';
 
-- WITH READ ONLY: DML 수행이 불가능
CREATE OR REPLACE VIEW V_DEPT
AS
SELECT A.*
  FROM DEPARTMENT A
  WITH READ ONLY;
 
DELETE
  FROM V_DEPT;
  

 
 
 
 





