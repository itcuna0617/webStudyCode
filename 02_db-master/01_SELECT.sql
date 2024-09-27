-- SELECT ��� ���� �� ������

-- ��� �� ��� �÷� ��ȸ
-- EMPLOYEE  ���̺��� ��� ���� ��ȸ
SELECT 
       *            -- 7ĭ ��� 
  FROM EMPLOYEE;    -- 2ĭ ���

-- SYSTEM �������� ������ �۾��� �� �־�� CREATE�� �����ϴ�.
CREATE TABLE CHARTEST(
   CH1 CHAR(6),
   CH2 CHAR(9),
   CH3 CHAR(3),
   CH4 CHAR(3)
);

INSERT
  INTO CHARTEST
VALUES
(
  '��ġ'
, '��ġ'
, '��'
, ''
);

SELECT
       *
  FROM CHARTEST;

-- ���ڰ� ��������� ������, ���� �� ����Ʈ�� �ν�
SELECT
       LENGTH(CH1)
     , LENGTH(CH2)      -- 2���� + ���� 3����Ʈ -> 5
     , LENGTH(CH3)
     , LENGTH(CH4)
  FROM CHARTEST;

-- ���ڰ� ����ִ� �÷��� ����Ʈ ��
SELECT
       LENGTHB(CH1)
     , LENGTHB(CH2)      
     , LENGTHB(CH3)
     , LENGTHB(CH4)
  FROM CHARTEST;
  
-- VARCHAR2 ���� ���ڿ� ���� �׽�Ʈ
CREATE TABLE CHARTEST2(
   CH1 VARCHAR2(6),
   CH2 VARCHAR2(9),
   CH3 VARCHAR2(3),
   CH4 VARCHAR2(3)
);

INSERT
  INTO CHARTEST2
VALUES
(
  '��ġ'
, '��'
, 'KIM'
, '��'
);

-- ���ڰ� ��������� ������, ���� �� ����Ʈ�� �ν�
SELECT
       LENGTH(CH1)
     , LENGTH(CH2)      -- 1���ڸ�ŭ�� ���̸� ������
     , LENGTH(CH3)
     , LENGTH(CH4)
  FROM CHARTEST2;

-- ���ڰ� ����ִ� �÷��� ����Ʈ ��
SELECT
       LENGTHB(CH1)
     , LENGTHB(CH2)      -- 3����Ʈ�� �پ��
     , LENGTHB(CH3)
     , LENGTHB(CH4)
  FROM CHARTEST2;


-- NVARCHAR2 ���� ���ڿ� ���� �׽�Ʈ
CREATE TABLE CHARTEST3(
   CH1 NVARCHAR2(6),
   CH2 NVARCHAR2(9),
   CH3 NVARCHAR2(3),
   CH4 NVARCHAR2(3)
);

SELECT
       *
  FROM CHARTEST3;
  
DESC CHARTEST3;

INSERT
  INTO CHARTEST3
VALUES
(
  '��'
, '��'
, 'KIM'
, '��'
);

SELECT
       LENGTH(CH1)
     , LENGTH(CH2)
     , LENGTH(CH3)
     , LENGTH(CH4)
  FROM CHARTEST3;
  
SELECT
       LENGTHB(CH1)
     , LENGTHB(CH2)
     , LENGTHB(CH3)
     , LENGTHB(CH4)
  FROM CHARTEST3;

DELETE
  FROM CHARTEST3;
  
-- �������� SELECT ���� ����
SELECT
       *
  FROM EMPLOYEE;
  
-- ������� ���(�����ȣ), �̸� ��ȸ
SELECT
       EMP_ID
     , EMP_NAME
  FROM EMPLOYEE;
  
-- ���ϴ� ��(Ʃ��)��ȸ
-- EMPLOYEE ���̺��� �μ� �ڵ尡 D9�� ��� ��ȸ
SELECT
       *
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D9';
 
-- EMPLOYEE ���̺��� �����ڵ尡 J1�� ��� ��ȸ
SELECT
       *
  FROM EMPLOYEE
 WHERE JOB_CODE = 'J1';
 
-- ���ϴ� ��� �÷� ��ȸ
-- EMPLOYEE ���̺��� �޿��� 300���� �̻��� �����
-- ���, �̸�, �μ��ڵ�, �޿��� ��ȸ�ϼ���
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY >= 3000000;
 
-- �÷��� ��Ī ����(AS "��Ī��")
SELECT
       EMP_NAME �̸�                                               -- Ư����ȣ �� ���� ������ ""�� ���� ����
     , SALARY * 12 "1�� �޿�(��)"                                   -- AS ���� ����
     , (SALARY + (SALARY * BONUS)) * 12 AS "�� �ҵ�(��)"            -- BONUS�� NULL�� ����� ��� ����� ������ NULL�� ��
     , (SALARY + (SALARY * NVL(BONUS, 0))) * 12 AS "�� �ҵ�(��) "   -- NVL�� �Ἥ NULL�� 0���� ó���ؼ� ��� �ǰ� ��
  FROM EMPLOYEE;

-- NVL �Լ��� ���� NULL�� ó��
SELECT
       EMP_ID
     , NVL(BONUS, 0) AS "NVL ó��"
  FROM EMPLOYEE;
  
-- DISTNCT Ű���� (SELECT ������ DISTINCT Ű����� �� �ѹ��� �� �� �ִ�.)
-- �ش� �÷��� �ߺ��� ����
SELECT
       DISTINCT JOB_CODE
--     , DISTINCT DEPT_CODE
  FROM EMPLOYEE;
  
-- WHERE��
-- ���̺��� ������ �����ϴ� ���� ���� ��(Ʃ��)�� ���
-- ���� ���� ������ �����ϴ� ���� ��� �� ���� AND Ȥ�� OR�� ����� �� �ִ�.

-- �μ��ڵ尡 D6�̰� �޿��� 200�������� ���� �޴� ������
-- �̸�, �μ��ڵ�, �޿� ��ȸ
SELECT
       EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D6' 
   AND SALARY > 2000000;
 
-- ���ʽ��� ���� �ʴ� ����� ����
-- ���, ������, �޿�, ���ʽ��� ��ȸ
SELECT
       EMP_ID
     , EMP_NAME
     , SALARY
     , BONUS
  FROM EMPLOYEE
-- WHERE BONUS IS NOT NULL;   -- ���ʽ��� �޴� ���
 WHERE BONUS IS NULL;         -- ���ʽ��� ���� �ʴ� ���
 
-- ���Ῥ����(||)�� �̿��Ͽ� ���� �÷��� �ϳ��� �÷��� ��ó��
-- ������ �� �ִ�.
SELECT
       EMP_ID || EMP_NAME || SALARY "�ѹ濡 ���� �÷�"
  FROM EMPLOYEE;
  
SELECT
       EMP_NAME || '�� �÷���' || SALARY || '�� �Դϴ�.'
  FROM EMPLOYEE;
  
-- �� ������
-- = ����, > ũ��, < �۳�, >= ũ�ų� ����, <= �۰ų� ���� (������ �������� ����)
-- !=, ^=, <> ���� �ʳ�

-- D9�μ����� �ٹ����� �ʴ� �����
-- ���, �̸�, �μ��ڵ带 ��ȸ
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
  FROM EMPLOYEE
-- WHERE DEPT_CODE != 'D9';
-- WHERE DEPT_CODE ^= 'D9';
 WHERE DEPT_CODE <> 'D9';

-- Ǯ���
-- EMPLOYEE ���̺��� ��� ���ΰ� N�� ������ ��ȸ�ϰ�
-- ���, �̸�, �Ի����� ��Ī�� ����� ��ȸ�� ����
-- (ENT_YN�̰� N�� ��� ���� ���, Y�� ��� �� ���)
SELECT
       EMP_ID ���
     , EMP_NAME �̸�
     , HIRE_DATE �Ի���
  FROM EMPLOYEE
 WHERE ENT_YN = 'N';

-- EMPLOYEE ���̺��� �޿��� 350���� �̻�
-- 550���� ���ϸ� �޴�
-- ������ ���, �̸�, �޿�, �μ��ڵ�, �����ڵ带 ��ȸ�ϼ���
SELECT
       EMP_ID
     , EMP_NAME
     , SALARY
     , DEPT_CODE
     , JOB_CODE
  FROM EMPLOYEE
 WHERE SALARY >= 3500000
   AND SALARY <= 5500000;
   
-- BETWEEN AND ���
-- �÷��� BETWEEN ���Ѱ� AND ���Ѱ�
-- : ���Ѱ� �̻�, ���Ѱ� ������ ��
SELECT
       EMP_ID
     , EMP_NAME
     , SALARY
     , DEPT_CODE
     , JOB_CODE
  FROM EMPLOYEE
 WHERE SALARY BETWEEN 3500000 AND 5000000;

-- �ݴ�� 350���� �̸�, �Ǵ� 550������ �ʰ��ϴ�
-- ������ ��ȸ�Ͻÿ�
SELECT
       *
  FROM EMPLOYEE
-- WHERE SALARY < 3500000
--    OR SALARY > 5500000;
-- WHERE NOT SALARY BETWEEN 3500000 AND 5500000;
 WHERE SALARY NOT BETWEEN 3500000 AND 5500000;      -- NOT�� �÷��� ���̵� �ڵ� ��� ����.

-- LIKE ������: ���� ������ ��ġ�ϴ� ���� ��ȸ�� �� ���
-- �÷��� LIKE '��������'
-- ��������: '����%' (���ڷ� �����ϴ� ��),
--          '%����%' (���ڰ� ���Ե� ��),
--          '%����' (���ڷ� ������ ��)

-- EMPLOYEE ���̺��� ���� �达�� ������
-- ���, �̸�, �Ի��� ��ȸ
SELECT
       EMP_ID
     , EMP_NAME
     , HIRE_DATE
  FROM EMPLOYEE
 WHERE EMP_NAME LIKE '��%';
 
-- EMPLOYEE ���̺��� ���� �达�� ������
-- ������ ������ ���, �̸�, �Ի��� ��ȸ
SELECT
       EMP_ID
     , EMP_NAME
     , HIRE_DATE
  FROM EMPLOYEE
-- WHERE NOT EMP_NAME LIKE '��%';
 WHERE EMP_NAME NOT LIKE '��%';
 
-- EMPLOYEE ���̺��� '��'�� �̸��� ���� ��
-- ������ �̸�, �ֹι�ȣ, �μ��ڵ� ��ȸ
-- (��, �μ��� ���ٸ� '�μ�����'���� ��ȸ�Ͻÿ�)
SELECT
       EMP_NAME
     , EMP_NO
     , NVL(DEPT_CODE, '�μ�����')
  FROM EMPLOYEE
 WHERE EMP_NAME LIKE '%��%';
 
-- ���ϵ�ī��: _(���� ���ڸ�), %(0�� �̻��� ����)

-- EMPLOYEE ���̺��� ��ȭ��ȣ ������ 3�ڸ��̸鼭 9�� �����ϴ�
-- ������ ���, �̸�, ��ȭ��ȣ�� ��ȸ�ϼ���
SELECT
       EMP_ID
     , EMP_NAME
     , PHONE
  FROM EMPLOYEE
 WHERE PHONE LIKE '___9%';
 
-- ������ 3�ڸ� �̸鼭 9�� �����ϸ� 7���� ��ȣ�� �ִ� ��ȭ��ȣ�� ��ȸ
SELECT
       EMP_ID
     , EMP_NAME
     , PHONE
  FROM EMPLOYEE
 WHERE PHONE LIKE '___9_______';
 
-- EMPLOYEE ���̺��� _�ձ��ڰ� 3�ڸ��� �̸��� �ּҸ� ����
-- ����� ���, �̸�, �̸����ּ� ��ȸ
SELECT
       EMP_ID
     , EMP_NAME
     , EMAIL
  FROM EMPLOYEE
 WHERE EMAIL LIKE '___#_%' ESCAPE '#';
 
-- �̾� ���� �ƴ� ������ ���, �̸�, �̸��� �ּ� ��ȸ
SELECT
       EMP_ID
     , EMP_NAME
     , EMAIL
  FROM EMPLOYEE
-- WHERE EMP_NAME NOT LIKE '��%';
 WHERE NOT EMP_NAME LIKE '��%';
 
-- IN ������: ���Ϸ��� �� ��Ͽ� ��ġ�ϴ� ���� �ִ��� Ȯ��
-- D6�μ� �̰ų� D8�μ� �̰ų� D9�μ��� �������� �̸�, �μ��ڵ�, �޿��� ��ȸ�� ����.
SELECT
       EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
-- WHERE DEPT_CODE = 'D6'
--    OR DEPT_CODE = 'D8'
--    OR DEPT_CODE = 'D9';
 WHERE DEPT_CODE IN ('D6', 'D8', 'D9');

-- D6�μ��� D8�μ��� �ƴ� ��� ��ȸ
SELECT
       EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
-- WHERE DEPT_CODE NOT IN ('D6', 'D8');
 WHERE NOT DEPT_CODE IN ('D6', 'D8');

-- ������ �켱����
/*
 1. ���������
 2. ���Ῥ����(||)
 3. �񱳿�����
 4. IS NULL/IS NOT NULL, LIKE/NOT LIKE, IN/NOT IN
 5. BETWEEN AND/NOT BETWEEN AND
 6. NOT
 7. AND
 8. OR
*/
-- J2�����̸鼭 �޿� 200���� �̻�޴� �����̰ų�
-- J7������ ������ �̸�, �޿�, �����ڵ� ��ȸ
SELECT
       EMP_NAME
     , SALARY
     , JOB_CODE
  FROM EMPLOYEE
 WHERE JOB_CODE = 'J2'
   AND SALARY >= 2000000
    OR JOB_CODE = 'J7';
    
-- J2�����̰ų� J7������ ������ �߿�
-- �޿��� 200���� �̻��� ������
-- �̸�, �޿�, �����ڵ带 ��ȸ
SELECT
       EMP_NAME
     , SALARY
     , JOB_CODE
  FROM EMPLOYEE
-- WHERE (JOB_CODE = 'J2'
--    OR JOB_CODE = 'J7')
--   AND SALARY >= 2000000;
 WHERE JOB_CODE IN ('J2', 'J7')
   AND SALARY >= 2000000;




 
 







