-- 시퀀스(SEQUENCE)
-- 자동 번호 발생기 역할을 하는 객체
-- 순차적으로 정수 값을 자동으로 생성해 줌
 
-- CREATE SEQUENCE 시퀀스명
-- [START WITH 숫자] - 처음 발생 시킬 시작 값 지정, 기본 값 1 
--                    (이 옵션은 수정(ALTER)이 안되므로 시작 값을 바꾸고 싶으면 SEQUENCE를 DROP 후 다시 만들자(CREATE) ★★)
-- [INCREMENT BY 숫자] - 다음 값에 대한 증가치, 기본 값 1
-- [MAXVALUE 숫자 | NOMAXVALUE] - 발생 시킬 최대 값 지정, 10의 27승 -1까지 가능
-- [MINVALUE 숫자 | NOMINVALUE] - 발생 시킬 최소 값 지정, -10의 26승
-- [CYCLE | NOCYCLE] - 시퀀스가 최대 값까지 증가를 완료하면 CYCLE은 START WITH 설정 값 혹은 MINVALUE 값으로
--                     돌아가고, NOCYCLE은 에러 발생
-- [CACHE | NOCACHE] - CACHE는 메모리 상에서 시퀀스 값을 관리하여 성능을 향상 시키기 위한 것, 기본 값 20
 
CREATE SEQUENCE SEQ_EMPID;

--SELECT SEQ_EMPID.CURRVAL FROM DUAL; -- 시퀀스 생성 후 처음부터 CURRVAL은 사용할 수 없다.
SELECT SEQ_EMPID.NEXTVAL FROM DUAL;   -- 시퀀스 생성 후에는 반드시 NEXTVAL을 한번 해 주어야 초기 값부터 시작 된다.
SELECT SEQ_EMPID.CURRVAL FROM DUAL;
SELECT SEQ_EMPID.NEXTVAL FROM DUAL;  
SELECT SEQ_EMPID.CURRVAL FROM DUAL;

SELECT SEQ_EMPID.NEXTVAL FROM DUAL;  
SELECT 'P'|| '-' || EXTRACT(MONTH FROM SYSDATE) || '-' || SEQ_EMPID.NEXTVAL FROM DUAL;  -- PK값에 활용할 때 연결 연산자로 원하는 형태를 만들자.

DROP SEQUENCE SEQ_EMPID;
CREATE SEQUENCE SEQ_EMPID
 START WITH 300
INCREMENT BY 5
MAXVALUE 310
MINVALUE 300
CYCLE
NOCACHE;

SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- 300
SELECT SEQ_EMPID.CURRVAL FROM DUAL; -- 300

SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- 305
SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- 310
SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- 300

-- 시퀀스의 옵션 변경(START WITH 옵션을 제외한 옵션들은 변경이 가능하다.)
ALTER SEQUENCE SEQ_EMPID
INCREMENT BY 10
MAXVALUE 400
NOCYCLE;

SELECT SEQ_EMPID.CURRVAL FROM DUAL;

-- 바뀐 옵션으로 계속 증가시키면 MAX값까지 증가 후 NOCYCLE이라 에러가 발생한다.
SELECT SEQ_EMPID.NEXTVAL FROM DUAL;

-- 참고사항
-- SELECT문에서 사용 가능
-- INSERT문에서 SELECT 구문 사용 가능(서브쿼리)
-- INSERT문에서 VALUES 절에서 사용 가능 -- (시퀀스 객체는 이 상황에서 주로 쓰게 된다.)
-- UPDATE문에서 SET절에서 사용 가능
 
-- 단, 일반적인 서브쿼리의 SELECT문에서는 사용 불가
-- VIEW의 SELECT절에서 사용 불가
-- DISTINCT 키워드가 있는 SELECT문에서 사용 불가
-- GROUP BY, HAVING절에 있는 SELECT문에서는 사용 불가
-- ORDER BY 절에서 사용 불가
-- CREATE TABLE, ALTER TABLE의 DEFAULT 값으로 사용 불가

-- VALUES를 활용한 INSERT문에서의 SEQUENCE 활용
SELECT 
       MAX(TO_NUMBER(A.EMP_ID))
  FROM EMPLOYEE A;

CREATE SEQUENCE SEQ_EID
 START WITH 902;   -- 마지막 PK값 + 1에 해당하는 값부터 출발하는 시퀀스를 만든다.

INSERT
  INTO EMPLOYEE
VALUES
(
  SEQ_EID.NEXTVAL, '홍길동', '555555-5555555', 'hong_gd@greedy.or.kr', '01012345555'
, 'D2', 'J7', 'S1', 50000000, 0.1
, '200', SYSDATE, NULL, DEFAULT
);







