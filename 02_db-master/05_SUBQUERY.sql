-- SUBQUERY(서브쿼리)
-- 서브쿼리: 쿼리문 안에서 사용 된 쿼리문
 
-- 사원명이 노옹철인 사람의 부서 조회
SELECT
       DEPT_CODE
  FROM EMPLOYEE
 WHERE EMP_NAME = '노옹철';  -- 'D9'
 
-- 부서코드가 D9인 직원의 이름을 조회
SELECT
       EMP_NAME
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D9';
 
-- 노옹철 사원과 같은 부서에서 일하는 직원 명단 조회
SELECT
       EMP_NAME
  FROM EMPLOYEE
 WHERE DEPT_CODE = (SELECT DEPT_CODE
                      FROM EMPLOYEE
                     WHERE EMP_NAME = '노옹철'
                   )
   AND EMP_NAME != '노옹철';

-- 전 직원의 평균 급여보다 많은 급여를 받고 있는 직원의
-- 사번, 이름, 직급코드, 급여를 조회하세요
-- 서브쿼리
SELECT
       AVG(SALARY)
  FROM EMPLOYEE;

-- 메인쿼리  
SELECT
       EMP_ID
     , EMP_NAME
     , JOB_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY >= 3047662.60869565217391304347826086956522;

-- 서브쿼리를 포함한 메인 쿼리 
SELECT
       EMP_ID
     , EMP_NAME
     , JOB_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY >= (SELECT AVG(SALARY)
                    FROM EMPLOYEE
                 );
  
-- 서브쿼리의 유형
-- 단일행 서브쿼리: 서브쿼리의 조회 결과 값이 1개의 행일 때
-- 다중행 서브쿼리: 서브쿼리의 조회 결과 값이 행이 여러 개일 때
-- 다중열 서브쿼리: 서브쿼리의 조회 결과 값의 컬럼이 여러 개일 때
-- 다중행 다중열 서브쿼리: 조회 결과 행의 수와 열의 수가 여러 개일 때
 
-- 서브쿼리의 유형에 따라 서브쿼리 앞에 붙는 연산자가 다름
-- 단일행 서브쿼리 앞에는 일반 비교 연산자 사용
-- >, <, >=, <=, =, !=/<>/^= (서브쿼리)
 
-- 노옹철 사원의 급여보다 많이 받는 사원의
-- 사번, 이름, 부서코드, 직급코드, 급여를 조회
SELECT 
       SALARY
  FROM EMPLOYEE
 WHERE EMP_NAME = '노옹철';

SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , JOB_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY > (SELECT SALARY
                   FROM EMPLOYEE
                  WHERE EMP_NAME = '노옹철'
                );

-- 가장 적은 급여를 받는 직원의
-- 사번, 이름, 직급코드, 부서코드, 급여, 입사일을 조회
SELECT MIN(SALARY)
  FROM EMPLOYEE;
  
SELECT
       EMP_ID
     , EMP_NAME
     , JOB_CODE
     , DEPT_CODE
     , SALARY
     , HIRE_DATE
  FROM EMPLOYEE
 WHERE SALARY = (SELECT MIN(SALARY)
                   FROM EMPLOYEE
                );
                
-- 다중행 서브쿼리
-- 다중행 서브쿼리 앞에서는 일반 비교 연산자 사용 못함
-- IN / NOT IN: 여러 개의 결과 값 중에서 한 개라도 일치하는 값이 있다면
--              혹은 없다면이라는 의미
-- > ANY / < ANY: 여러 개의 결과 값 중에서 한 개라도 큰 / 작은 경우
--               가장 작은 값보다 크냐? / 가장 큰 값보다 작냐?
--               (서브쿼리의 결과들 중에 어떤 것보다도 크거나 작기만 하면 된다.)
-- > ALL / < ALL: 모든 값보다 큰 / 작은 경우
--               가장 큰 값보다 크냐? / 가장 작은 값보다 작냐?
--               (모든 서브쿼리의 결과들보다 크거나 작아야 한다.)
-- EXISTS / NOT EXISTS: 서브쿼리에만 사용하는 연산자로
--                      값이 존재하냐? / 존재하지 않느냐?
                      
-- 부서별 최고 급여를 받는 직원의 이름, 직급, 부서, 급여 조회
SELECT DEPT_CODE
     , MAX(SALARY)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE;
 
SELECT
       A.EMP_NAME
     , A.JOB_CODE
     , A.DEPT_CODE
     , A.SALARY
     , A.MANAGER_ID
     , C.JOB_NAME
  FROM EMPLOYEE A
  LEFT JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE)
 WHERE A.EMP_ID = (SELECT D.EMP_ID
                      FROM EMPLOYEE D
                     WHERE A.MANAGER_ID = D.EMP_ID
                   );
  
-- 관리자에 해당하는 직원에 대한 정보와 관리자가 아닌 직원의
-- 정보를 추출하여 조회하시오.
-- (사번, 이름, 부서명, 직급명, '관리자' AS 구분 / '직원' AS 구분)
-- (관리자를 두고 있는 사원들의 관리자를 중복값 없이 추출)
SELECT * FROM EMPLOYEE;

SELECT
       DISTINCT(MANAGER_ID)
  FROM EMPLOYEE
 WHERE MANAGER_ID IS NOT NULL;

-- 관리자 조회
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.DEPT_TITLE
     , C.JOB_NAME
     , '관리자' AS 구분
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  LEFT JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE)
 WHERE A.EMP_ID IN (SELECT DISTINCT(D.MANAGER_ID)
                      FROM EMPLOYEE D
                     WHERE D.MANAGER_ID IS NOT NULL
                   );

-- 직원 조회
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.DEPT_TITLE
     , C.JOB_NAME
     , '직원' AS 구분
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  LEFT JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE)
 WHERE A.EMP_ID NOT IN (SELECT DISTINCT(D.MANAGER_ID)
                          FROM EMPLOYEE D
                         WHERE D.MANAGER_ID IS NOT NULL
                       );

-- 직원 전체를 관리자와 직원으로 구분하여 한번에 조회
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.DEPT_TITLE
     , C.JOB_NAME
     , '관리자' AS 구분
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  LEFT JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE)
 WHERE A.EMP_ID IN (SELECT DISTINCT(D.MANAGER_ID)
                      FROM EMPLOYEE D
                     WHERE D.MANAGER_ID IS NOT NULL
                   )
UNION
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.DEPT_TITLE
     , C.JOB_NAME
     , '직원' AS 구분
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  LEFT JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE)
 WHERE A.EMP_ID NOT IN (SELECT DISTINCT(D.MANAGER_ID)
                          FROM EMPLOYEE D
                         WHERE D.MANAGER_ID IS NOT NULL
                       )
 ORDER BY 구분 DESC; -- 직원부터 볼 수 있게 정렬 하려면...
  
-- 차장 직급 급여의 가장 큰 값보다 많이 받는 과장 직급의(모든 차장들보다 급여를 많이 받는 과장)
-- 사번, 이름, 직급명, 급여를 조회
-- 단, > ALL 혹은 < ALL 연산자를 사용

-- 차장 직급들의 급여
SELECT A.SALARY
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE)
 WHERE B.JOB_NAME = '차장';

SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.JOB_NAME
     , A.SALARY
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE)
 WHERE B.JOB_NAME = '과장'
   AND A.SALARY > ALL (SELECT C.SALARY
                         FROM EMPLOYEE C
                         JOIN JOB D ON (C.JOB_CODE = D.JOB_CODE)
                        WHERE D.JOB_NAME = '차장'
                      );
                      
-- 대리 직급의 직원들 중에서 과장 직급의 최소 급여보다 많이 받는(과장 중에 아무나 한명 보다만 많이 받으면 됨)
-- 직원의 사번, 이름, 직급명, 급여를 조회
-- 단, > ANY 혹은 < ANY 연산자 사용
SELECT A.SALARY
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE)
 WHERE B.JOB_NAME = '과장';
 
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.JOB_NAME
     , A.SALARY
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE)
 WHERE B.JOB_NAME = '대리'
   AND A.SALARY > ANY (SELECT C.SALARY
                         FROM EMPLOYEE C
                         JOIN JOB D ON (C.JOB_CODE = D.JOB_CODE)
                        WHERE D.JOB_NAME = '과장'
                      );
    
-- EXISTS / NOT EXISTS
-- 서브쿼리의 결과가 있는지 없는지
SELECT A.EMP_NAME
  FROM EMPLOYEE A
 WHERE A.EMP_ID = '100';
 
SELECT 
       A.EMP_NAME
  FROM EMPLOYEE A
 WHERE NOT EXISTS (SELECT B.EMP_NAME
                     FROM EMPLOYEE B
                    WHERE B.EMP_ID = '100'
                  );

-- 조회를 결과를 ON/OFF하는 것과 같은 코드                  
SELECT 
       A.EMP_NAME
  FROM EMPLOYEE A
 WHERE 1 = 0;
 
-- 자기 직급의 평균 급여를 받고 있는 직원의
-- 사번, 이름, 직급코드, 급여를 조회
-- 단, 급여와 급여 평균은 만원 단위로 계산 하시오.(TRUNC(컬럼명, -4))
SELECT
       TRUNC(1234567, -4)
  FROM DUAL;

-- 직급별 평균 급여(만원단위)
SELECT TRUNC(AVG(A.SALARY), -4)
  FROM EMPLOYEE A
 GROUP BY A.JOB_CODE;

-- 직급별 평균과 일치하는 급여를 받는 사원 조회
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.JOB_CODE
     , A.SALARY
  FROM EMPLOYEE A
 WHERE A.SALARY IN (SELECT TRUNC(AVG(B.SALARY), -4)
                      FROM EMPLOYEE B
                     GROUP BY B.JOB_CODE
                   );

-- 직급별 평균과 일치하면서 해당 직급이 자신의 직급인 사원 조회
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.JOB_CODE
     , A.SALARY
  FROM EMPLOYEE A
 WHERE (A.JOB_CODE, A.SALARY) IN (SELECT B.JOB_CODE
                                       , TRUNC(AVG(B.SALARY), -4)
                                    FROM EMPLOYEE B
                                   GROUP BY B.JOB_CODE
                                 );
 
-- 퇴사한 여직원과 같은 부서, 같은 직급에 해당하는
-- 사원의 이름, 직급, 부서, 입사일을 조회
SELECT * FROM EMPLOYEE;
 
-- 퇴사한 여직원의 부서와 직급
SELECT A.DEPT_CODE
     , A.JOB_CODE
  FROM EMPLOYEE A
 WHERE A.ENT_YN = 'Y'
   AND SUBSTR(A.EMP_NO, 8, 1) = '2';

SELECT
       A.EMP_NAME
     , A.JOB_CODE
     , A.DEPT_CODE
     , A.HIRE_DATE
  FROM EMPLOYEE A
 WHERE (A.DEPT_CODE, A.JOB_CODE) IN (SELECT B.DEPT_CODE
                                          , B.JOB_CODE
                                       FROM EMPLOYEE B
                                      WHERE B.ENT_YN = 'Y'
                                        AND SUBSTR(B.EMP_NO, 8, 1) = '2'
                                    )
--   AND A.ENT_YN = 'N';  -- 퇴사자를 제외하기 위한 짧은 처리 구문도 가능하다.
   AND A.EMP_ID NOT IN (SELECT C.EMP_ID
                          FROM EMPLOYEE C
                         WHERE C.ENT_YN = 'Y'
                           AND SUBSTR(C.EMP_NO, 8, 1) = '2'
                       );
  
-- 서브쿼리의 사용 위치
-- : SELECT 절, FROM 절, WHERE 절, GROUP BY 절, HAVING 절, ORDER BY 절
-- DML 구문: INSERT문, UPDATE문, DELETE문
-- DDL 구문: CREATE TABLE문, CREATE VIEW문

-- FROM절에서 서브쿼리를 사용할 수 있다: 테이블 대신에 사용
-- 인라인 뷰(INLINE VIEW)라고도 함
-- : 서브쿼리가 만든 결과 집합(RESULT SET)으로부터 시작을 의미

-- 직급별 급여 평균(만원단위)
 SELECT
        A.JOB_CODE
      , TRUNC(AVG(A.SALARY), -4)
   FROM EMPLOYEE A
  GROUP BY A.JOB_CODE;

-- 인라인 뷰에서 연산으로 처리된 컬럼은 메인 쿼리에서 별칭으로만 조회할 수 있다.
SELECT
       B.JOB_CODE
     , B.JOBAVG
  FROM ( SELECT A.JOB_CODE
              , TRUNC(AVG(A.SALARY), -4) AS JOBAVG
           FROM EMPLOYEE A
          GROUP BY A.JOB_CODE
       ) B;

-- 위와 같은 인라인 뷰에서 사원명, 급여, 직급명을 추가로 알고 싶을 때
SELECT
       B.JOB_CODE
     , B.JOBAVG
     , C.EMP_NAME
     , C.SALARY
     , D.JOB_NAME
  FROM ( SELECT A.JOB_CODE
              , TRUNC(AVG(A.SALARY), -4) AS JOBAVG
           FROM EMPLOYEE A
          GROUP BY A.JOB_CODE
       ) B
  JOIN EMPLOYEE C ON (B.JOB_CODE = C.JOB_CODE)
  JOIN JOB D ON (C.JOB_CODE = D.JOB_CODE);
  
-- 서브쿼리에서 쓰인 별칭은 메인쿼리에서 쓸 수 있다.
SELECT
       A.EMP_NAME 이름
     , B.DEPT_TITLE 부서명
     , C.JOB_NAME 직급명
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE);

-- 인라인 뷰의 서브쿼리의 컬럼에 별칭을 달면 메인 쿼리에서는 반드시 별칭을 달아줘야 한다.
-- 만약 별칭이 없다면 연산식이 아닌 컬럼일 경우는 메인쿼리에서 컬럼명으로 조회가 가능하다.
-- 단, 인라인 뷰의 서브쿼리에 연산식으로 도출된 컬럼이 있다면 그 때는 인라인 뷰에서 반드시
-- 별칭을 달고 메인 쿼리에서도 별칭으로만 조회할 수 있다.
SELECT
       D.이름
     , D.부서명
     , D.직급명
  FROM (SELECT A.EMP_NAME 이름
             , B.DEPT_TITLE 부서명
             , C.JOB_NAME 직급명
          FROM EMPLOYEE A
          JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
          JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE)
       ) D
 WHERE D.부서명 = '총무부';

-- ★★★ 인라인 뷰를 활용한 TOP-N분석 ★★★  
-- ROWNUM: 행 번호(순번)을 의미함
--         FROM의 결과 행(튜플)에 자동으로 순번이 달리게 된다.
--         ROWNUM을 활용한 조건절에서는 반드시 1순위부터 포함되게 범위를 지정해야 한다. ★★★  
SELECT
       ROWNUM
     , SALARY
  FROM EMPLOYEE
 ORDER BY SALARY DESC;
 
-- 급여를 많이 받는 사원을 10명 추출
SELECT
       ROWNUM
     , B.SALARY
  FROM (SELECT A.SALARY
          FROM EMPLOYEE A
         ORDER BY SALARY DESC
       ) B
 WHERE ROWNUM <= 10;    -- 1을 반드시 포함해야 하므로 주로 어떤 값보다 작다는 조건을 쓰게 된다.
 
-- ROWNUM이 1을 반드시 포함해야한다는 규칙을 지키지 않기 위한 2중 서브 쿼리문
SELECT
       C.순번
     , C.SALARY
  FROM (SELECT ROWNUM 순번
             , B.SALARY
         FROM (SELECT A.SALARY
                 FROM EMPLOYEE A
                ORDER BY SALARY DESC
              ) B
       )C
 WHERE C.순번 <= 10
   AND C.순번 >= 4;

-- 부서별 급여 평균 상위 3위 안에 드는 부서들의
-- 부서 코드와 부서명, 평균 급여를 조회

SELECT
       A.DEPT_CODE
     , B.DEPT_TITLE
     , AVG(A.SALARY)
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
 GROUP BY A.DEPT_CODE, B.DEPT_TITLE
 ORDER BY 3 DESC;

-- 메인 쿼리는 순위를 자르고, 서브쿼리는 결과와 정렬을 담당한다.
SELECT
       C.DEPT_CODE
     , C.DEPT_TITLE
     , C.급여평균
  FROM (SELECT A.DEPT_CODE
             , B.DEPT_TITLE
             , AVG(A.SALARY) 급여평균
          FROM EMPLOYEE A
          JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
         GROUP BY A.DEPT_CODE, B.DEPT_TITLE
         ORDER BY 3 DESC
       ) C
 WHERE ROWNUM <= 3;
  
-- 다른 방식(메인에서 JOIN)으로도 풀 수는 있다.
SELECT
       B.DEPT_CODE
     , C.DEPT_TITLE
     , B.급여평균
  FROM (SELECT A.DEPT_CODE
             , AVG(A.SALARY) 급여평균
          FROM EMPLOYEE A
         GROUP BY A.DEPT_CODE
         ORDER BY 2 DESC
       ) B
  JOIN DEPARTMENT C ON (B.DEPT_CODE = C.DEPT_ID)
 WHERE ROWNUM <= 3;

-- RANK() 함수
-- : 동일한 순위 이후의 등수를 동일한 인원수만큼 건너 뛰고 다음 순위를 계산하는 방식
-- DENSE_RANK() 함수
-- : 중복되는 순위 이후의 등수를 건너뛰지 않고 이후 등수로 처리
 
-- FROM절 이후로 바로 순위를 적용하는 것이 아닌 정렬 이후 순위를 적용함
SELECT
       A.EMP_NAME
     , A.SALARY
     , RANK() OVER(ORDER BY A.SALARY DESC) 순위
  FROM EMPLOYEE A;
  
SELECT
       A.EMP_NAME
     , A.SALARY
     , DENSE_RANK() OVER(ORDER BY A.SALARY DESC) 순위
  FROM EMPLOYEE A;

-- RANK() OVER를 사용해서도 TOP-N 분석을 할 수 있다.
SELECT
       B.EMP_NAME
     , B.SALARY
     , B.순위
  FROM (SELECT A.EMP_NAME
             , A.SALARY
             , RANK() OVER(ORDER BY A.SALARY DESC) 순위
          FROM EMPLOYEE A
       ) B
 WHERE 순위 <= 3;
 
-- WITH 이름 AS (서브쿼리문)
-- 서브쿼리에 이름을 붙여주고 메인쿼리에서 사용 시 이름을 사용하게 됨
-- 인라인 뷰로 사용 될 서브쿼리에서 이용 됨
-- 같은 서브쿼리가 여러 번 사용 될 경우 중복 작성을 줄일 수 있다.
-- 실행 속도도 빨라진다는 장점이 있고 가독성도 좋다.
 

  WITH
       TOPN_SAL
    AS (SELECT A.EMP_ID
             , A.EMP_NAME
             , A.SALARY
          FROM EMPLOYEE A
         ORDER BY A.SALARY DESC
       )
SELECT
       ROWNUM
     , B.EMP_NAME
     , B.SALARY
  FROM TOPN_SAL B
 WHERE ROWNUM <= 3;
 
-- 서브쿼리를 다양한 곳에서 써보자.

-- 부서별 급여 합계가 전체 급여의 총 합의 20%보다 많은
-- 부서의 부서명과 부서별 급여 합계 조회

-- 전체 급여의 총 합의 20%
SELECT
       SUM(SALARY) * 0.2
  FROM EMPLOYEE;

SELECT
       B.DEPT_TITLE
     , SUM(A.SALARY)
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
 GROUP BY B.DEPT_TITLE
HAVING SUM(A.SALARY) > (SELECT
                               SUM(SALARY) * 0.2
                          FROM EMPLOYEE
                       );
 
-- 인라인 뷰 방식으로도 풀어보자.
SELECT
       C.DT
     , C.SSAL
  FROM (SELECT B.DEPT_TITLE DT
             , SUM(A.SALARY) SSAL     -- 연산 결과 컬럼은 반드시 별칭 사용
          FROM EMPLOYEE A
          JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
         GROUP BY B.DEPT_TITLE
       ) C
 WHERE C.SSAL > (SELECT SUM(SALARY) * 0.2
                   FROM EMPLOYEE
                );
 
-- WITH AS로 서브쿼리 여러 개 저장하기
  WITH
       TOTAL_SAL
    AS (SELECT SUM(A.SALARY)
          FROM EMPLOYEE A
       )
     , AVG_SAL
    AS (SELECT AVG(B.SALARY)
          FROM EMPLOYEE B
       )
SELECT
       C.*
  FROM TOTAL_SAL C
UNION
SELECT
       D.*
  FROM AVG_SAL D;
 
-- 상[호연]관 서브쿼리
-- 일반적으로는 서브쿼리가 만든 결과 값을 메인 쿼리가 비교 연산
-- 메인쿼리가 사용하는 테이블의 값을 서브쿼리가 이용해서 결과를 만듦
-- 메인쿼리의 테이블 값이 변경되면, 서브쿼리의 결과값도 바뀌게 됨
 
-- 관리자 사번이 EMPLOYEE 테이블에 EMP_ID로 존재하는 직원에 대한 조회
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.DEPT_CODE
     , A.MANAGER_ID
  FROM EMPLOYEE A
 WHERE EXISTS (SELECT B.EMP_ID
                 FROM EMPLOYEE B
                WHERE A.MANAGER_ID = B.EMP_ID
              );
 
SELECT * FROM EMPLOYEE;
 
-- 스칼라 서브쿼리
-- 상관 서브쿼리 + 단일행 서브쿼리
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.MANAGER_ID
     , NVL((SELECT B.EMP_NAME
              FROM EMPLOYEE B
             WHERE A.MANAGER_ID = B.EMP_ID
           ), '없음')
  FROM EMPLOYEE A;
 
 
 
 
 
 
 
 
 
 
 
 
 










 