-- DML(Data Manipulation Language)
-- INSERT, UPDATE, DELETE, SELECT(DQL)
-- : ������ ���۾��, ���̺� ���� �����ϰų�, �����ϰų�,
--   �����ϰų�, ��ȸ�ϴ� ���
   
-- INSERT: ���ο� ���� �߰��ϴ� �����̴�.
--         ���̺��� �� ������ �����Ѵ�.
-- INSERT INTO ���̺��(�÷���, �÷���, ...) VALUES(������, ������, ...)

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
  900, '������', '901123-1080503'
, 'jang_ch@greedy.or.kr', '01055556912', 'D1'
, 'J1', 'S5', 2800000
, 0.1, '200', SYSDATE
, NULL, DEFAULT    -- DEFAULT�� �ش� �÷��� DEFAULT ������ ������ ���� INSERT �ȴ�.
                   -- INSERT �ÿ� �ش� �÷��� INSERT ���� ������ DEFAULT ���� �ش� �÷��� ����.(NULL���)
);

INSERT
  INTO EMPLOYEE
(
  EMP_ID, EMP_NAME, EMP_NO      -- ������ �÷��� �⺻������ NULL�� ���� DEFAULT ���� ������ �÷��� DEFAULT ���� ����.
, EMAIL, JOB_CODE, SAL_LEVEL
)
VALUES
(
  901, '�ο���', '901123-1080504'
, 'jang_ch2@greedy.or.kr', 'J1', 'S5'
);

SELECT
       A.*
  FROM EMPLOYEE A
 WHERE A.EMP_ID = '901';

ROLLBACK;
COMMIT;

-- INSERT �ÿ� VALUES ��� ���������� �̿��� �� �ִ�.
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

-- INSERT ALL: INSERT �ÿ� ����ϴ� ���������� ���� ���
--             �� �� �̻��� ���̺� INSERT ALL�� �̿��Ͽ�
--             �ѹ��� �����͸� ������ �� �ִ�.
--             ��, �� ���������� �������� ���ƾ� �Ѵ�.
DROP TABLE EMP_DEPT_D1;
CREATE TABLE EMP_DEPT_D1
AS
SELECT A.EMP_ID
     , A.EMP_NAME
     , A.DEPT_CODE
     , A.HIRE_DATE
  FROM EMPLOYEE A
 WHERE 1 = 0;   -- �����ʹ� ���� �ڷ����� NOT NULL �������Ǹ� �����ؼ� ����� ���̺�

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

-- INSERT ALL�� Ȱ���� �ϳ��� ������
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

-- EMPLOYEE ���̺��� �Ի��� �������� 2000�� 1�� 1�� ������ �Ի���
-- ����� ���, �̸�, �Ի���, �޿��� ��ȸ�Ͽ�
-- EMP_OLD ���̺� �����ϰ�
-- �� ���Ŀ� �Ի��� ����� EMP_NEW ���̺� �����Ͻÿ�.
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

-- UPDATE: ���̺� ��ϵ� �÷��� ���� �����ϴ� �����̴�.
--         ���̺��� ��ü �� ������ ��ȭ�� ����.
CREATE TABLE DEPT_COPY
AS
SELECT A.*
  FROM DEPARTMENT A;

SELECT * FROM DEPT_COPY;
    
-- UPDATE ���̺�� SET �÷��� = �ٲܰ�, �÷��� = �ٲܰ�, ...
-- [WHERE �÷��� �񱳿����� �񱳰�]
UPDATE
       DEPT_COPY A
   SET A.DEPT_TITLE = '������ȹ��'
 WHERE A.DEPT_ID = 'D9';
SELECT * FROM DEPT_COPY;
ROLLBACK;
COMMIT;

-- UPDATE �ÿ��� ���������� �̿��� �� �ִ�.
-- UPDATE ���̺��
-- SET �÷��� = (��������)
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
 WHERE A.EMP_NAME IN ('�����', '����');

-- ��� �ÿ� ����� ����� �η��� �ϴ� ���� �����
-- �޿��� ���ʽ����� ����� ����� �����ϰ� ������ �ֱ�� �ߴ�.
-- �̸� �ݿ��ϴ� UPDATE ���� �ۼ��� ���ÿ�.
UPDATE
       EMP_SALARY A
   SET A.SALARY = (SELECT B.SALARY
                     FROM EMP_SALARY B
                    WHERE B.EMP_NAME = '�����'
                  )
     , A.BONUS = (SELECT C.BONUS
                     FROM EMP_SALARY C
                    WHERE C.EMP_NAME = '�����'
                 )
 WHERE A.EMP_NAME = '����';
 
SELECT
       A.*
  FROM EMP_SALARY A
 WHERE A.EMP_NAME IN ('�����', '����');

-- ���߿� ���������� �̿��� ������Ʈ��
-- ���� ����� �޿� �λ� �ҽ��� ���ص��� �ٸ� ��������
-- ��ü�� �ľ��� �����ߴ�.
-- ���ö, ������, ������, �ϵ��� ����� �޿��� ���ʽ���
-- ����� ����� �޿� �� ���ʽ��� ���� �����ϴ� UPDATE���� �ۼ�
UPDATE
       EMP_SALARY A
   SET (A.SALARY, A.BONUS) = (SELECT B.SALARY
                                   , B.BONUS
                                FROM EMP_SALARY B
                               WHERE B.EMP_NAME = '�����'
                             )
 WHERE A.EMP_NAME IN ('���ö', '������', '������', '�ϵ���');

SELECT
       A.*
  FROM EMP_SALARY A
 WHERE EMP_NAME IN ('�����', '���ö', '������', '������', '�ϵ���');

-- ������ ���������� �̿��� UPDATE
-- EMP_SALARY ���̺��� �ƽþ� ������ �ٹ��ϴ� ������
-- ���ʽ��� 0.5�� �����Ͻÿ�.
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

-- UPDATE �� ������ ���� �ش� �÷��� ���� �������ǿ� ������� �ʵ��� �ؾ� ��
UPDATE
       EMPLOYEE A
   SET A.DEPT_CODE = '10'  -- FOREIGN KEY �������� ���� ��
 WHERE A.DEPT_CODE = 'D6';
ROLLBACK;

UPDATE
       EMPLOYEE A
   SET A.EMP_NAME = NULL  -- NOT NULL �������� ���� ��
 WHERE A.EMP_ID = '200';

UPDATE
       EMPLOYEE A
   SET A.EMP_NO = '631106-1548654'  -- UNIQUE �������� ���� ��
 WHERE A.EMP_ID = '202';

-- DELETE: ���̺��� ���� �����ϴ� �����̴�.
--         ���̺��� ���� ������ �پ���.
-- DELETE FROM ���̺�� [WHERE ������]
-- ���� WHERE ������ �������� ������ ��� ���� �� �����ȴ�.
SELECT * FROM EMPLOYEE WHERE EMP_NAME = '������';
DELETE FROM EMPLOYEE;
ROLLBACK;

DELETE
  FROM EMPLOYEE A
 WHERE A.EMP_NAME = '������'; -- WHERE���� ���� ������ ���� PK�÷����� ó������.
 
-- FOREIGN KEY ���������� �����Ǿ� �ִ� ���
-- �ڽ����̺� ���� �����ǰ� �ִ� ���� ���ؼ��� �θ����̺��� ������ �� ����.(�⺻�����δ�)
DELETE
  FROM DEPARTMENT A
 WHERE A.DEPT_ID = 'D1';
  
-- �������ǰ� ���õ� ��ųʸ� �並 ���� ���������� Ȯ������.
SELECT * FROM USER_CONSTRAINTS;
SELECT * FROM USER_CONS_COLUMNS;

-- ���ϴ� ���̺��� �������ǰ� ���õ� �ʼ����� �÷��� �����ؼ� ������ SELECT�� �ڡ�
SELECT
       A.TABLE_NAME
     , A.CONSTRAINT_NAME
     , A.SEARCH_CONDITION
     , A.CONSTRAINT_TYPE
     , B.COLUMN_NAME
  FROM USER_CONSTRAINTS A
  JOIN USER_CONS_COLUMNS B ON (A.CONSTRAINT_NAME = B.CONSTRAINT_NAME)
 WHERE A.TABLE_NAME = 'EMPLOYEE';

-- �������� ��� ����
ALTER TABLE EMPLOYEE
DISABLE CONSTRAINT SYS_C007689; 

DELETE
  FROM DEPARTMENT A
 WHERE A.DEPT_ID = 'D1';

ROLLBACK;

-- �������� �ٽ� �ѱ�
ALTER TABLE EMPLOYEE
ENABLE CONSTRAINT SYS_C007689;

-- TRUNCATE: ���̺��� ��ü ���� ������ �� ����Ѵ�.
--           DELETE���� ���� �ӵ��� �� ������.
--           ROLLBACK�� ���� ������ �� ����.
SELECT * FROM EMP_SALARY;

TRUNCATE TABLE EMP_SALARY; -- ���̺� �ʱ�ȭ
ROLLBACK;

-- MERGE: ������ ���� �� ���� ���̺��� �ϳ��� ���̺��� �������� ��ġ��(����) ����� �Ѵ�.
--        ���̺��� �����ϴ� ������ ���� �����ϸ� UPDATE, ������ ���� ������ INSERT �ȴ�.
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
  999, '����', '000101-3123456', 'dlagon77@hanmail.net', '01092880100'
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












