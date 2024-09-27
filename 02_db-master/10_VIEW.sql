-- VIEW(��)
-- SELECT �������� ������ ��ü�� �������̺��̶�� �Ҹ���.
-- �������� �����͸� ���������� �����ϰ� ���� ����
-- ���̺��� ����ϴ� �Ͱ� �����ϰ� ����� �� �ִ�.
-- CREATE [OR REPLACE] VIEW ���̸� AS ��������
 
-- EMPLOYEE ���̺�κ��� ���, �̸�, �����ڵ�, �μ��ڵ带 ��ȸ�ϰ�
-- �� ����� V_RESULT_EMP��� �並 �����ؼ� ��������.
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
  
-- ���̽� ���̺��� ������ ����Ǹ� VIEW�� ����� ���� ����ȴ�.
SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE '��%';
UPDATE
       EMPLOYEE
   SET EMP_NAME = '���߾�';

SELECT
       *
  FROM V_RESULT_EMP
 WHERE EMP_ID = '205';
ROLLBACK;

-- ������ ��ųʸ� ��(Data Dictionary View)
-- �ڿ��� ȿ�������� �����ϱ� ���� �پ��� ������ �����ϴ� �ý��� ���̺�(������ ��ųʸ�)
-- ����ڰ� ���̺��� �����ϰų�, ����ڸ� �����ϴ� ���� �۾��� �� ��
-- �����ͺ��̽� ������ ���� �ڵ����� ���ŵǴ� ���̺�(������ ��ųʸ�)
 
-- ���� ���̺��� Ŀ���͸���¡ �ؼ� �����ִ� ���̽� ���̺�(������ ��ųʸ�)�� ���� ���̺�(������ ��ųʸ� ��)
 
-- ��ųʸ� ���� 3���� ����
-- 1. DBA_XXX: �����ͺ��̽� �����ڸ� ������ ������ ��ü���� ���� ��ȸ
-- 2. ALL_XXX: �ڽ��� ���� + ������ �ο����� ��ü�� ���� ��ȸ
-- 3. USER_XXX: �ڽ��� ������ ������ ��ü � ���� ���� ��ȸ

-- ������ ���� �� Ȯ��  
SELECT
       *
  FROM USER_VIEWS;

-- ������ ���� ���̺� Ȯ��
SELECT
       *
  FROM USER_TABLES;
  
-- ������ ���� ���̺�� �÷��� Ȯ��
SELECT
       *
  FROM USER_TAB_COLUMNS
 WHERE TABLE_NAME = 'EMPLOYEE';

CREATE OR REPLACE VIEW V_RESULT_EMP
(
  ���
, �̸�
, ���޸�
, �μ���
, ������
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

-- �� �������� �ȿ� ���� ����� �÷��� ������ �� �ִ�.
CREATE OR REPLACE VIEW V_EMP_JOB
(
  ���
, �̸�
, ���޸�
, ����
, �ٹ����
)
AS
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.JOB_NAME
     , DECODE(SUBSTR(A.EMP_NO, 8, 1), '1', '��', '��')
     , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM A.HIRE_DATE)
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE);
  
SELECT * FROM V_EMP_JOB;

-- ��� INSERT ��
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
, '����'
);

SELECT * FROM JOB;

-- ��� UPDATE ��
UPDATE
       V_JOB A
   SET A.JOB_NAME = '�˹�'
 WHERE A.JOB_CODE = 'J8';
 
-- ��� DELETE ��
DELETE
  FROM V_JOB A
 WHERE A.JOB_CODE = 'J8';

-- DML ��ɾ�� ������ �Ұ����� ���
-- 1. �� ���ǿ� ���Ե��� ���� �÷��� �����ϴ� ���
-- 2. �信 ���Ե��� ���� �÷� �߿�,
--    ���̽��� �Ǵ� ���̺� �÷��� NOT NULL ���������� ������ ���
-- 3. ���ǥ�������� ���� �� ���
-- 4. JOIN�� �̿��� ���� ���̺��� ������ ���
-- 5. DISTINCT�� ������ ���
-- 6. �׷��Լ��� GROUP BY ���� ������ ���
 
-- �� ���ǿ� ���Ե��� ���� �÷��� �����ϴ� ���
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
, '����'
);
  
UPDATE
       V_JOB2 A
   SET A.JOB_NAME = '����'
 WHERE A.JOB_CODE = 'J7';
 
-- DELETE�� �ش��÷��� ���� ������ �÷��� ���� ��
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
 
-- ��� ǥ�������� ���� �� ���
CREATE OR REPLACE VIEW V_SAL
AS
SELECT A.EMP_ID
     , A.EMP_NAME
     , A.SALARY
     , (A.SALARY + (A.SALARY * NVL(A.BONUS, 0))) * 12 ����
  FROM EMPLOYEE A;

SELECT * FROM V_SAL;
 
INSERT
  INTO V_SAL
(
  EMP_ID
, EMP_NAME
, SALARY
, ����
)
VALUES
(
  '800'
, '������'
, 3000000
, 4000000
);          -- ������

UPDATE
       V_SAL A
   SET A.���� = 80000000
 WHERE A.EMP_ID = '200';  -- ������

DELETE
  FROM V_SAL A
 WHERE A.���� = 124800000; -- DELETE ���� ����

-- JOIN�� �̿��� ���� ���̺��� ������ ���
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
, '������'
, '�λ������'
);             -- ������

UPDATE
       V_JOINEMP A
   SET A.DEPT_TITLE = '�λ������'
 WHERE A.EMP_ID = '219';   -- ������

-- �ϳ��� ���̽� ���̺��� ������ �ָ� ���� ��
DELETE
  FROM V_JOINEMP A
 WHERE A.EMP_ID = '219';

SELECT * FROM EMPLOYEE;
ROLLBACK;

-- DISTINCT�� ������ ���
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
);    -- ������

UPDATE
       V_DT_EMP A
   SET A.JOB_CODE = 'J8'
 WHERE A.JOB_CODE = 'J7';  -- ������
 
DELETE
  FROM V_DT_EMP A
 WHERE A.JOB_CODE = 'J7';  -- ������
 
-- �׷� �Լ��� GROUP BY ���� ������ ���
CREATE OR REPLACE VIEW V_GROUP_DEPT
AS
SELECT
       DEPT_CODE
     , SUM(SALARY) �հ�
     , AVG(SALARY) ���
  FROM EMPLOYEE
 GROUP BY DEPT_CODE;

INSERT
  INTO V_GROUP_DEPT
(
  DEPT_CODE
, �հ�
, ���
)
VALUES
(
  'D0'
, 6000000
, 400000
);    -- ������

UPDATE
       V_GROUP_DEPT A
   SET A.DEPT_CODE = 'D10'
 WHERE A.DEPT_CODE = 'D1';  -- ������

DELETE
  FROM V_GROUP_DEPT A
 WHERE A.DEPT_CODE = 'D1';  -- ������

-- VIEW �ɼ�
-- OR REPLACE: ������ ������ �� �̸��� �����ϴ� ��� ������ �ɼ�
-- FORCE: ���������� ��� �� ���̺��� �������� �ʾƵ� �� ����
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

-- WITH CHECK OPTION: �������� ���� �÷��� ���� �������� ���ϰ� �Ѵ�.
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
 
-- WITH READ ONLY: DML ������ �Ұ���
CREATE OR REPLACE VIEW V_DEPT
AS
SELECT A.*
  FROM DEPARTMENT A
  WITH READ ONLY;
 
DELETE
  FROM V_DEPT;
  

 
 
 
 





