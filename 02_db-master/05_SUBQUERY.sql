-- SUBQUERY(��������)
-- ��������: ������ �ȿ��� ��� �� ������
 
-- ������� ���ö�� ����� �μ� ��ȸ
SELECT
       DEPT_CODE
  FROM EMPLOYEE
 WHERE EMP_NAME = '���ö';  -- 'D9'
 
-- �μ��ڵ尡 D9�� ������ �̸��� ��ȸ
SELECT
       EMP_NAME
  FROM EMPLOYEE
 WHERE DEPT_CODE = 'D9';
 
-- ���ö ����� ���� �μ����� ���ϴ� ���� ��� ��ȸ
SELECT
       EMP_NAME
  FROM EMPLOYEE
 WHERE DEPT_CODE = (SELECT DEPT_CODE
                      FROM EMPLOYEE
                     WHERE EMP_NAME = '���ö'
                   )
   AND EMP_NAME != '���ö';

-- �� ������ ��� �޿����� ���� �޿��� �ް� �ִ� ������
-- ���, �̸�, �����ڵ�, �޿��� ��ȸ�ϼ���
-- ��������
SELECT
       AVG(SALARY)
  FROM EMPLOYEE;

-- ��������  
SELECT
       EMP_ID
     , EMP_NAME
     , JOB_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY >= 3047662.60869565217391304347826086956522;

-- ���������� ������ ���� ���� 
SELECT
       EMP_ID
     , EMP_NAME
     , JOB_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY >= (SELECT AVG(SALARY)
                    FROM EMPLOYEE
                 );
  
-- ���������� ����
-- ������ ��������: ���������� ��ȸ ��� ���� 1���� ���� ��
-- ������ ��������: ���������� ��ȸ ��� ���� ���� ���� ���� ��
-- ���߿� ��������: ���������� ��ȸ ��� ���� �÷��� ���� ���� ��
-- ������ ���߿� ��������: ��ȸ ��� ���� ���� ���� ���� ���� ���� ��
 
-- ���������� ������ ���� �������� �տ� �ٴ� �����ڰ� �ٸ�
-- ������ �������� �տ��� �Ϲ� �� ������ ���
-- >, <, >=, <=, =, !=/<>/^= (��������)
 
-- ���ö ����� �޿����� ���� �޴� �����
-- ���, �̸�, �μ��ڵ�, �����ڵ�, �޿��� ��ȸ
SELECT 
       SALARY
  FROM EMPLOYEE
 WHERE EMP_NAME = '���ö';

SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , JOB_CODE
     , SALARY
  FROM EMPLOYEE
 WHERE SALARY > (SELECT SALARY
                   FROM EMPLOYEE
                  WHERE EMP_NAME = '���ö'
                );

-- ���� ���� �޿��� �޴� ������
-- ���, �̸�, �����ڵ�, �μ��ڵ�, �޿�, �Ի����� ��ȸ
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
                
-- ������ ��������
-- ������ �������� �տ����� �Ϲ� �� ������ ��� ����
-- IN / NOT IN: ���� ���� ��� �� �߿��� �� ���� ��ġ�ϴ� ���� �ִٸ�
--              Ȥ�� ���ٸ��̶�� �ǹ�
-- > ANY / < ANY: ���� ���� ��� �� �߿��� �� ���� ū / ���� ���
--               ���� ���� ������ ũ��? / ���� ū ������ �۳�?
--               (���������� ����� �߿� � �ͺ��ٵ� ũ�ų� �۱⸸ �ϸ� �ȴ�.)
-- > ALL / < ALL: ��� ������ ū / ���� ���
--               ���� ū ������ ũ��? / ���� ���� ������ �۳�?
--               (��� ���������� ����麸�� ũ�ų� �۾ƾ� �Ѵ�.)
-- EXISTS / NOT EXISTS: ������������ ����ϴ� �����ڷ�
--                      ���� �����ϳ�? / �������� �ʴ���?
                      
-- �μ��� �ְ� �޿��� �޴� ������ �̸�, ����, �μ�, �޿� ��ȸ
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
  
-- �����ڿ� �ش��ϴ� ������ ���� ������ �����ڰ� �ƴ� ������
-- ������ �����Ͽ� ��ȸ�Ͻÿ�.
-- (���, �̸�, �μ���, ���޸�, '������' AS ���� / '����' AS ����)
-- (�����ڸ� �ΰ� �ִ� ������� �����ڸ� �ߺ��� ���� ����)
SELECT * FROM EMPLOYEE;

SELECT
       DISTINCT(MANAGER_ID)
  FROM EMPLOYEE
 WHERE MANAGER_ID IS NOT NULL;

-- ������ ��ȸ
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.DEPT_TITLE
     , C.JOB_NAME
     , '������' AS ����
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  LEFT JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE)
 WHERE A.EMP_ID IN (SELECT DISTINCT(D.MANAGER_ID)
                      FROM EMPLOYEE D
                     WHERE D.MANAGER_ID IS NOT NULL
                   );

-- ���� ��ȸ
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.DEPT_TITLE
     , C.JOB_NAME
     , '����' AS ����
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  LEFT JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE)
 WHERE A.EMP_ID NOT IN (SELECT DISTINCT(D.MANAGER_ID)
                          FROM EMPLOYEE D
                         WHERE D.MANAGER_ID IS NOT NULL
                       );

-- ���� ��ü�� �����ڿ� �������� �����Ͽ� �ѹ��� ��ȸ
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.DEPT_TITLE
     , C.JOB_NAME
     , '������' AS ����
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
     , '����' AS ����
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  LEFT JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE)
 WHERE A.EMP_ID NOT IN (SELECT DISTINCT(D.MANAGER_ID)
                          FROM EMPLOYEE D
                         WHERE D.MANAGER_ID IS NOT NULL
                       )
 ORDER BY ���� DESC; -- �������� �� �� �ְ� ���� �Ϸ���...
  
-- ���� ���� �޿��� ���� ū ������ ���� �޴� ���� ������(��� ����麸�� �޿��� ���� �޴� ����)
-- ���, �̸�, ���޸�, �޿��� ��ȸ
-- ��, > ALL Ȥ�� < ALL �����ڸ� ���

-- ���� ���޵��� �޿�
SELECT A.SALARY
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE)
 WHERE B.JOB_NAME = '����';

SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.JOB_NAME
     , A.SALARY
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE)
 WHERE B.JOB_NAME = '����'
   AND A.SALARY > ALL (SELECT C.SALARY
                         FROM EMPLOYEE C
                         JOIN JOB D ON (C.JOB_CODE = D.JOB_CODE)
                        WHERE D.JOB_NAME = '����'
                      );
                      
-- �븮 ������ ������ �߿��� ���� ������ �ּ� �޿����� ���� �޴�(���� �߿� �ƹ��� �Ѹ� ���ٸ� ���� ������ ��)
-- ������ ���, �̸�, ���޸�, �޿��� ��ȸ
-- ��, > ANY Ȥ�� < ANY ������ ���
SELECT A.SALARY
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE)
 WHERE B.JOB_NAME = '����';
 
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , B.JOB_NAME
     , A.SALARY
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE)
 WHERE B.JOB_NAME = '�븮'
   AND A.SALARY > ANY (SELECT C.SALARY
                         FROM EMPLOYEE C
                         JOIN JOB D ON (C.JOB_CODE = D.JOB_CODE)
                        WHERE D.JOB_NAME = '����'
                      );
    
-- EXISTS / NOT EXISTS
-- ���������� ����� �ִ��� ������
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

-- ��ȸ�� ����� ON/OFF�ϴ� �Ͱ� ���� �ڵ�                  
SELECT 
       A.EMP_NAME
  FROM EMPLOYEE A
 WHERE 1 = 0;
 
-- �ڱ� ������ ��� �޿��� �ް� �ִ� ������
-- ���, �̸�, �����ڵ�, �޿��� ��ȸ
-- ��, �޿��� �޿� ����� ���� ������ ��� �Ͻÿ�.(TRUNC(�÷���, -4))
SELECT
       TRUNC(1234567, -4)
  FROM DUAL;

-- ���޺� ��� �޿�(��������)
SELECT TRUNC(AVG(A.SALARY), -4)
  FROM EMPLOYEE A
 GROUP BY A.JOB_CODE;

-- ���޺� ��հ� ��ġ�ϴ� �޿��� �޴� ��� ��ȸ
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

-- ���޺� ��հ� ��ġ�ϸ鼭 �ش� ������ �ڽ��� ������ ��� ��ȸ
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
 
-- ����� �������� ���� �μ�, ���� ���޿� �ش��ϴ�
-- ����� �̸�, ����, �μ�, �Ի����� ��ȸ
SELECT * FROM EMPLOYEE;
 
-- ����� �������� �μ��� ����
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
--   AND A.ENT_YN = 'N';  -- ����ڸ� �����ϱ� ���� ª�� ó�� ������ �����ϴ�.
   AND A.EMP_ID NOT IN (SELECT C.EMP_ID
                          FROM EMPLOYEE C
                         WHERE C.ENT_YN = 'Y'
                           AND SUBSTR(C.EMP_NO, 8, 1) = '2'
                       );
  
-- ���������� ��� ��ġ
-- : SELECT ��, FROM ��, WHERE ��, GROUP BY ��, HAVING ��, ORDER BY ��
-- DML ����: INSERT��, UPDATE��, DELETE��
-- DDL ����: CREATE TABLE��, CREATE VIEW��

-- FROM������ ���������� ����� �� �ִ�: ���̺� ��ſ� ���
-- �ζ��� ��(INLINE VIEW)��� ��
-- : ���������� ���� ��� ����(RESULT SET)���κ��� ������ �ǹ�

-- ���޺� �޿� ���(��������)
 SELECT
        A.JOB_CODE
      , TRUNC(AVG(A.SALARY), -4)
   FROM EMPLOYEE A
  GROUP BY A.JOB_CODE;

-- �ζ��� �信�� �������� ó���� �÷��� ���� �������� ��Ī���θ� ��ȸ�� �� �ִ�.
SELECT
       B.JOB_CODE
     , B.JOBAVG
  FROM ( SELECT A.JOB_CODE
              , TRUNC(AVG(A.SALARY), -4) AS JOBAVG
           FROM EMPLOYEE A
          GROUP BY A.JOB_CODE
       ) B;

-- ���� ���� �ζ��� �信�� �����, �޿�, ���޸��� �߰��� �˰� ���� ��
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
  
-- ������������ ���� ��Ī�� ������������ �� �� �ִ�.
SELECT
       A.EMP_NAME �̸�
     , B.DEPT_TITLE �μ���
     , C.JOB_NAME ���޸�
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE);

-- �ζ��� ���� ���������� �÷��� ��Ī�� �޸� ���� ���������� �ݵ�� ��Ī�� �޾���� �Ѵ�.
-- ���� ��Ī�� ���ٸ� ������� �ƴ� �÷��� ���� ������������ �÷������� ��ȸ�� �����ϴ�.
-- ��, �ζ��� ���� ���������� ��������� ����� �÷��� �ִٸ� �� ���� �ζ��� �信�� �ݵ��
-- ��Ī�� �ް� ���� ���������� ��Ī���θ� ��ȸ�� �� �ִ�.
SELECT
       D.�̸�
     , D.�μ���
     , D.���޸�
  FROM (SELECT A.EMP_NAME �̸�
             , B.DEPT_TITLE �μ���
             , C.JOB_NAME ���޸�
          FROM EMPLOYEE A
          JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
          JOIN JOB C ON (A.JOB_CODE = C.JOB_CODE)
       ) D
 WHERE D.�μ��� = '�ѹ���';

-- �ڡڡ� �ζ��� �並 Ȱ���� TOP-N�м� �ڡڡ�  
-- ROWNUM: �� ��ȣ(����)�� �ǹ���
--         FROM�� ��� ��(Ʃ��)�� �ڵ����� ������ �޸��� �ȴ�.
--         ROWNUM�� Ȱ���� ������������ �ݵ�� 1�������� ���Եǰ� ������ �����ؾ� �Ѵ�. �ڡڡ�  
SELECT
       ROWNUM
     , SALARY
  FROM EMPLOYEE
 ORDER BY SALARY DESC;
 
-- �޿��� ���� �޴� ����� 10�� ����
SELECT
       ROWNUM
     , B.SALARY
  FROM (SELECT A.SALARY
          FROM EMPLOYEE A
         ORDER BY SALARY DESC
       ) B
 WHERE ROWNUM <= 10;    -- 1�� �ݵ�� �����ؾ� �ϹǷ� �ַ� � ������ �۴ٴ� ������ ���� �ȴ�.
 
-- ROWNUM�� 1�� �ݵ�� �����ؾ��Ѵٴ� ��Ģ�� ��Ű�� �ʱ� ���� 2�� ���� ������
SELECT
       C.����
     , C.SALARY
  FROM (SELECT ROWNUM ����
             , B.SALARY
         FROM (SELECT A.SALARY
                 FROM EMPLOYEE A
                ORDER BY SALARY DESC
              ) B
       )C
 WHERE C.���� <= 10
   AND C.���� >= 4;

-- �μ��� �޿� ��� ���� 3�� �ȿ� ��� �μ�����
-- �μ� �ڵ�� �μ���, ��� �޿��� ��ȸ

SELECT
       A.DEPT_CODE
     , B.DEPT_TITLE
     , AVG(A.SALARY)
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
 GROUP BY A.DEPT_CODE, B.DEPT_TITLE
 ORDER BY 3 DESC;

-- ���� ������ ������ �ڸ���, ���������� ����� ������ ����Ѵ�.
SELECT
       C.DEPT_CODE
     , C.DEPT_TITLE
     , C.�޿����
  FROM (SELECT A.DEPT_CODE
             , B.DEPT_TITLE
             , AVG(A.SALARY) �޿����
          FROM EMPLOYEE A
          JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
         GROUP BY A.DEPT_CODE, B.DEPT_TITLE
         ORDER BY 3 DESC
       ) C
 WHERE ROWNUM <= 3;
  
-- �ٸ� ���(���ο��� JOIN)���ε� Ǯ ���� �ִ�.
SELECT
       B.DEPT_CODE
     , C.DEPT_TITLE
     , B.�޿����
  FROM (SELECT A.DEPT_CODE
             , AVG(A.SALARY) �޿����
          FROM EMPLOYEE A
         GROUP BY A.DEPT_CODE
         ORDER BY 2 DESC
       ) B
  JOIN DEPARTMENT C ON (B.DEPT_CODE = C.DEPT_ID)
 WHERE ROWNUM <= 3;

-- RANK() �Լ�
-- : ������ ���� ������ ����� ������ �ο�����ŭ �ǳ� �ٰ� ���� ������ ����ϴ� ���
-- DENSE_RANK() �Լ�
-- : �ߺ��Ǵ� ���� ������ ����� �ǳʶ��� �ʰ� ���� ����� ó��
 
-- FROM�� ���ķ� �ٷ� ������ �����ϴ� ���� �ƴ� ���� ���� ������ ������
SELECT
       A.EMP_NAME
     , A.SALARY
     , RANK() OVER(ORDER BY A.SALARY DESC) ����
  FROM EMPLOYEE A;
  
SELECT
       A.EMP_NAME
     , A.SALARY
     , DENSE_RANK() OVER(ORDER BY A.SALARY DESC) ����
  FROM EMPLOYEE A;

-- RANK() OVER�� ����ؼ��� TOP-N �м��� �� �� �ִ�.
SELECT
       B.EMP_NAME
     , B.SALARY
     , B.����
  FROM (SELECT A.EMP_NAME
             , A.SALARY
             , RANK() OVER(ORDER BY A.SALARY DESC) ����
          FROM EMPLOYEE A
       ) B
 WHERE ���� <= 3;
 
-- WITH �̸� AS (����������)
-- ���������� �̸��� �ٿ��ְ� ������������ ��� �� �̸��� ����ϰ� ��
-- �ζ��� ��� ��� �� ������������ �̿� ��
-- ���� ���������� ���� �� ��� �� ��� �ߺ� �ۼ��� ���� �� �ִ�.
-- ���� �ӵ��� �������ٴ� ������ �ְ� �������� ����.
 

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
 
-- ���������� �پ��� ������ �Ẹ��.

-- �μ��� �޿� �հ谡 ��ü �޿��� �� ���� 20%���� ����
-- �μ��� �μ���� �μ��� �޿� �հ� ��ȸ

-- ��ü �޿��� �� ���� 20%
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
 
-- �ζ��� �� ������ε� Ǯ���.
SELECT
       C.DT
     , C.SSAL
  FROM (SELECT B.DEPT_TITLE DT
             , SUM(A.SALARY) SSAL     -- ���� ��� �÷��� �ݵ�� ��Ī ���
          FROM EMPLOYEE A
          JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
         GROUP BY B.DEPT_TITLE
       ) C
 WHERE C.SSAL > (SELECT SUM(SALARY) * 0.2
                   FROM EMPLOYEE
                );
 
-- WITH AS�� �������� ���� �� �����ϱ�
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
 
-- ��[ȣ��]�� ��������
-- �Ϲ������δ� ���������� ���� ��� ���� ���� ������ �� ����
-- ���������� ����ϴ� ���̺��� ���� ���������� �̿��ؼ� ����� ����
-- ���������� ���̺� ���� ����Ǹ�, ���������� ������� �ٲ�� ��
 
-- ������ ����� EMPLOYEE ���̺� EMP_ID�� �����ϴ� ������ ���� ��ȸ
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
 
-- ��Į�� ��������
-- ��� �������� + ������ ��������
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.MANAGER_ID
     , NVL((SELECT B.EMP_NAME
              FROM EMPLOYEE B
             WHERE A.MANAGER_ID = B.EMP_ID
           ), '����')
  FROM EMPLOYEE A;
 
 
 
 
 
 
 
 
 
 
 
 
 










 