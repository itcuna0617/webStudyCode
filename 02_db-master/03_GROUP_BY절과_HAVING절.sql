-- GROUP BY�� HAVING �׸��� ORDER BY
 
-- ORDER BY��: SELECT�� ���(RESULT SET)���� �÷��� �������� ������ �� �� �����
-- ORDER BY �÷��� | �÷� ��Ī | �÷� ���� ���� [ASC] | DESC [NULLS FIRST | LAST]
 
-- ORDER BY �÷��� ���Ĺ��, �÷��� ���Ĺ��, �÷��� ���Ĺ��...
-- ù ��° �������� �ϴ� �÷��� ���� �����ϰ�
-- ���� ���鿡 ���� �ι� ° ���� �÷����� ������ �ϰ� �Ǵ� ������� ���� ��
 
-- SELECT ���� �� �������� ��ġ��
-- �ڡڡ� ���� ������ SELECT������ �� �������� ���� �� �ڡڡ�
 
/*
5: SELECT
1: FROM 
2: WHERE
3: GROUP BY
4: HAVING
6: ORDER BY
*/ 
 
SELECT
       EMP_NAME
     , SALARY �޿�
  FROM EMPLOYEE
-- ORDER BY SALARY ASC;     -- SALARY �÷��� ��������
-- ORDER BY SALARY DESC;    -- SALARY �÷��� ��������
-- ORDER BY 2;              -- ASC�� DESC�� �� ���� �⺻�� ��������, ���ڸ� ���� RESULT SET�� �÷� ������ ���Ѵ�.
 ORDER BY �޿�;              -- SELECT ������ ��Ī�� ����ϸ� ��Ī���� ���������� �����ϴ�. 
 
 
SELECT
       DEPT_CODE
     , SUM(SALARY)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE
 ORDER BY 1 DESC NULLS LAST;    -- ���� ����� ���� �࿡ ���� NULL���� ��ġ�� ����
  
SELECT
       *
  FROM EMPLOYEE
 ORDER BY DEPT_CODE DESC NULLS LAST, JOB_CODE DESC;
  
  
-- GROUP BY ��: ���� ������ ���� �� ��ϵ� �÷�(���� ���̺� �÷� Ȥ�� RESULT SET�� �÷�)�� ������
--              ���� ������ �ϳ��� �׷����� ����
-- GROUP BY �÷��� | �Լ���, ...
-- ���� ���� ���� ��� �ϳ��� �׷� ������ ó�� �� �������� ����Ѵ�.
-- �׷����� ���� ���� ���ؼ� SELECT�������� �׷��Լ��� ����Ѵ�.

SELECT
       DEPT_CODE
     , SUM(SALARY)
     , FLOOR(AVG(SALARY))
  FROM EMPLOYEE
 WHERE DEPT_CODE IS NOT NULL
 GROUP BY DEPT_CODE
 ORDER BY 1;
 
-- �μ����� �ο����� ���µ�(COUNT �Լ�) �׷캰 �ο����� ��ü ����� ���� �ְ� Ư�� ���� �÷��� ���� NULL�� ����� �����ϰ� �� ���� �ִ�.
SELECT
       DEPT_CODE
     , COUNT(*)
     , COUNT(DEPT_CODE)
     , COUNT(BONUS)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE;

-- �׷� �ΰ� �̻� �����
SELECT
       DEPT_CODE
     , JOB_CODE
     , COUNT(*)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE
        , JOB_CODE
 ORDER BY 1, 2 DESC;
 
-- ���� ���̺��� �μ� �ڵ庰 �׷��� �����Ͽ�
-- �μ��ڵ�, �׷캰 �޿��� �հ�,
-- �׷캰 �޿��� ���(����ó��), �ο��� ��ȸ�ϰ�
-- �μ� �ڵ� ������ ����(��������) �Ͻÿ�.
SELECT
       DEPT_CODE
     , SUM(SALARY)
     , FLOOR(AVG(SALARY))
     , COUNT(*)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE
 ORDER BY 1;
 
--  ���� ���̺��� ���޺� �����ڵ�, ���ʽ��� �޴� ������� ��ȸ�Ͽ�
--  ���� �ڵ� ������ �������� �����Ͻÿ�.
SELECT
       JOB_CODE
     , COUNT(*)
  FROM EMPLOYEE
 WHERE BONUS IS NOT NULL
 GROUP BY JOB_CODE
 ORDER BY 1;

-- ���ʽ� �ȹ޴� ���޵� ���(RESULT SET)���� ��ȸ �� ���
SELECT
       JOB_CODE
     , COUNT(BONUS)
  FROM EMPLOYEE
 GROUP BY JOB_CODE
 ORDER BY 1;
 
-- ���� ���̺��� �ֹι�ȣ�� 8��° �ڸ��� ��ȸ�Ͽ�
-- 1�̸� ��, 2�� ���� ����� ��ȸ�ϰ�
-- ������ �޿� ���(����ó��), �޿� �հ�, �ο����� ��ȸ�� ��
-- �ο����� �������� �����Ͻÿ�.
SELECT
       DECODE(SUBSTR(EMP_NO, 8, 1), '1', '��', '2', '��')
     , FLOOR(AVG(SALARY))
     , SUM(SALARY)
     , COUNT(*)
  FROM EMPLOYEE
 GROUP BY DECODE(SUBSTR(EMP_NO, 8, 1), '1', '��', '2', '��');
 
-- HAVING ��: �׷��Լ��� ���� �� �׷쿡 ���� ������ ������ �� ���
-- HAVING �÷��� | �Լ���(�׷��Լ�) �� ������ �񱳰�

-- 300���� �̻��� �޴� ������� �μ��� ��� �޿�(SALARY)�� ���� ����.(WHERE���� �ذ�)
SELECT
       DEPT_CODE
     , FLOOR(AVG(SALARY))
  FROM EMPLOYEE
 WHERE SALARY >= 3000000                  -- ������ �Լ��� ����
 GROUP BY DEPT_CODE
 ORDER BY 1;
 
-- �޿� ����� 300���� �̻��� �μ����� ���ϴ� ������� �μ��� ��� �޿�(SALARY)�� ���� ����.(HAVING���� �ذ�)
SELECT
       DEPT_CODE
     , FLOOR(AVG(SALARY))
  FROM EMPLOYEE
 GROUP BY DEPT_CODE
 HAVING FLOOR(AVG(SALARY)) >= 3000000    -- �׷� �Լ��� ����
 ORDER BY 1;
 
-- �޿� �հ谡 ���� ���� �μ��� �μ� �ڵ�� �޿� �հ踦 ���Ͻÿ�.
-- ���� �޿� �հ谡 ���� �μ��� �޿� �հ�
SELECT
       MAX(SUM(SALARY))
  FROM EMPLOYEE
 GROUP BY DEPT_CODE;  -- 17700000

-- ���� �޿� �հ谡 ���� �μ��� �޿� �հ�� ��ġ�ϴ� �׷��� SELECT 
SELECT
       DEPT_CODE
     , SUM(SALARY)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE
HAVING SUM(SALARY) = 17700000;

-- ���������� Ȱ���ϸ� �ϳ��� ������ �ۼ��� �����ϴ�.
SELECT
       DEPT_CODE
     , SUM(SALARY)
  FROM EMPLOYEE
 GROUP BY DEPT_CODE
HAVING SUM(SALARY) = (SELECT
                             MAX(SUM(SALARY))
                        FROM EMPLOYEE
                       GROUP BY DEPT_CODE);
  
 
-- �����Լ�
-- ROLLUP �Լ�: �׷캰�� �߰� ���� ó���� �ϴ� �Լ�
--             GROUP BY �������� ����ϴ� �Լ�
-- �׷캰�� ������ ���� ���� �߰� ����� �� ���踦 ���� �� ����Ѵ�.
-- �׷캰�� ���� ������鿡 ���� �� ���谡 �ڵ����� �߰��ȴ�.
SELECT
       JOB_CODE
     , SUM(SALARY)
  FROM EMPLOYEE
 GROUP BY ROLLUP(JOB_CODE);

-- CUBE �Լ�: �׷캰 ������ ����� �����ϴ� �Լ�
-- ���� �Ǵ� �÷����� �ϳ��� ���� ROLLUP�� ���̰� ����.
SELECT
       JOB_CODE
     , SUM(SALARY)
  FROM EMPLOYEE
 GROUP BY CUBE(JOB_CODE)
 ORDER BY 1 NULLS LAST;
 
-- ���� �� ���� �׷��� ����� �����Լ� ����
SELECT
       DEPT_CODE
     , JOB_CODE
     , SUM(SALARY)
  FROM EMPLOYEE
 GROUP BY ROLLUP(DEPT_CODE, JOB_CODE);

-- CUBE�� ���� ���ڰ� �߰��� ������ ROLLUP�� �� �����Ѵ�.
SELECT
       DEPT_CODE
     , JOB_CODE
     , SUM(SALARY)
  FROM EMPLOYEE
 GROUP BY CUBE(DEPT_CODE, JOB_CODE);
 
-- GROUPING �Լ�: ROLLUP�̳� CUBE�� ���� ���⹰��
--               ���ڷ� ���޹��� �÷� ������ ���⹰�̸� 0�� ��ȯ�ϰ�,
--               �ƴϸ� 1�� ��ȯ�ϴ� �Լ�
SELECT
       DEPT_CODE
     , JOB_CODE
     , SUM(SALARY)
     , GROUPING(DEPT_CODE) "�μ��� �׷� ���� ����"
     , GROUPING(JOB_CODE) "���޺� �׷� ���� ����"
  FROM EMPLOYEE
 GROUP BY CUBE(DEPT_CODE, JOB_CODE);

-- 0�� 1�δ� �������� �������� ������ ��Ȳ�� ���ͷ� ������ �����ϱ� ���� CASE�� Ȱ��
SELECT
       DEPT_CODE
     , JOB_CODE
     , SUM(SALARY)
     , CASE
        WHEN GROUPING(DEPT_CODE) = 0 AND GROUPING(JOB_CODE) = 1 THEN '�μ����հ�'
        WHEN GROUPING(DEPT_CODE) = 1 AND GROUPING(JOB_CODE) = 0 THEN '���޺��հ�'
        WHEN GROUPING(DEPT_CODE) = 1 AND GROUPING(JOB_CODE) = 1 THEN '���հ�'
       END
  FROM EMPLOYEE
 GROUP BY CUBE(DEPT_CODE, JOB_CODE)
 ORDER BY 1;
 
-- SET OPERATOR(���տ���)
-- UNION: ���� ���� ���� ��� Ʃ�õ��� �ϳ��� ��ġ�� �������̴�.
--        �ߺ��� ������ �����Ͽ� �ϳ��� ��ģ��. (������)
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D5'
UNION
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY > 3000000;
 
-- UNION ALL: ���� ���� ���� ��� Ʃ�õ��� �ϳ��� ��ġ�� �������̴�.
--            UNION���� �������� �ߺ� ������ ��� ���Խ�Ų�ٴ� ���̴�.
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D5'
UNION ALL
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY > 3000000
 ORDER BY 2;
 
-- INTERSECT: ���� ���� SELECT�� ������� ���� �κи� ����� ����(������)
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D5'
INTERSECT
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY > 3000000
 ORDER BY 2;
 
-- MINUS: ���� SELECT ���� ��� Ʃ�õ鿡�� ���� SELECT�� ��� Ʃ�ð� ��ġ�� �κ���
--        ������ ������ �κи� ����(������)(SELECT�� ���� ����)
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D5'
MINUS
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY > 3000000
 ORDER BY 2;  
  
-- GROUPING SETS: �׷캰�� ó�� �� ���� ���� SELECT���� �ϳ���
--                ��ĥ �� ����Ѵ�.(�׷��� ���� ������ �پ��� ��� �ѹ��� ��ȸ�ϰ��� �� �� ���)
SELECT
       DEPT_CODE
     , JOB_CODE
     , MANAGER_ID
     , FLOOR(AVG(SALARY))
  FROM EMPLOYEE
 GROUP BY DEPT_CODE, JOB_CODE, MANAGER_ID;

SELECT
       DEPT_CODE
     , MANAGER_ID
     , FLOOR(AVG(SALARY))
  FROM EMPLOYEE
 GROUP BY DEPT_CODE, MANAGER_ID;
                
SELECT
       JOB_CODE
     , MANAGER_ID
     , FLOOR(AVG(SALARY))
  FROM EMPLOYEE
 GROUP BY JOB_CODE, MANAGER_ID;

-- ���� ������� �ѹ��� ���� ���� GROUPING SETS Ȱ��
-- �پ��� �׷캰 ��ȸ ����� ª�� �ڵ�� �ϼ��ؼ� ��ȸ�ϰ��� �� �� ����� �� �ִ�.
SELECT
       DEPT_CODE
     , JOB_CODE
     , MANAGER_ID
     , FLOOR(AVG(SALARY))
  FROM EMPLOYEE
 GROUP BY GROUPING SETS((DEPT_CODE, JOB_CODE, MANAGER_ID)
                      , (DEPT_CODE, MANAGER_ID)
                      , (JOB_CODE, MANAGER_ID))
 ORDER BY 1;
  
  
  
  
  
  
  
  
  