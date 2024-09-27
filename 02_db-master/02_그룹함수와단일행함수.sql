-- 그룹 함수와 단일행 함수
-- 함수(FUNCTION): 컬럼 값을 읽어서 계산한 결과를 리턴함

-- 그룹함수(1행 이상, RESULT SET의 결과는 1행): SUM, AVG, MAX, MIN, COUNT

-- SUM(숫자가 기록 된 컬럼명): 합계를 구하여 리턴
-- 사원들의 급여의 총합을 조회하시오.
SELECT
       SUM(SALARY)
  FROM EMPLOYEE;
  
-- AVG(숫자가 기록 된 컬럼명): 평균을 구하여 리턴
-- 사원들의 급여의 평균을 조회하시오.
SELECT
       AVG(SALARY)
  FROM EMPLOYEE;
  
-- MIN(컬럼명): 컬럼에서 가장 작은 값 리턴
SELECT
       MIN(EMAIL)
     , MIN(HIRE_DATE)
     , MIN(SALARY)
  FROM EMPLOYEE;
  
-- MAX(컬럼명): 컬럼에서 가장 큰 값 리턴
SELECT
       MAX(EMAIL)
     , MAX(HIRE_DATE)
     , MAX(SALARY)
  FROM EMPLOYEE;

-- AVG(BONUS): 보너스를 받는사람들만 고려해 평균을 낸 것
-- AVG(DISTINCT BONUS): 같은 보너스를 받는사람을 한명으로 취급해서 평균을 낸 것
-- AVG(NVL(BONUS, 0)): 모든 사람들을 고려해 보너스에 대한 평균을 낸 것
SELECT
       AVG(BONUS) 기본평균
     , AVG(DISTINCT BONUS) 중복제거평균
     , AVG(NVL(BONUS, 0)) "NULL값 포함 평균"
  FROM EMPLOYEE;
  
-- COUNT(*|컬럼명): 행의 갯수를 헤아려서 리턴
-- COUNT(*): NULL을 포함한 전체 행 갯수 리턴
-- COUNT(컬럼명): 해당 컬럼의 컬럼 값이 NULL이 아닌 행의 갯수 리턴
SELECT
       COUNT(*)                     -- 테이블의 모든 행의 갯수
     , COUNT(DEPT_CODE)             -- 부서를 배치 받은 사원들의 행의 갯수
     , COUNT(DISTINCT DEPT_CODE)    -- 사원들이 일하는 부서들의 갯수
  FROM EMPLOYEE;
  
-- 사원들이 일하고 있는 부서의 종류(없는 부서도 부서로 취급(NULL))
--SELECT
--       DISTINCT DEPT_CODE
--  FROM EMPLOYEE;

-- 단일행 함수
-- 문자 관련 함수
-- LENGTH, LENGTHB, SUBSTR, UPPER, LOWER, INSTR, ...
SELECT
       LENGTH('오라클')
     , LENGTHB('오라클')
  FROM DUAL;

SELECT
       LENGTH(EMP_NAME)
  FROM EMPLOYEE;
  
-- INSTR('문자열' | 컬럼명, '문자', 찾을 위치의 시작값, [빈도])
SELECT
       EMAIL
     , INSTR(EMAIL, '@')        -- 결과가 1부터 시작하는 숫자체계로 위치를 나타냄
  FROM EMPLOYEE;
  
SELECT INSTR('AABAACAABBAA', 'B') FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', 4) FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', -3) FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', 1, 2) FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', -1, 2) FROM DUAL;

-- SUBSTR
-- 이메일에서 @이후를 제외한 아이디만 조회
SELECT
       EMAIL
     , SUBSTR(EMAIL, 1, INSTR(EMAIL, '@') - 1)
     , SUBSTR(EMAIL, INSTR(EMAIL, '@') + 1, 5)  -- SUBSTR(컬러명, 시작위치, 길이)
  FROM EMPLOYEE;

-- LTRIM / RTRIM: 주어진 컬럼이나 문자열 왼쪽 / 오른쪽에서
--                지정한 문자 혹은 문자열을 제거한 나머지를
--                반환하는 함수이다.
SELECT LTRIM('      THE') FROM DUAL;
SELECT LTRIM('      THE', ' ') FROM DUAL;
SELECT LTRIM('000123056', '0') FROM DUAL;
SELECT LTRIM('123321THE', '123') FROM DUAL;
SELECT LTRIM('123321THE123', '123') FROM DUAL;
SELECT LTRIM('ACABACCTHE', 'ABC') FROM DUAL;   

SELECT RTRIM('THE      ') FROM DUAL;
SELECT RTRIM('THE      ', ' ') FROM DUAL;
SELECT RTRIM('123056000', '0') FROM DUAL;
SELECT RTRIM('THE123321', '123') FROM DUAL;
SELECT RTRIM('123THE123321', '123') FROM DUAL;
SELECT RTRIM('THEACABACC', 'ABC') FROM DUAL;

-- TRIM: 주어진 컬럼이나 문자열의 앞/뒤에 지정한 문자를 제거
SELECT TRIM('   THE   ') FROM DUAL;
SELECT TRIM('Z' FROM 'ZZZ123456ZZZ') FROM DUAL;
SELECT TRIM(LEADING 'Z' FROM 'ZZZTHEZZZ') FROM DUAL;
SELECT TRIM(TRAILING '3' FROM '333THE333') FROM DUAL;
SELECT TRIM(BOTH '3' FROM '333THE333') FROM DUAL;

-- LPAD/RPAD: 주어진 컬럼 문자열에 임의의 문자열을 덧붙여 길이
--            N의 문자열을 반환하는 함수
SELECT
       LPAD(EMAIL, 20, '#')
  FROM EMPLOYEE;

SELECT
       RPAD(EMAIL, 20, '#')
  FROM EMPLOYEE;

-- LOWER/UPPER/INITCAP: 대소문자 변경해 주는 함수
-- LOWER(문자열|컬럼): 소문자로 변경해 주는 함수
SELECT
       LOWER('Welcome to My World')
  FROM DUAL;

-- UPPER(문자열|컬럼): 대문자로 변경해 주는 함수
SELECT
       UPPER('Welcome to My World')
  FROM DUAL;

-- INITCAP(문자열|컬럼): 앞글자만 대문자로 변경해 주는 함수
SELECT
       INITCAP('welcome to my world')
  FROM DUAL;
  
-- CONCAT: 문자열 혹은 컬럼 두 개를 입력 받아 하나로 합친 후 리턴
SELECT
       CONCAT('가나다라', 'ABCD')
  FROM DUAL;

SELECT
       '가나다라' || 'ABCD'
  FROM DUAL;
  
-- REPLACE: 컬럼 혹은 문자열을 입력받아
--          변경하고자 하는 문자열을
--          변경하려고 하는 문자열로 바꾼 후 처리
SELECT
       REPLACE('서울시 강남구', '강남구', '서초구')
  FROM DUAL;

SELECT
       REPLACE(EMAIL, 'greedy', 'naver')
  FROM EMPLOYEE;

-- 함수들 응용하기

-- EMPLOYEE 테이블에서 직원들의 주민번호를 조회하여
-- 사원명, 생년, 생월, 생일을 각각 분리하여 조회하세요.
-- 단, 컬럼의 별칭은 사원명, 생년, 생월, 생일로 한다.

SELECT
       EMP_NAME 사원명
     , EMP_NO 주민번호
     , SUBSTR(EMP_NO, 1, 2) 생년
     , SUBSTR(EMP_NO, 3, 2) 생월
     , SUBSTR(EMP_NO, 5, 2) 생일
  FROM EMPLOYEE;
  
-- WHERE 절에는 단일행 함수만 사용 가능하다.
-- 사원 중에 사원들의 급여 평균 이상으로 받는 사원의 이름과 급여를 조회
SELECT
       EMP_NAME
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY >= AVG(SALARY);   -- WHERE절이 도는 횟수때문에 그룹함수는 사용할 수 없다

-- 여직원들을 조회해 보자
SELECT
       EMP_ID
     , EMP_NAME
     , EMP_NO
  FROM EMPLOYEE
 WHERE SUBSTR(EMP_NO, 8, 1) = '2';
 
-- EMPLOYEE 테이블에서 직원들의 입사일로부터
-- 입사년도, 입사월, 입사날짜를 분리해서 조회
-- (SUBSTR의 경우는 DATE형(날짜형)에 대해서도 사용할 수 있다.)
SELECT
       HIRE_DATE
     , SUBSTR(HIRE_DATE, 1, 2) 입사년도
     , SUBSTR(HIRE_DATE, 4, 2) 입사월
     , SUBSTR(HIRE_DATE, 7, 2) 입사날짜
  FROM EMPLOYEE;

-- EMPLOYEE 테이블에서 사원명, 주민번호 조회
-- (단, 주민번호는 생년월일만 보이게 하고, '-'다음의 값은 '*'로 바꿔서 출력하시오.)
SELECT
       EMP_NAME
     , EMP_NO
     , SUBSTR(EMP_NO, 1, 7) || '*******'
     , CONCAT(SUBSTR(EMP_NO, 1, 7), '*******')
     , RPAD(SUBSTR(EMP_NO, 1, 7), 14, '*')
  FROM EMPLOYEE;
--------------------------------------------------------------------------------
-- 숫자 처리 함수: ABS, MOD, ROUND, FLOOR, TRUNC, CEIL
-- ABS(숫자|숫자로 된 컬럼명): 절대값 구하는 함수
SELECT
       ABS(-10)
     , ABS(10)
  FROM DUAL;
  
-- MOD(숫자|숫자로 된 컬럼명, 숫자|숫자로 된 컬럼명)
-- : 두 수를 나누어서 나머지를 구하는 함수
--   처음 인자는 나누어지는 수, 두 번째 인자는 나눌 수
SELECT
       MOD(10, 5)
     , MOD(10, 3)
  FROM DUAL;
  
-- ROUND(숫자|숫자로 된 컬럼명, [위치])
-- : 반올림 해서 리턴하는 함수
SELECT ROUND(123.456) FROM DUAL;
SELECT ROUND(123.456, 0) FROM DUAL;
SELECT ROUND(123.456, 1) FROM DUAL;
SELECT ROUND(123.456, 2) FROM DUAL;
SELECT ROUND(123.456, -1) FROM DUAL;

-- FLOOR(숫자|숫자로 된 컬럼명): 내림처리 하는 함수
SELECT FLOOR(123.456) FROM DUAL;
SELECT FLOOR(126.678) FROM DUAL;
--SELECT FLOOR(123.678, 1) FROM DUAL;

-- TRUNC(숫자|숫자로 된 컬럼명, [위치]): 내림처리(해당 위치부터 절삭) 하는 함수
SELECT TRUNC(123.456) FROM DUAL;
SELECT TRUNC(123.456, 1) FROM DUAL;
SELECT TRUNC(123.456, 2) FROM DUAL;
SELECT TRUNC(123.456, -1) FROM DUAL;

-- CEIL(숫자|숫자로 된 컬럼명): 올림처리 함수
SELECT CEIL(123) FROM DUAL;    
SELECT CEIL(123.456) FROM DUAL;
SELECT CEIL(123.678) FROM DUAL;
------------------------------------------------------------------------------------
-- 날짜 처리 함수: SYSDATE, MONTHS_BETWEEN, ADD_MONTH, NEXT_DAY, LAST_DAY, EXTRACT
SELECT SYSDATE FROM DUAL;

-- MONTHS_BETWEEN(날짜(큰, 최신), 날짜(작, 예전))
-- : 두 날짜의 개월 수 차이를 숫자로 리턴하는 함수

-- 입사일로부터 현재까지 몇 개월간 근무했는지(꽉 채워서 일한 달 기준)
SELECT
       EMP_NAME
     , HIRE_DATE
     , FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))
  FROM EMPLOYEE;

-- ADD_MONTHS(날짜, 숫자)
-- : 날짜에 숫자만큼의 개월 수를 더해서 리턴(달 별로 몇 일까지인지를 파악해서 개월 수를 더해준다.)
SELECT
       ADD_MONTHS(SYSDATE, 5)
  FROM DUAL;

-- EMPLOYEE 테이블에서 사원의 이름, 입사일, 입사 후 6개월이 되는 날짜를 조회
SELECT
       EMP_NAME
     , HIRE_DATE
     , ADD_MONTHS(HIRE_DATE, 6)
  FROM EMPLOYEE;
  
-- EMPLOYEE 테이블에서 근무 년수가 20년 이상인 직원의 사원의 이름, 입사일, 근무년수 조회
SELECT
       EMP_NAME
     , HIRE_DATE
     , FLOOR(FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))/12)
  FROM EMPLOYEE
-- WHERE FLOOR(FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))/12) >= 20;
 WHERE ADD_MONTHS(HIRE_DATE, 240) <= SYSDATE;

-- NEXT_DAY(기준 날짜, 요일(문자|숫자))
SELECT SYSDATE, NEXT_DAY(SYSDATE, '목요일') FROM DUAL;
SELECT SYSDATE, NEXT_DAY(SYSDATE, 5) FROM DUAL;
SELECT SYSDATE, NEXT_DAY(SYSDATE, '목') FROM DUAL;
SELECT SYSDATE, NEXT_DAY(SYSDATE, 'THURSDAY') FROM DUAL;

-- ORACLE 언어 설정 변경
ALTER SESSION SET NLS_LANGUAGE = AMERICAN;  -- 영어
ALTER SESSION SET NLS_LANGUAGE = KOREAN;    -- 한국어

-- LAST_DAY(날짜): 해당 날짜의 월에서 마지막 날짜를 구하여 리턴
SELECT
       SYSDATE
     , LAST_DAY(SYSDATE)
     , LAST_DAY('1987/02/02')
  FROM DUAL;

-- EMPLOYEE 테이블에서 사원명, 입사일, 입사한 달의 근무일수를 조회
SELECT
       EMP_NAME
     , HIRE_DATE
     , LAST_DAY(HIRE_DATE) - HIRE_DATE + 1 "입사한 달의 근무일수 "
  FROM EMPLOYEE;
  
-- EXTRACT: DATE형에서 년, 월, 일 정보를 추출하여 리턴하는 함수
-- EXTRACT(YEAR FROM 날짜): 년도만 추출
-- EXTRACT(MONTH FROM 날짜): 월만 추출
-- EXTRACT(DAY FORM 날짜): 일만 추출
SELECT
       EXTRACT(YEAR FROM SYSDATE)
     , EXTRACT(MONTH FROM SYSDATE)
     , EXTRACT(DAY FROM SYSDATE)
  FROM DUAL;
  
SELECT
       EMP_NAME
     , EXTRACT(YEAR FROM HIRE_DATE) || '년' 입사년도
     , EXTRACT(MONTH FROM HIRE_DATE) || '월' 입사월
     , EXTRACT(DAY FROM HIRE_DATE) || '일' 입사일
  FROM EMPLOYEE;

--SELECT * FROM EMPLOYEE;
-----------------------------------------------------------------------------------
-- 형변환 함수
-- TO_CHAR(날짜, [포맷]): 날짜형 데이터를 문자형 데이터로 변경
-- TO_CHAR(숫자, [포맷]): 숫자형 데이터를 문자형 데이터로 변경

SELECT TO_CHAR(1234) FROM DUAL;
SELECT TO_CHAR(1234, '99999') FROM DUAL;
SELECT TO_CHAR(1234, '00000') FROM DUAL;
SELECT TO_CHAR(1234, 'L99999') FROM DUAL;
SELECT TO_CHAR(1234, '$99999') FROM DUAL;
SELECT TO_CHAR(1234, 'L999,999,999') FROM DUAL;
SELECT TO_CHAR(1234, 'L000,000,000') FROM DUAL;
SELECT TO_CHAR(1234, '9.9EEEE') FROM DUAL;
SELECT TO_CHAR(1234, '999') FROM DUAL;       -- 자릿수가 부족해서 #으로 처리됨

-- 직원 테이블에서 사원명, 급여 조회
-- (단, 급여는 '￦9,000,000' 형식으로 표시하시오)
SELECT
       EMP_NAME
     , TO_CHAR(SALARY, 'L999,999,999')
  FROM EMPLOYEE;
  
-- 날짜 데이터 포맷 적용 시에도 TO_CHAR 함수를 사용할 수 있다.
SELECT TO_CHAR(SYSDATE, 'PM HH24:MI;SS') FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'AM HH:MI;SS') FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'MON DY, YYYY') FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'YYYY-fmMM-DD DAY') FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'YYYY-MM-2DD DAY') FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'YEAR, Q') || '분기' FROM DUAL;

SELECT
       EMP_NAME
     , HIRE_DATE
     , TO_CHAR(HIRE_DATE, 'YYYY-MM-DD') 입사일
     , TO_CHAR(HIRE_DATE, 'YYYY-MM-DD HH24:MI:SS') 상세입사일
  FROM EMPLOYEE;

-- 오늘 날짜에 대해 4자리 년도, 2자리 년도 이름으로 조회
SELECT
       TO_CHAR(SYSDATE, 'YYYY')
     , TO_CHAR(SYSDATE, 'RRRR')
     , TO_CHAR(SYSDATE, 'YY')
     , TO_CHAR(SYSDATE, 'RR')
     , TO_CHAR(SYSDATE, 'YEAR')
  FROM DUAL;

-- RR과 YY의 차이
-- RR은 두자리로 인식한 년도를 네자리로 바꿀 때
-- 바꿀 년도가 50년 미만(00 ~ 49)이면 2000년대를 적용하고
-- 50년 이상(50 ~ 99)이면 1900년대를 적용한다.
SELECT
       TO_CHAR(TO_DATE('980630', 'YYMMDD'), 'YYYY-MM-DD')   -- CHAR -> DATE -> CHAR
  FROM DUAL;        -- 2098년으로 인식
  
SELECT
       TO_CHAR(TO_DATE('980630', 'RRMMDD'), 'YYYY-MM-DD')   -- CHAR -> DATE -> CHAR
  FROM DUAL;        -- 1998년으로 인식

-- EMPLOYEE 테이블에서 이름, 입사일 조회
-- (단, 입사일에 포맷을 적용함.
--  '2018년 6월 10일 (수)' 형식으로 처리 해보자)
SELECT
       EMP_NAME
     , TO_CHAR(HIRE_DATE, 'RRRR"년" fmMM"월" DD"일" (DY)')
  FROM EMPLOYEE;
  
-- TO_DATE: 문자형 데이터를 날짜형 데이터로 변환하여 리턴
SELECT
       TO_DATE('20100101', 'RRRRMMDD')
  FROM DUAL;

-- 약간의 응용
SELECT
       '123' + '10'                                     -- 비추천
     , TO_NUMBER('123') + TO_NUMBER('10')               -- 추천
  FROM DUAL;
  
-- 사원 테이블에서 2000년도 이후에 입사한 사원의
-- 사번, 이름, 입사일을 조회
SELECT
       EMP_ID
     , EMP_NAME
     , HIRE_DATE
  FROM EMPLOYEE
-- WHERE HIRE_DATE >= TO_DATE('20000101', 'RRRRMMDD');  -- 추천
-- WHERE EXTRACT(YEAR FROM HIRE_DATE) >= 2000;          -- 추천
 WHERE HIRE_DATE >= '20000101';                         -- 자동 형변환이 들어갔으므로 비추천
 
------------------------------------------------------------------------------------------
-- 선택함수
-- 여러 가지 경우에 선택할 수 있는 기능을 제공한다.
-- DECODE(계산식|컬럼명, 조건값1, 선택값1, 조건값2, 선택값2...)
SELECT
       EMP_ID
     , EMP_NAME
     , EMP_NO
     , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남', '2', '여') 성별
  FROM EMPLOYEE;
  
-- 마지막 인자로 조건값 없이 선택값만 작성하면
-- 어떤 조건 값에도 해당하지 않는 경우 마지막 선택값으로 결정된다.
-- (일종의 else, default 같은 개념)
SELECT
       EMP_ID
     , EMP_NAME
     , EMP_NO
     , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남', '여') 성별
  FROM EMPLOYEE;  

-- 직원의 급여를 인상하고자 한다.
-- 직급 코드가 J7인 직원은 급여의 10%를 인상하고
-- 직급 코드가 J6인 직원은 급여의 15%를 인상하고
-- 직급 코드가 J5인 직원은 급여의 20%를 인상한다.
-- 그 외 직급의 직원은 5%만 인상한다.
-- 직원 테이블에서 직원명, 직급코드, 급여, 인상급여(위 조건을 따른 급여)를 조회
SELECT
       EMP_NAME
     , JOB_CODE
     , SALARY
     , DECODE(JOB_CODE, 'J7', SALARY * 1.1
                      , 'J6', SALARY * 1.15
                      , 'J5', SALARY * 1.2
                            , SALARY * 1.05) 인상급여
  FROM EMPLOYEE;

-- CASE
--  WHEN 조건식1 THEN 결과값1
--  WHEN 조건식2 THEN 결과값2
--  ELSE 결과값3
-- END
SELECT
       EMP_NAME
     , JOB_CODE
     , SALARY
     , CASE
        WHEN JOB_CODE = 'J7' THEN SALARY * 1.1
        WHEN JOB_CODE = 'J6' THEN SALARY * 1.15
        WHEN JOB_CODE = 'J5' THEN SALARY * 1.2
        ELSE SALARY * 1.05
       END 인상급여
  FROM EMPLOYEE;
  
-- 사번, 사원명, 급여를 EMPLOYEE 테이블에서 조회하고
-- 급여가 500만원 초과이면 '고급'
-- 급여가 300만원 초과 ~ 500만원 이하이면 '중급'
-- 그 이하는 '초급'으로 출력 처리하고 별칭은 '구분'으로 한다.
SELECT
       EMP_ID
     , EMP_NAME
     , SALARY
     , CASE
        WHEN SALARY > 5000000 THEN '고급'
        WHEN SALARY > 3000000 AND SALARY <= 5000000 THEN '중급'
        ELSE '초급'
       END
  FROM EMPLOYEE;

-----------------------------------------------------------------------------------------   
-- 함수 연습 문제
--1. 직원명과 주민번호를 조회함
--  단, 주민번호 9번째 자리부터 끝까지는 '*'문자로 채움
--  예 : 홍길동 771120-1******
SELECT
       EMP_NAME
     , SUBSTR(EMP_NO, 1, 8) || '******'
  FROM EMPLOYEE;

--2. 직원명, 직급코드, 연봉(원) 조회
--  단, 연봉은 ￦57,000,000 으로 표시되게 함
--     연봉은 보너스포인트가 적용된 1년치 급여임
SELECT
       EMP_NAME 직원명
     , JOB_CODE 직급코드
     , TO_CHAR(SALARY * (1 + NVL(BONUS, 0)) * 12, 'L999,999,999,999') AS "연봉(원)"
  FROM EMPLOYEE;

--3. 부서코드가 D5, D9인 직원들 중에서 2004년도에 입사한 직원의 
--	사번 사원명 부서코드 입사일 조회
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , HIRE_DATE
  FROM EMPLOYEE
 WHERE DEPT_CODE IN ('D5','D9')
--   AND SUBSTR(HIRE_DATE, 1, 2) = '04';
   AND EXTRACT(YEAR FROM HIRE_DATE) = 2004;

-- HIRE_DATE를 EXTRACT 해 본 결과에 대한 테스트
SELECT
       EMP_NAME
     , EXTRACT(YEAR FROM HIRE_DATE)
  FROM EMPLOYEE;


--4. 직원명, 입사일, 입사한 달의 근무일수 조회
--  단, 주말도 포함함
SELECT
       EMP_NAME
     , HIRE_DATE
     , LAST_DAY(HIRE_DATE)
     , LAST_DAY(HIRE_DATE) - HIRE_DATE + 1
  FROM EMPLOYEE;


--5. 직원명, 부서코드, 생년월일, 나이(만) 조회
--  단, 생년월일은 주민번호에서 추출해서, 
--     ㅇㅇ년 ㅇㅇ월 ㅇㅇ일로 출력되게 함.
--  나이는 주민번호에서 추출해서 날짜데이터로 변환한 다음, 계산함
SELECT
       EMP_NAME
     , DEPT_CODE
     , SUBSTR(EMP_NO, 1, 2) || '년' || SUBSTR(EMP_NO, 3, 2) || '월' || SUBSTR(EMP_NO, 5, 2) || '일' "생년월일"
--     , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO, 1, 6), 'RRMMDD')) "나이"
     , FLOOR(FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(EMP_NO, 1, 6), 'RRMMDD'))) / 12)
  FROM EMPLOYEE;


--6.  부서코드가 D5이면 총무부, D6이면 기획부, D9이면 영업부로 처리하시오.
--   단, 부서코드가 D5, D6, D9 인 직원의 정보만 조회함
--  => case 사용
SELECT
       EMP_NAME
     , DEPT_CODE
     , CASE
        WHEN DEPT_CODE = 'D5' THEN '총무부'
        WHEN DEPT_CODE = 'D6' THEN '기획부'
        WHEN DEPT_CODE = 'D9' THEN '영업부'
       END
  FROM EMPLOYEE
 WHERE DEPT_CODE IN ('D5', 'D6', 'D9');
 
-- 7. 직원들의 입사일로부터 년도만 가지고, 각 년도별 입사 인원수를 구하시오.
--    아래의 년도에 입사한 인원수를 조회하시오.
--    => TO_CHAR, DECODE, COUNT 사용
--    
--    -------------------------------------------------------------
--    전체 인원수  2001년   2002년   2003년   2004년
--    -------------------------------------------------------------
SELECT
       COUNT(*)
     , COUNT(DECODE(TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE)), '2001', 'A')) "2001년"
     , COUNT(DECODE(TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE)), '2002', 'A')) "2002년"
     , COUNT(DECODE(TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE)), '2003', 'A')) "2003년"
     , COUNT(DECODE(TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE)), '2004', 'A')) "2004년"
  FROM EMPLOYEE;

-- RESULT SET에 만들 새로운 컬럼
SELECT
       TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE))                        -- 입사 년도 추출한 컬럼
     , DECODE(TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE)), '2001', 'A')   -- 2001년에 해당하는 사원에만 리터럴 값 적용한 컬럼
  FROM EMPLOYEE;









