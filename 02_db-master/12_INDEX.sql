-- 인덱스(INDEX)
-- : SQL 명령문의 검색 처리 속도를 향상시키기 위해
--   컬럼에 대해서 생성하는 오라클 객체이다.
--   
--   하드 디스크의 어느 위치인지에 대한 정보를 가진 주소록
--   DATA - ROWID로 구성
--   
--   인덱스의 내부 구조는 B*트리 형식으로 구성되어 있고
--   인덱스를 생성하기 위해서는 시간이 필요하다.
--   또한 인덱스를 위한 추가 저장공간이 필요하기 때문에 반드시 좋은 것은 아니다.
--   => 인덱스가 생성된 컬럼에서 DML 작업이 빈번한 경우 처리 속도가 느려진다.
--   따라서 일반적으로 테이블 전체 로우 수의 15% 이하의 데이터를 조회할 때 인덱스를 활용한다.
   
-- 장점
-- 검색 속도가 빨라짐
-- 시스템에 걸리는 부하를 줄여서 시스템 전체의 성능을 향상시킴
 
-- 단점
-- 인덱스를 위한 추가 저장공간이 필요함
-- 인덱스를 생성하는데 시간이 걸림
-- 데이터의 변경작업(INSERT/UPDATE/DELETE)이 자주 일어나는 경우
-- REBUILD 작업을 주기적으로 해 줘야 되고, REBUILD를 자주 해 주지 않으면
-- 성능이 오히려 저하된다.
 
-- 인덱스를 관리하는 데이터 딕셔너리 뷰
SELECT
       A.*
  FROM USER_IND_COLUMNS A;
  
-- ROWID 구조: 오브젝트 번호, 상대 파일 번호, 블록 번호, 데이터 번호
SELECT
       ROWID
     , A.EMP_ID
     , A.EMP_NAME
  FROM EMPLOYEE A;
  
-- 인덱스 종류
-- 1. 고유 인덱스(UNIQUE INDEX)
-- 2. 비고유 인덱스(NONUNIQUE INDEX)
-- 3. 단일 인덱스(SINGLE INDEX)
-- 4. 결합 인덱스(COMPOSITE INDEX)
-- 5. 함수기반 인덱스(FUNCTION BASED INDEX)
 
-- UNIQUE INDEX
-- UNIQUE INDEX로 생성 된 컬럼에는 중복 값이 포함될 수 없음
-- 오라클 PRIMARY KEY 제약조건을 생성하면
-- 자동으로 해당 컬럼에 UNIQUE INDEX가 생성 됨
-- PRIMARY KEY를 이용하여 ACCESS한 경우에는 성능 향상의 효과가 있음(카디널리티가 높은 컬럼으로 검색하기 때문)
 
-- 인덱스 리빌드(INDEX REBUILD)
ALTER INDEX 엔터티1_PK REBUILD;

-- 인덱스 힌트
-- 일반적으로는 옵티마이저가 적절한 인덱스를 타거나 풀 스캐닝을 해서 비용이 적게 드는 효율적인 방식으로 검색함
-- 하지만 우리는 원하는 테이블에 있는 인덱스를 사용할 수 있도록 해주는 구문(힌트)를 통해 선택 가능
-- SELECT절 첫 줄에 힌트 주석(/*+ 내용 */)을 작성하여 적절한 인덱스를 부여할 수 있다.
-- 주석에 '+'를 반드시 붙이고 /*+ 다음에 스페이스를 반드시 줘야 함
 
-- 옵티마이저: SQL을 위한 최적의 실행계획을 생성하는 알고리즘이다.
--ALTER INDEX 엔터티1_PK RENAME TO ENTITY1_PK;  -- 인덱스 이름을 바꾸기 위한 구문

-- 인덱스가 내림차순으로 생성되어서 INDEX_DESC를 해야 역방향으로 예전에 넣은 데이터부터 순서대로 나오도록 정렬 된다.
SELECT /*+ INDEX_DESC(EMPLOYEE 엔터티1_PK) */
--SELECT /*+ INDEX(A@SEL$1 엔터티1_PK) */
       A.*
  FROM EMPLOYEE A;
  
SELECT /*+ INDEX(A@SEL$1 엔터티1_PK) */
       A.*
  FROM EMPLOYEE A
 WHERE A.EMP_ID > '0'; -- WHERE 절에 고유 인덱스가 달린 컬럼으로 조회
 
-- 비고유이면서 단일 인덱스 생성
CREATE INDEX IDX_EMPNO
ON EMPLOYEE(EMP_NAME);

-- 중복값이 있는 컬럼에는 고유 인덱스를 생성할 수 없다.
CREATE UNIQUE INDEX IDX_DEPTCODE
ON EMPLOYEE(DEPT_CODE);

-- EMPLOYEE 테이블의 EMP_NO에 달린 인덱스는 딕셔너리 뷰로 확인하고 지워보자.
SELECT
       A.*
  FROM USER_IND_COLUMNS A;
 
-- 인덱스 모니터링
-- 만들어진 인덱스가 조회 시 사용 되었는지 확인
CREATE TABLE EMP001
AS
SELECT *
  FROM EMPLOYEE;  -- PK나 UNIQUE 제약조건이 없는 테이블 만들기(자동으로 인덱스가 설정되어 있지 않음)
  
-- EMP001 테이블의 EMP_ID컬럼에 대해 UNIQUE INDEX 만들기: IDX_EID
CREATE UNIQUE INDEX IDX_EID
    ON EMP001(EMP_ID);
  
SELECT
       *
  FROM USER_INDEXES
 WHERE TABLE_NAME = 'EMP001';
 
-- 모니터링 할 인덱스 설정
ALTER INDEX IDX_EID MONITORING USAGE;
 
-- 인덱스 모니터링
SELECT INDEX_NAME, TABLE_NAME, MONITORING, USED, START_MONITORING, END_MONITORING
  FROM V$OBJECT_USAGE;

-- V$OBJECT_USAGE: 인덱스 활용과 관련된 데이터를 수집하는 뷰
-- USED컬럼: 모니터링 시작 후 해당 인덱스가 사용 되었는지 확인

-- (1)인덱스 활용 X 전체 조회
SELECT
       *
  FROM EMP001;

SELECT INDEX_NAME, TABLE_NAME, MONITORING, USED, START_MONITORING, END_MONITORING
  FROM V$OBJECT_USAGE;
 
-- (2)인덱스 활용 O 전체 조회
SELECT /*+ INDEX_DESC(A IDX_EID) */
       A.*
  FROM EMP001 A;
 
SELECT INDEX_NAME, TABLE_NAME, MONITORING, USED, START_MONITORING, END_MONITORING
  FROM V$OBJECT_USAGE;
 
-- 인덱스 모니터링 종료
ALTER INDEX IDX_EID NOMONITORING USAGE;

-- 결합 인덱스 선언 방법
CREATE UNIQUE INDEX IDX_EID2
    ON EMP001(EMAIL, EMP_NAME); -- 결합 인덱스는 카디널리티가 상대적으로 높은 컬럼을 먼저 써야 한다.
 





 