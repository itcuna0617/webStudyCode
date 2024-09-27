-- 조인(JOIN)
-- JOIN: 한 개 이상의 테이블을 하나로 합쳐서 하나의 결과(RESULT SET)으로 조회하기 위해 사용된다.
 
-- 오라클 전용
-- FROM절에 ','로 구분하여 합치게 될 테이블명을 기술하고
-- WHERE절에 합치기에 사용 할 컬럼명을 명시한다.
 
-- 연결에 사용 할 두 컬럼의 컬럼명이 다른 경우
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , DEPT_TITLE
     , LOCATION_ID
  FROM EMPLOYEE
     , DEPARTMENT
 WHERE DEPT_CODE = DEPT_ID;  -- 두 테이블의 행이 MAPPING할 조건
 
SELECT * FROM EMPLOYEE;
SELECT * FROM DEPARTMENT;

-- 연결에 사용할 두 컬럼명이 같은 경우
SELECT
       EMP_ID
     , EMP_NAME
     , EMPLOYEE.JOB_CODE
     , JOB_NAME
  FROM EMPLOYEE
     , JOB
 WHERE EMPLOYEE.JOB_CODE = JOB.JOB_CODE;

SELECT * FROM EMPLOYEE;
SELECT * FROM JOB;

-- 테이블명에 대해 별칭 사용
-- 동일한 컬럼명에 대해 별칭은 당연히 달아야 되지만 나머지 컬럼에 대해서도 별칭으로 구분하는 습관을 들이자.
SELECT
       E.EMP_ID
     , E.EMP_NAME
     , E.JOB_CODE
     , J.JOB_NAME
  FROM EMPLOYEE E
     , JOB J
 WHERE E.JOB_CODE = J.JOB_CODE;

-- 별칭은 조인의 순서를 달 수 있도록 알파벳순으로 달아주자.
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.JOB_CODE
     , B.JOB_NAME
  FROM EMPLOYEE A
     , JOB B
 WHERE A.JOB_CODE = B.JOB_CODE;
 
-- ANSI 표준
-- 컬럼명에 대한 조건(매핑을 위한 조건)을 ON()을 사용할 수 있다.
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.JOB_CODE
     , B.JOB_NAME
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE);

-- 컬럼명이 같은 경우 USING()을 사용할 수 있음(단, 권장하지 않음, 별칭을 지워야 하기 때문)
SELECT
       EMP_ID
     , EMP_NAME
     , JOB_CODE
     , JOB_NAME
  FROM EMPLOYEE 
  JOIN JOB USING (JOB_CODE); 
  
-- 부서 테이블과 지역 테이블을 조인하여 테이블에 있는 모든 데이터를 조회해 보자.
SELECT * FROM DEPARTMENT;
SELECT * FROM LOCATION;

-- ANSI 표준
SELECT
       *
  FROM DEPARTMENT
  JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE);

-- 오라클 표준
SELECT
       *
  FROM DEPARTMENT, LOCATION
 WHERE LOCATION_ID = LOCAL_CODE;

-- 조인은 기본이 EQUAL JOIN이다.(EQU JOIN이라고도 함)
-- 연결되는 컬럼의 값이 일치하는 행들만 조인됨
 
-- 일치하는 값이 없는 행은 조인에서 제외되는 것을 INNER JOIN이라고 한다.
 
-- JOIN의 기본은 INNER JOIN & EQU JOIN이다.

-- EMPLOYEE 테이블과 DEPARTMENT 테이블을 조인하고 결과 행의 갯수를 살펴보자.
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID); -- 매핑 조건을 만족하지 못하는 행은 조회가 되지 않는다.
  
-- OUTER JOIN: 두 테이블의 지정하는 컬럼 값이 일치하지 않는 행도
--             조인에 포함을 시킴
--             반드시 OUTER JOIN임을 명시해야 한다.
             
-- 1. LEFT OUTER JOIN: 합치기에 사용한 두 테이블 중 왼편에 기술 된 테이블의 행의 수를 기준으로 JOIN
-- 2. RIGHT OUTER JOIN: 합치기에 사용한 두 테이블 중 오른편에 기술 된 테이블의 행의 수를 기준으로 JOIN
-- 3. FULL OUTER JOIN: 합치기에 사용한 두 테이블이 가진 모든 행을 결과에 포함하여 JOIN
 
-- ANSI표준의 INNER JOIN
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
  
-- ANSI 표준의 LEFT OUTER JOIN
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A
--  LEFT OUTER JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);

-- 오라클 전용 LEFT OUTER JOIN
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A
     , DEPARTMENT B
 WHERE A.DEPT_CODE = B.DEPT_ID(+);
 
-- RIGHT OUTER JOIN
-- ANSI 표준
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A 
--  RIGHT OUTER JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
  RIGHT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
 
-- 오라클 전용
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A 
     , DEPARTMENT B
 WHERE A.DEPT_CODE(+) = B.DEPT_ID;
 
-- FULL OUTER JOIN
-- ANSI 표준
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A 
--  FULL OUTER JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
  FULL JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
 
-- 오라클 전용
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A 
     , DEPARTMENT B
 WHERE A.DEPT_CODE(+) = B.DEPT_ID(+); -- 에러 남
 
-- CROSS JOIN: 카테이션곱이라고도 한다.
--             조인되는 테이블들의 각 행들이 모두 매핑되게 데이터를 검색하는 방법이다.
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A 
 CROSS JOIN DEPARTMENT B;

-- 일반적으로는 CROSS JOIN은 의도한다기 보다 MAPPING 조건을 잘못 작성 했을 때 CROSS JOIN이 발생한다.
SELECT
       *
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON (1 = 1);
 
-- NON EQUAL JOIN(NON EQU JOIN)
SELECT * FROM EMPLOYEE;
SELECT * FROM SAL_GRADE;

-- ANSI 표준
SELECT
       A.EMP_NAME
     , A.SALARY
     , B.SAL_LEVEL
     , B.MIN_SAL
     , B.MAX_SAL
  FROM EMPLOYEE A
--  JOIN SAL_GRADE B ON (A.SALARY BETWEEN B.MIN_SAL AND B.MAX_SAL);
  JOIN SAL_GRADE B ON (A.SALARY >= B.MIN_SAL AND A.SALARY <= B.MAX_SAL);
  
-- 오라클 전용
SELECT
       A.EMP_NAME
     , A.SALARY
     , B.SAL_LEVEL
     , B.MIN_SAL
     , B.MAX_SAL
  FROM EMPLOYEE A
     , SAL_GRADE B
-- WHERE A.SALARY BETWEEN B.MIN_SAL AND B.MAX_SAL;
 WHERE A.SALARY >= B.MIN_SAL AND A.SALARY <= B.MAX_SAL;

-- SELF JOIN: 같은 테이블을 조인하는 경우
--            자기 자신 테이블과 조인을 맺는 것이다.
-- ANSI 표준
SELECT
       A.EMP_ID
     , A.EMP_NAME 사원이름
     , A.DEPT_CODE
     , A.MANAGER_ID
     , B.EMP_NAME 관리자이름
  FROM EMPLOYEE A
  LEFT JOIN EMPLOYEE B ON (A.MANAGER_ID = B.EMP_ID);

-- 오라클 전용
SELECT
       A.EMP_ID
     , A.EMP_NAME 사원이름
     , A.DEPT_CODE
     , A.MANAGER_ID
     , B.EMP_NAME 관리자이름
  FROM EMPLOYEE A
     , EMPLOYEE B 
 WHERE A.MANAGER_ID = B.EMP_ID(+);
 
-- 다중 JOIN: N개(3개 이상)의 테이블을 조회할 때 사용하는 JOIN
-- ANSI 표준
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.DEPT_CODE
     , B.DEPT_TITLE
     , C.LOCAL_NAME
     , C.NATIONAL_CODE
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  LEFT JOIN LOCATION C ON (B.LOCATION_ID = C.LOCAL_CODE)    -- 이전 조인에서 OUTER조인을 했다면 OUTER 조인을 계속 적용해야 원하는 결과를 얻을 수 있다.
 WHERE A.EMP_ID = '210';

-- 오라클 전용
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.DEPT_CODE
     , B.DEPT_TITLE
     , C.LOCAL_NAME
     , C.NATIONAL_CODE
  FROM EMPLOYEE A
     , DEPARTMENT B
     , LOCATION C
 WHERE A.DEPT_CODE = B.DEPT_ID(+)
   AND B.LOCATION_ID = C.LOCAL_CODE(+)
   AND A.EMP_ID = '210';

-- 직급이 대리이면서 아시아 지역에 근무하는 직원 조회
-- 사번(EMPLOYEE.EMP_ID), 이름(EMPLOYEE.EMP_NAME), 직급명(JOB.JOB_NAME), 부서명(DEPARTMENT.DEPT_TITLE),
-- 근무지역명(LOCATION.LOCAL_NAME), 급여(EMPLOYEE.SALARY)를 조회하시오.
-- (해당 컬럼을 찾고, 해당 컬럼을 지닌 테이블들을 찾고, 테이블들을 어떤 순서로 조인 해야하는지 고민하고 SQL문을 작성하자)
-- ANSI 표준
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.SALARY
     , B.JOB_NAME
     , C.DEPT_TITLE
     , D.LOCAL_NAME
  FROM EMPLOYEE A
  LEFT JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE)
  LEFT JOIN DEPARTMENT C ON (A.DEPT_CODE = C.DEPT_ID)
  LEFT JOIN LOCATION D ON (C.LOCATION_ID = D.LOCAL_CODE)
 WHERE B.JOB_NAME = '대리'
   AND D.LOCAL_NAME LIKE 'ASIA%';
 
-- 오라클 전용
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.SALARY
     , B.JOB_NAME
     , C.DEPT_TITLE
     , D.LOCAL_NAME
  FROM EMPLOYEE A
     , JOB B 
     , DEPARTMENT C
     , LOCATION D
 WHERE A.JOB_CODE = B.JOB_CODE(+)
   AND A.DEPT_CODE = C.DEPT_ID(+)
   AND C.LOCATION_ID = D.LOCAL_CODE(+)
   AND B.JOB_NAME = '대리'
   AND D.LOCAL_NAME LIKE 'ASIA%';

--------------------------------------------------------------------------------------
-- JOIN 연습문제

-- 1. 2020년 12월 25일이 무슨 요일인지 조회하시오.(조인 아님)
SELECT 
       TO_CHAR(TO_DATE('20201225', 'YYYYMMDD'), 'DAY')
  FROM DUAL;

-- 2. 주민번호가 70년대 생이면서 성별이 여자이고, 
--    성이 전씨인 직원들의 사원명, 주민번호, 부서명, 직급명을 조회하시오.
-- ANSI 표준
SELECT 
       A.EMP_NAME
     , A.EMP_NO
     , B.DEPT_TITLE
     , C.JOB_NAME
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON(A.DEPT_CODE = B.DEPT_ID)
  JOIN JOB C ON(A.JOB_CODE = C.JOB_CODE)
 WHERE SUBSTR(A.EMP_NO, 1,2) >= 70 
   AND SUBSTR(A.EMP_NO, 1,2) < 80
   AND SUBSTR(A.EMP_NO, 8,1) = 2
   AND A.EMP_NAME LIKE '전%';

-- 오라클 전용
SELECT 
       A.EMP_NAME
     , A.EMP_NO
     , B.DEPT_TITLE
     , C.JOB_NAME
  FROM EMPLOYEE A
     , DEPARTMENT B
     , JOB C
 WHERE A.DEPT_CODE = B.DEPT_ID
   AND C.JOB_CODE = A.JOB_CODE
   AND SUBSTR(A.EMP_NO, 1,2) >= 70 
   AND SUBSTR(A.EMP_NO, 1,2) < 80
   AND SUBSTR(A.EMP_NO, 8,1) = 2
   AND A.EMP_NAME LIKE '전%';

-- 3. 가장 나이가 적은 직원의 사번, 사원명, 
--    나이, 부서명, 직급명을 조회하시오.
-- ANSI 표준
SELECT 
       A.EMP_ID
     , A.EMP_NAME
     , FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(A.EMP_NO, 1, 2), 'RR')) / 12) + 1 나이
     , B.DEPT_TITLE
     , C.JOB_NAME
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON(A.DEPT_CODE = B.DEPT_ID)
  LEFT JOIN JOB C ON(A.JOB_CODE = C.JOB_CODE)
 WHERE FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(A.EMP_NO, 1, 2), 'RR')) / 12) + 1 = (SELECT MIN(FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(D.EMP_NO, 1, 2), 'RR')) / 12) + 1)
                                                                                          FROM EMPLOYEE D
                                                                                        );
              
-- ORACLE 전용
SELECT 
       A.EMP_ID
     , A.EMP_NAME
     , FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(A.EMP_NO, 1, 2), 'RR')) / 12) + 1 나이
     , B.DEPT_TITLE
     , C.JOB_NAME
  FROM EMPLOYEE A
     , DEPARTMENT B
     , JOB C
 WHERE A.DEPT_CODE = B.DEPT_ID(+)
   AND A.JOB_CODE = C.JOB_CODE(+)
   AND FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(A.EMP_NO, 1, 2), 'RR')) / 12) + 1 = (SELECT MIN(FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(D.EMP_NO, 1, 2), 'RR')) / 12) + 1)
                                                                                          FROM EMPLOYEE D
                                                                                        );

-- 4. 이름에 '형'자가 들어가는 직원들의
-- 사번, 사원명, 부서명을 조회하시오.
-- ANSI 표준
SELECT 
       A.EMP_ID
     , A.EMP_NAME
     , B.JOB_NAME
  FROM EMPLOYEE A
  JOIN JOB B ON(A.JOB_CODE = B.JOB_CODE)
 WHERE A.EMP_NAME LIKE '%형%';

-- 오라클 전용
SELECT 
       A.EMP_ID
     , A.EMP_NAME
     , B.JOB_NAME
  FROM EMPLOYEE A
     , JOB B
 WHERE A.JOB_CODE = B.JOB_CODE
   AND A.EMP_NAME LIKE '%형%';

-- 5. 해외영업팀에 근무하는 사원명, 
--    직급명, 부서코드, 부서명을 조회하시오.
-- ANSI표준
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , A.DEPT_CODE
     , C.DEPT_TITLE
  FROM EMPLOYEE A
  JOIN JOB B ON(A.JOB_CODE = B.JOB_CODE)
  JOIN DEPARTMENT C ON(A.DEPT_CODE = C.DEPT_ID)
 WHERE C.DEPT_ID IN('D5', 'D6');

-- 오라클 전용
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , A.DEPT_CODE
     , C.DEPT_TITLE
  FROM EMPLOYEE A
     , JOB B
     , DEPARTMENT C
WHERE A.DEPT_CODE = C.DEPT_ID
AND A.JOB_CODE = B.JOB_CODE
AND C.DEPT_ID IN('D5', 'D6');

-- 6. 보너스포인트를 받는 직원들의 사원명, 
--    보너스포인트, 부서명, 근무지역명을 조회하시오.
-- ANSI표준
SELECT 
       A.EMP_NAME
     , A.BONUS
     , B.DEPT_TITLE
     , C.LOCAL_NAME
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON(A.DEPT_CODE = B.DEPT_ID)
  JOIN LOCATION C ON(B.LOCATION_ID = C.LOCAL_CODE)
 WHERE A.BONUS IS NOT NULL;

-- 오라클 전용
SELECT 
       A.EMP_NAME
     , A.BONUS
     , B.DEPT_TITLE
     , C.LOCAL_NAME
  FROM EMPLOYEE A
     , DEPARTMENT B
     , LOCATION C
 WHERE A.DEPT_CODE = B.DEPT_ID
   AND B.LOCATION_ID = C.LOCAL_CODE
   AND A.BONUS IS NOT NULL;

-- 7. 부서코드가 D2인 직원들의 사원명, 
--    직급명, 부서명, 근무지역명을 조회하시오.
-- ANSI 표준
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , C.DEPT_TITLE
     , D.LOCAL_NAME
FROM EMPLOYEE A
JOIN JOB B ON(A.JOB_CODE = B.JOB_CODE)
JOIN DEPARTMENT C ON(A.DEPT_CODE = C.DEPT_ID)
JOIN LOCATION D ON(C.LOCATION_ID = D.LOCAL_CODE)
WHERE A.DEPT_CODE = 'D2';

-- 오라클 전용
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , C.DEPT_TITLE
     , D.LOCAL_NAME
  FROM EMPLOYEE A
     , JOB B
     , DEPARTMENT C
     , LOCATION D
 WHERE A.JOB_CODE = B.JOB_CODE
   AND A.DEPT_CODE = C.DEPT_ID
   AND C.LOCATION_ID = D.LOCAL_CODE
   AND A.DEPT_CODE = 'D2';

-- 8. 본인 급여 등급의 최소급여(MIN_SAL)를 초과하여 급여를 받는 직원들의
--    사원명, 직급명, 급여, 보너스포함 연봉을 조회하시오.
--    연봉에 보너스포인트를 적용하시오.
-- ANSI 표준
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , A.SALARY
     , A.SALARY * 12 + A.SALARY * NVL(A.BONUS, 0)
  FROM EMPLOYEE A
  JOIN JOB B ON(A.JOB_CODE = B.JOB_CODE)
  JOIN SAL_GRADE C ON(A.SAL_LEVEL = C.SAL_LEVEL)
 WHERE A.SALARY > C.MIN_SAL;

-- 오라클 전용
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , A.SALARY
     , A.SALARY * 12 + A.SALARY * NVL(A.BONUS, 0) 
  FROM EMPLOYEE A
     , JOB B
     , SAL_GRADE C
 WHERE A.JOB_CODE = B.JOB_CODE
   AND A.SAL_LEVEL = C.SAL_LEVEL
   AND A.SALARY > C.MIN_SAL;

-- 9. 한국(KO)과 일본(JP)에 근무하는 직원들의 
--    사원명, 부서명, 지역명, 국가명을 조회하시오.
-- ANSI 표준
SELECT 
       A.EMP_NAME
     , B.DEPT_TITLE
     , C.LOCAL_NAME
     , D.NATIONAL_NAME
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON(A.DEPT_CODE = B.DEPT_ID)
  JOIN LOCATION C ON(B.LOCATION_ID = C.LOCAL_CODE)
  JOIN NATIONAL D ON(C.NATIONAL_CODE = D.NATIONAL_CODE)
 WHERE D.NATIONAL_NAME IN('한국', '일본');

-- 오라클 전용
SELECT 
       A.EMP_NAME
     , B.DEPT_TITLE
     , C.LOCAL_NAME
     , D.NATIONAL_NAME
  FROM EMPLOYEE A
     , DEPARTMENT B
     , LOCATION C
     , NATIONAL D
 WHERE A.DEPT_CODE = B.DEPT_ID
   AND B.LOCATION_ID = C.LOCAL_CODE
   AND C.NATIONAL_CODE = D.NATIONAL_CODE
   AND D.NATIONAL_NAME IN('한국', '일본');

-- 10. 같은 부서에 근무하는 직원들의 사원명, 부서코드, 
--     동료이름을 조회하시오.self join 사용
-- ANSI 표준
SELECT 
       A.EMP_NAME 사원명
     , B.DEPT_CODE 부서코드
     , B.EMP_NAME 동료이름
  FROM EMPLOYEE A
  JOIN EMPLOYEE B ON(A.DEPT_CODE = B.DEPT_CODE)
 WHERE A.EMP_NAME != B.EMP_NAME
 ORDER BY 1;

-- 오라클 전용
SELECT 
       A.EMP_NAME
     , B.DEPT_CODE
     , B.EMP_NAME
  FROM EMPLOYEE A
     , EMPLOYEE B
 WHERE A.DEPT_CODE = B.DEPT_CODE
   AND A.EMP_NAME != B.EMP_NAME
 ORDER BY 1;

-- 11. 보너스포인트가 없는 직원들 중에서 직급코드가 
--     J4와 J7인 직원들의 사원명, 직급명, 급여를 조회하시오.
--     단, join과 IN 사용할 것
-- ANSI 표준
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , A.SALARY
  FROM EMPLOYEE A
  JOIN JOB B ON(A.JOB_CODE = B.JOB_CODE)
 WHERE NVL(A.BONUS, 0)= 0 
   AND B.JOB_CODE IN('J4', 'J7');

-- 오라클 전용
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , A.SALARY
  FROM EMPLOYEE A
     , JOB B
 WHERE A.JOB_CODE = B.JOB_CODE
   AND NVL(A.BONUS, 0)= 0 
   AND B.JOB_CODE IN('J4', 'J7');

--12. 재직중인 직원과 퇴사한 직원의 수를 조회하시오.(조인아님)
SELECT 
       COUNT(*)
  FROM EMPLOYEE 
 GROUP BY ENT_YN;






 