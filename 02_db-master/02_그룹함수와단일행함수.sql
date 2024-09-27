-- �׷� �Լ��� ������ �Լ�
-- �Լ�(FUNCTION): �÷� ���� �о ����� ����� ������

-- �׷��Լ�(1�� �̻�, RESULT SET�� ����� 1��): SUM, AVG, MAX, MIN, COUNT

-- SUM(���ڰ� ��� �� �÷���): �հ踦 ���Ͽ� ����
-- ������� �޿��� ������ ��ȸ�Ͻÿ�.
SELECT
       SUM(SALARY)
  FROM EMPLOYEE;
  
-- AVG(���ڰ� ��� �� �÷���): ����� ���Ͽ� ����
-- ������� �޿��� ����� ��ȸ�Ͻÿ�.
SELECT
       AVG(SALARY)
  FROM EMPLOYEE;
  
-- MIN(�÷���): �÷����� ���� ���� �� ����
SELECT
       MIN(EMAIL)
     , MIN(HIRE_DATE)
     , MIN(SALARY)
  FROM EMPLOYEE;
  
-- MAX(�÷���): �÷����� ���� ū �� ����
SELECT
       MAX(EMAIL)
     , MAX(HIRE_DATE)
     , MAX(SALARY)
  FROM EMPLOYEE;

-- AVG(BONUS): ���ʽ��� �޴»���鸸 ����� ����� �� ��
-- AVG(DISTINCT BONUS): ���� ���ʽ��� �޴»���� �Ѹ����� ����ؼ� ����� �� ��
-- AVG(NVL(BONUS, 0)): ��� ������� ����� ���ʽ��� ���� ����� �� ��
SELECT
       AVG(BONUS) �⺻���
     , AVG(DISTINCT BONUS) �ߺ��������
     , AVG(NVL(BONUS, 0)) "NULL�� ���� ���"
  FROM EMPLOYEE;
  
-- COUNT(*|�÷���): ���� ������ ��Ʒ��� ����
-- COUNT(*): NULL�� ������ ��ü �� ���� ����
-- COUNT(�÷���): �ش� �÷��� �÷� ���� NULL�� �ƴ� ���� ���� ����
SELECT
       COUNT(*)                     -- ���̺��� ��� ���� ����
     , COUNT(DEPT_CODE)             -- �μ��� ��ġ ���� ������� ���� ����
     , COUNT(DISTINCT DEPT_CODE)    -- ������� ���ϴ� �μ����� ����
  FROM EMPLOYEE;
  
-- ������� ���ϰ� �ִ� �μ��� ����(���� �μ��� �μ��� ���(NULL))
--SELECT
--       DISTINCT DEPT_CODE
--  FROM EMPLOYEE;

-- ������ �Լ�
-- ���� ���� �Լ�
-- LENGTH, LENGTHB, SUBSTR, UPPER, LOWER, INSTR, ...
SELECT
       LENGTH('����Ŭ')
     , LENGTHB('����Ŭ')
  FROM DUAL;

SELECT
       LENGTH(EMP_NAME)
  FROM EMPLOYEE;
  
-- INSTR('���ڿ�' | �÷���, '����', ã�� ��ġ�� ���۰�, [��])
SELECT
       EMAIL
     , INSTR(EMAIL, '@')        -- ����� 1���� �����ϴ� ����ü��� ��ġ�� ��Ÿ��
  FROM EMPLOYEE;
  
SELECT INSTR('AABAACAABBAA', 'B') FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', 4) FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', -3) FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', 1, 2) FROM DUAL;
SELECT INSTR('AABAACAABBAA', 'B', -1, 2) FROM DUAL;

-- SUBSTR
-- �̸��Ͽ��� @���ĸ� ������ ���̵� ��ȸ
SELECT
       EMAIL
     , SUBSTR(EMAIL, 1, INSTR(EMAIL, '@') - 1)
     , SUBSTR(EMAIL, INSTR(EMAIL, '@') + 1, 5)  -- SUBSTR(�÷���, ������ġ, ����)
  FROM EMPLOYEE;

-- LTRIM / RTRIM: �־��� �÷��̳� ���ڿ� ���� / �����ʿ���
--                ������ ���� Ȥ�� ���ڿ��� ������ ��������
--                ��ȯ�ϴ� �Լ��̴�.
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

-- TRIM: �־��� �÷��̳� ���ڿ��� ��/�ڿ� ������ ���ڸ� ����
SELECT TRIM('   THE   ') FROM DUAL;
SELECT TRIM('Z' FROM 'ZZZ123456ZZZ') FROM DUAL;
SELECT TRIM(LEADING 'Z' FROM 'ZZZTHEZZZ') FROM DUAL;
SELECT TRIM(TRAILING '3' FROM '333THE333') FROM DUAL;
SELECT TRIM(BOTH '3' FROM '333THE333') FROM DUAL;

-- LPAD/RPAD: �־��� �÷� ���ڿ��� ������ ���ڿ��� ���ٿ� ����
--            N�� ���ڿ��� ��ȯ�ϴ� �Լ�
SELECT
       LPAD(EMAIL, 20, '#')
  FROM EMPLOYEE;

SELECT
       RPAD(EMAIL, 20, '#')
  FROM EMPLOYEE;

-- LOWER/UPPER/INITCAP: ��ҹ��� ������ �ִ� �Լ�
-- LOWER(���ڿ�|�÷�): �ҹ��ڷ� ������ �ִ� �Լ�
SELECT
       LOWER('Welcome to My World')
  FROM DUAL;

-- UPPER(���ڿ�|�÷�): �빮�ڷ� ������ �ִ� �Լ�
SELECT
       UPPER('Welcome to My World')
  FROM DUAL;

-- INITCAP(���ڿ�|�÷�): �ձ��ڸ� �빮�ڷ� ������ �ִ� �Լ�
SELECT
       INITCAP('welcome to my world')
  FROM DUAL;
  
-- CONCAT: ���ڿ� Ȥ�� �÷� �� ���� �Է� �޾� �ϳ��� ��ģ �� ����
SELECT
       CONCAT('�����ٶ�', 'ABCD')
  FROM DUAL;

SELECT
       '�����ٶ�' || 'ABCD'
  FROM DUAL;
  
-- REPLACE: �÷� Ȥ�� ���ڿ��� �Է¹޾�
--          �����ϰ��� �ϴ� ���ڿ���
--          �����Ϸ��� �ϴ� ���ڿ��� �ٲ� �� ó��
SELECT
       REPLACE('����� ������', '������', '���ʱ�')
  FROM DUAL;

SELECT
       REPLACE(EMAIL, 'greedy', 'naver')
  FROM EMPLOYEE;

-- �Լ��� �����ϱ�

-- EMPLOYEE ���̺��� �������� �ֹι�ȣ�� ��ȸ�Ͽ�
-- �����, ����, ����, ������ ���� �и��Ͽ� ��ȸ�ϼ���.
-- ��, �÷��� ��Ī�� �����, ����, ����, ���Ϸ� �Ѵ�.

SELECT
       EMP_NAME �����
     , EMP_NO �ֹι�ȣ
     , SUBSTR(EMP_NO, 1, 2) ����
     , SUBSTR(EMP_NO, 3, 2) ����
     , SUBSTR(EMP_NO, 5, 2) ����
  FROM EMPLOYEE;
  
-- WHERE ������ ������ �Լ��� ��� �����ϴ�.
-- ��� �߿� ������� �޿� ��� �̻����� �޴� ����� �̸��� �޿��� ��ȸ
SELECT
       EMP_NAME
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY >= AVG(SALARY);   -- WHERE���� ���� Ƚ�������� �׷��Լ��� ����� �� ����

-- ���������� ��ȸ�� ����
SELECT
       EMP_ID
     , EMP_NAME
     , EMP_NO
  FROM EMPLOYEE
 WHERE SUBSTR(EMP_NO, 8, 1) = '2';
 
-- EMPLOYEE ���̺��� �������� �Ի��Ϸκ���
-- �Ի�⵵, �Ի��, �Ի糯¥�� �и��ؼ� ��ȸ
-- (SUBSTR�� ���� DATE��(��¥��)�� ���ؼ��� ����� �� �ִ�.)
SELECT
       HIRE_DATE
     , SUBSTR(HIRE_DATE, 1, 2) �Ի�⵵
     , SUBSTR(HIRE_DATE, 4, 2) �Ի��
     , SUBSTR(HIRE_DATE, 7, 2) �Ի糯¥
  FROM EMPLOYEE;

-- EMPLOYEE ���̺��� �����, �ֹι�ȣ ��ȸ
-- (��, �ֹι�ȣ�� ������ϸ� ���̰� �ϰ�, '-'������ ���� '*'�� �ٲ㼭 ����Ͻÿ�.)
SELECT
       EMP_NAME
     , EMP_NO
     , SUBSTR(EMP_NO, 1, 7) || '*******'
     , CONCAT(SUBSTR(EMP_NO, 1, 7), '*******')
     , RPAD(SUBSTR(EMP_NO, 1, 7), 14, '*')
  FROM EMPLOYEE;
--------------------------------------------------------------------------------
-- ���� ó�� �Լ�: ABS, MOD, ROUND, FLOOR, TRUNC, CEIL
-- ABS(����|���ڷ� �� �÷���): ���밪 ���ϴ� �Լ�
SELECT
       ABS(-10)
     , ABS(10)
  FROM DUAL;
  
-- MOD(����|���ڷ� �� �÷���, ����|���ڷ� �� �÷���)
-- : �� ���� ����� �������� ���ϴ� �Լ�
--   ó�� ���ڴ� ���������� ��, �� ��° ���ڴ� ���� ��
SELECT
       MOD(10, 5)
     , MOD(10, 3)
  FROM DUAL;
  
-- ROUND(����|���ڷ� �� �÷���, [��ġ])
-- : �ݿø� �ؼ� �����ϴ� �Լ�
SELECT ROUND(123.456) FROM DUAL;
SELECT ROUND(123.456, 0) FROM DUAL;
SELECT ROUND(123.456, 1) FROM DUAL;
SELECT ROUND(123.456, 2) FROM DUAL;
SELECT ROUND(123.456, -1) FROM DUAL;

-- FLOOR(����|���ڷ� �� �÷���): ����ó�� �ϴ� �Լ�
SELECT FLOOR(123.456) FROM DUAL;
SELECT FLOOR(126.678) FROM DUAL;
--SELECT FLOOR(123.678, 1) FROM DUAL;

-- TRUNC(����|���ڷ� �� �÷���, [��ġ]): ����ó��(�ش� ��ġ���� ����) �ϴ� �Լ�
SELECT TRUNC(123.456) FROM DUAL;
SELECT TRUNC(123.456, 1) FROM DUAL;
SELECT TRUNC(123.456, 2) FROM DUAL;
SELECT TRUNC(123.456, -1) FROM DUAL;

-- CEIL(����|���ڷ� �� �÷���): �ø�ó�� �Լ�
SELECT CEIL(123) FROM DUAL;    
SELECT CEIL(123.456) FROM DUAL;
SELECT CEIL(123.678) FROM DUAL;
------------------------------------------------------------------------------------
-- ��¥ ó�� �Լ�: SYSDATE, MONTHS_BETWEEN, ADD_MONTH, NEXT_DAY, LAST_DAY, EXTRACT
SELECT SYSDATE FROM DUAL;

-- MONTHS_BETWEEN(��¥(ū, �ֽ�), ��¥(��, ����))
-- : �� ��¥�� ���� �� ���̸� ���ڷ� �����ϴ� �Լ�

-- �Ի��Ϸκ��� ������� �� ������ �ٹ��ߴ���(�� ä���� ���� �� ����)
SELECT
       EMP_NAME
     , HIRE_DATE
     , FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))
  FROM EMPLOYEE;

-- ADD_MONTHS(��¥, ����)
-- : ��¥�� ���ڸ�ŭ�� ���� ���� ���ؼ� ����(�� ���� �� �ϱ��������� �ľ��ؼ� ���� ���� �����ش�.)
SELECT
       ADD_MONTHS(SYSDATE, 5)
  FROM DUAL;

-- EMPLOYEE ���̺��� ����� �̸�, �Ի���, �Ի� �� 6������ �Ǵ� ��¥�� ��ȸ
SELECT
       EMP_NAME
     , HIRE_DATE
     , ADD_MONTHS(HIRE_DATE, 6)
  FROM EMPLOYEE;
  
-- EMPLOYEE ���̺��� �ٹ� ����� 20�� �̻��� ������ ����� �̸�, �Ի���, �ٹ���� ��ȸ
SELECT
       EMP_NAME
     , HIRE_DATE
     , FLOOR(FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))/12)
  FROM EMPLOYEE
-- WHERE FLOOR(FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))/12) >= 20;
 WHERE ADD_MONTHS(HIRE_DATE, 240) <= SYSDATE;

-- NEXT_DAY(���� ��¥, ����(����|����))
SELECT SYSDATE, NEXT_DAY(SYSDATE, '�����') FROM DUAL;
SELECT SYSDATE, NEXT_DAY(SYSDATE, 5) FROM DUAL;
SELECT SYSDATE, NEXT_DAY(SYSDATE, '��') FROM DUAL;
SELECT SYSDATE, NEXT_DAY(SYSDATE, 'THURSDAY') FROM DUAL;

-- ORACLE ��� ���� ����
ALTER SESSION SET NLS_LANGUAGE = AMERICAN;  -- ����
ALTER SESSION SET NLS_LANGUAGE = KOREAN;    -- �ѱ���

-- LAST_DAY(��¥): �ش� ��¥�� ������ ������ ��¥�� ���Ͽ� ����
SELECT
       SYSDATE
     , LAST_DAY(SYSDATE)
     , LAST_DAY('1987/02/02')
  FROM DUAL;

-- EMPLOYEE ���̺��� �����, �Ի���, �Ի��� ���� �ٹ��ϼ��� ��ȸ
SELECT
       EMP_NAME
     , HIRE_DATE
     , LAST_DAY(HIRE_DATE) - HIRE_DATE + 1 "�Ի��� ���� �ٹ��ϼ� "
  FROM EMPLOYEE;
  
-- EXTRACT: DATE������ ��, ��, �� ������ �����Ͽ� �����ϴ� �Լ�
-- EXTRACT(YEAR FROM ��¥): �⵵�� ����
-- EXTRACT(MONTH FROM ��¥): ���� ����
-- EXTRACT(DAY FORM ��¥): �ϸ� ����
SELECT
       EXTRACT(YEAR FROM SYSDATE)
     , EXTRACT(MONTH FROM SYSDATE)
     , EXTRACT(DAY FROM SYSDATE)
  FROM DUAL;
  
SELECT
       EMP_NAME
     , EXTRACT(YEAR FROM HIRE_DATE) || '��' �Ի�⵵
     , EXTRACT(MONTH FROM HIRE_DATE) || '��' �Ի��
     , EXTRACT(DAY FROM HIRE_DATE) || '��' �Ի���
  FROM EMPLOYEE;

--SELECT * FROM EMPLOYEE;
-----------------------------------------------------------------------------------
-- ����ȯ �Լ�
-- TO_CHAR(��¥, [����]): ��¥�� �����͸� ������ �����ͷ� ����
-- TO_CHAR(����, [����]): ������ �����͸� ������ �����ͷ� ����

SELECT TO_CHAR(1234) FROM DUAL;
SELECT TO_CHAR(1234, '99999') FROM DUAL;
SELECT TO_CHAR(1234, '00000') FROM DUAL;
SELECT TO_CHAR(1234, 'L99999') FROM DUAL;
SELECT TO_CHAR(1234, '$99999') FROM DUAL;
SELECT TO_CHAR(1234, 'L999,999,999') FROM DUAL;
SELECT TO_CHAR(1234, 'L000,000,000') FROM DUAL;
SELECT TO_CHAR(1234, '9.9EEEE') FROM DUAL;
SELECT TO_CHAR(1234, '999') FROM DUAL;       -- �ڸ����� �����ؼ� #���� ó����

-- ���� ���̺��� �����, �޿� ��ȸ
-- (��, �޿��� '��9,000,000' �������� ǥ���Ͻÿ�)
SELECT
       EMP_NAME
     , TO_CHAR(SALARY, 'L999,999,999')
  FROM EMPLOYEE;
  
-- ��¥ ������ ���� ���� �ÿ��� TO_CHAR �Լ��� ����� �� �ִ�.
SELECT TO_CHAR(SYSDATE, 'PM HH24:MI;SS') FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'AM HH:MI;SS') FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'MON DY, YYYY') FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'YYYY-fmMM-DD DAY') FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'YYYY-MM-2DD DAY') FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'YEAR, Q') || '�б�' FROM DUAL;

SELECT
       EMP_NAME
     , HIRE_DATE
     , TO_CHAR(HIRE_DATE, 'YYYY-MM-DD') �Ի���
     , TO_CHAR(HIRE_DATE, 'YYYY-MM-DD HH24:MI:SS') ���Ի���
  FROM EMPLOYEE;

-- ���� ��¥�� ���� 4�ڸ� �⵵, 2�ڸ� �⵵ �̸����� ��ȸ
SELECT
       TO_CHAR(SYSDATE, 'YYYY')
     , TO_CHAR(SYSDATE, 'RRRR')
     , TO_CHAR(SYSDATE, 'YY')
     , TO_CHAR(SYSDATE, 'RR')
     , TO_CHAR(SYSDATE, 'YEAR')
  FROM DUAL;

-- RR�� YY�� ����
-- RR�� ���ڸ��� �ν��� �⵵�� ���ڸ��� �ٲ� ��
-- �ٲ� �⵵�� 50�� �̸�(00 ~ 49)�̸� 2000��븦 �����ϰ�
-- 50�� �̻�(50 ~ 99)�̸� 1900��븦 �����Ѵ�.
SELECT
       TO_CHAR(TO_DATE('980630', 'YYMMDD'), 'YYYY-MM-DD')   -- CHAR -> DATE -> CHAR
  FROM DUAL;        -- 2098������ �ν�
  
SELECT
       TO_CHAR(TO_DATE('980630', 'RRMMDD'), 'YYYY-MM-DD')   -- CHAR -> DATE -> CHAR
  FROM DUAL;        -- 1998������ �ν�

-- EMPLOYEE ���̺��� �̸�, �Ի��� ��ȸ
-- (��, �Ի��Ͽ� ������ ������.
--  '2018�� 6�� 10�� (��)' �������� ó�� �غ���)
SELECT
       EMP_NAME
     , TO_CHAR(HIRE_DATE, 'RRRR"��" fmMM"��" DD"��" (DY)')
  FROM EMPLOYEE;
  
-- TO_DATE: ������ �����͸� ��¥�� �����ͷ� ��ȯ�Ͽ� ����
SELECT
       TO_DATE('20100101', 'RRRRMMDD')
  FROM DUAL;

-- �ణ�� ����
SELECT
       '123' + '10'                                     -- ����õ
     , TO_NUMBER('123') + TO_NUMBER('10')               -- ��õ
  FROM DUAL;
  
-- ��� ���̺��� 2000�⵵ ���Ŀ� �Ի��� �����
-- ���, �̸�, �Ի����� ��ȸ
SELECT
       EMP_ID
     , EMP_NAME
     , HIRE_DATE
  FROM EMPLOYEE
-- WHERE HIRE_DATE >= TO_DATE('20000101', 'RRRRMMDD');  -- ��õ
-- WHERE EXTRACT(YEAR FROM HIRE_DATE) >= 2000;          -- ��õ
 WHERE HIRE_DATE >= '20000101';                         -- �ڵ� ����ȯ�� �����Ƿ� ����õ
 
------------------------------------------------------------------------------------------
-- �����Լ�
-- ���� ���� ��쿡 ������ �� �ִ� ����� �����Ѵ�.
-- DECODE(����|�÷���, ���ǰ�1, ���ð�1, ���ǰ�2, ���ð�2...)
SELECT
       EMP_ID
     , EMP_NAME
     , EMP_NO
     , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '��', '2', '��') ����
  FROM EMPLOYEE;
  
-- ������ ���ڷ� ���ǰ� ���� ���ð��� �ۼ��ϸ�
-- � ���� ������ �ش����� �ʴ� ��� ������ ���ð����� �����ȴ�.
-- (������ else, default ���� ����)
SELECT
       EMP_ID
     , EMP_NAME
     , EMP_NO
     , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '��', '��') ����
  FROM EMPLOYEE;  

-- ������ �޿��� �λ��ϰ��� �Ѵ�.
-- ���� �ڵ尡 J7�� ������ �޿��� 10%�� �λ��ϰ�
-- ���� �ڵ尡 J6�� ������ �޿��� 15%�� �λ��ϰ�
-- ���� �ڵ尡 J5�� ������ �޿��� 20%�� �λ��Ѵ�.
-- �� �� ������ ������ 5%�� �λ��Ѵ�.
-- ���� ���̺��� ������, �����ڵ�, �޿�, �λ�޿�(�� ������ ���� �޿�)�� ��ȸ
SELECT
       EMP_NAME
     , JOB_CODE
     , SALARY
     , DECODE(JOB_CODE, 'J7', SALARY * 1.1
                      , 'J6', SALARY * 1.15
                      , 'J5', SALARY * 1.2
                            , SALARY * 1.05) �λ�޿�
  FROM EMPLOYEE;

-- CASE
--  WHEN ���ǽ�1 THEN �����1
--  WHEN ���ǽ�2 THEN �����2
--  ELSE �����3
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
       END �λ�޿�
  FROM EMPLOYEE;
  
-- ���, �����, �޿��� EMPLOYEE ���̺��� ��ȸ�ϰ�
-- �޿��� 500���� �ʰ��̸� '���'
-- �޿��� 300���� �ʰ� ~ 500���� �����̸� '�߱�'
-- �� ���ϴ� '�ʱ�'���� ��� ó���ϰ� ��Ī�� '����'���� �Ѵ�.
SELECT
       EMP_ID
     , EMP_NAME
     , SALARY
     , CASE
        WHEN SALARY > 5000000 THEN '���'
        WHEN SALARY > 3000000 AND SALARY <= 5000000 THEN '�߱�'
        ELSE '�ʱ�'
       END
  FROM EMPLOYEE;

-----------------------------------------------------------------------------------------   
-- �Լ� ���� ����
--1. ������� �ֹι�ȣ�� ��ȸ��
--  ��, �ֹι�ȣ 9��° �ڸ����� �������� '*'���ڷ� ä��
--  �� : ȫ�浿 771120-1******
SELECT
       EMP_NAME
     , SUBSTR(EMP_NO, 1, 8) || '******'
  FROM EMPLOYEE;

--2. ������, �����ڵ�, ����(��) ��ȸ
--  ��, ������ ��57,000,000 ���� ǥ�õǰ� ��
--     ������ ���ʽ�����Ʈ�� ����� 1��ġ �޿���
SELECT
       EMP_NAME ������
     , JOB_CODE �����ڵ�
     , TO_CHAR(SALARY * (1 + NVL(BONUS, 0)) * 12, 'L999,999,999,999') AS "����(��)"
  FROM EMPLOYEE;

--3. �μ��ڵ尡 D5, D9�� ������ �߿��� 2004�⵵�� �Ի��� ������ 
--	��� ����� �μ��ڵ� �Ի��� ��ȸ
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , HIRE_DATE
  FROM EMPLOYEE
 WHERE DEPT_CODE IN ('D5','D9')
--   AND SUBSTR(HIRE_DATE, 1, 2) = '04';
   AND EXTRACT(YEAR FROM HIRE_DATE) = 2004;

-- HIRE_DATE�� EXTRACT �� �� ����� ���� �׽�Ʈ
SELECT
       EMP_NAME
     , EXTRACT(YEAR FROM HIRE_DATE)
  FROM EMPLOYEE;


--4. ������, �Ի���, �Ի��� ���� �ٹ��ϼ� ��ȸ
--  ��, �ָ��� ������
SELECT
       EMP_NAME
     , HIRE_DATE
     , LAST_DAY(HIRE_DATE)
     , LAST_DAY(HIRE_DATE) - HIRE_DATE + 1
  FROM EMPLOYEE;


--5. ������, �μ��ڵ�, �������, ����(��) ��ȸ
--  ��, ��������� �ֹι�ȣ���� �����ؼ�, 
--     ������ ������ �����Ϸ� ��µǰ� ��.
--  ���̴� �ֹι�ȣ���� �����ؼ� ��¥�����ͷ� ��ȯ�� ����, �����
SELECT
       EMP_NAME
     , DEPT_CODE
     , SUBSTR(EMP_NO, 1, 2) || '��' || SUBSTR(EMP_NO, 3, 2) || '��' || SUBSTR(EMP_NO, 5, 2) || '��' "�������"
--     , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO, 1, 6), 'RRMMDD')) "����"
     , FLOOR(FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(EMP_NO, 1, 6), 'RRMMDD'))) / 12)
  FROM EMPLOYEE;


--6.  �μ��ڵ尡 D5�̸� �ѹ���, D6�̸� ��ȹ��, D9�̸� �����η� ó���Ͻÿ�.
--   ��, �μ��ڵ尡 D5, D6, D9 �� ������ ������ ��ȸ��
--  => case ���
SELECT
       EMP_NAME
     , DEPT_CODE
     , CASE
        WHEN DEPT_CODE = 'D5' THEN '�ѹ���'
        WHEN DEPT_CODE = 'D6' THEN '��ȹ��'
        WHEN DEPT_CODE = 'D9' THEN '������'
       END
  FROM EMPLOYEE
 WHERE DEPT_CODE IN ('D5', 'D6', 'D9');
 
-- 7. �������� �Ի��Ϸκ��� �⵵�� ������, �� �⵵�� �Ի� �ο����� ���Ͻÿ�.
--    �Ʒ��� �⵵�� �Ի��� �ο����� ��ȸ�Ͻÿ�.
--    => TO_CHAR, DECODE, COUNT ���
--    
--    -------------------------------------------------------------
--    ��ü �ο���  2001��   2002��   2003��   2004��
--    -------------------------------------------------------------
SELECT
       COUNT(*)
     , COUNT(DECODE(TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE)), '2001', 'A')) "2001��"
     , COUNT(DECODE(TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE)), '2002', 'A')) "2002��"
     , COUNT(DECODE(TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE)), '2003', 'A')) "2003��"
     , COUNT(DECODE(TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE)), '2004', 'A')) "2004��"
  FROM EMPLOYEE;

-- RESULT SET�� ���� ���ο� �÷�
SELECT
       TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE))                        -- �Ի� �⵵ ������ �÷�
     , DECODE(TO_CHAR(EXTRACT(YEAR FROM HIRE_DATE)), '2001', 'A')   -- 2001�⿡ �ش��ϴ� ������� ���ͷ� �� ������ �÷�
  FROM EMPLOYEE;









