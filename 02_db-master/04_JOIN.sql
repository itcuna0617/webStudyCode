-- ����(JOIN)
-- JOIN: �� �� �̻��� ���̺��� �ϳ��� ���ļ� �ϳ��� ���(RESULT SET)���� ��ȸ�ϱ� ���� ���ȴ�.
 
-- ����Ŭ ����
-- FROM���� ','�� �����Ͽ� ��ġ�� �� ���̺���� ����ϰ�
-- WHERE���� ��ġ�⿡ ��� �� �÷����� ����Ѵ�.
 
-- ���ῡ ��� �� �� �÷��� �÷����� �ٸ� ���
SELECT
       EMP_ID
     , EMP_NAME
     , DEPT_CODE
     , DEPT_TITLE
     , LOCATION_ID
  FROM EMPLOYEE
     , DEPARTMENT
 WHERE DEPT_CODE = DEPT_ID;  -- �� ���̺��� ���� MAPPING�� ����
 
SELECT * FROM EMPLOYEE;
SELECT * FROM DEPARTMENT;

-- ���ῡ ����� �� �÷����� ���� ���
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

-- ���̺�� ���� ��Ī ���
-- ������ �÷��� ���� ��Ī�� �翬�� �޾ƾ� ������ ������ �÷��� ���ؼ��� ��Ī���� �����ϴ� ������ ������.
SELECT
       E.EMP_ID
     , E.EMP_NAME
     , E.JOB_CODE
     , J.JOB_NAME
  FROM EMPLOYEE E
     , JOB J
 WHERE E.JOB_CODE = J.JOB_CODE;

-- ��Ī�� ������ ������ �� �� �ֵ��� ���ĺ������� �޾�����.
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.JOB_CODE
     , B.JOB_NAME
  FROM EMPLOYEE A
     , JOB B
 WHERE A.JOB_CODE = B.JOB_CODE;
 
-- ANSI ǥ��
-- �÷��� ���� ����(������ ���� ����)�� ON()�� ����� �� �ִ�.
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.JOB_CODE
     , B.JOB_NAME
  FROM EMPLOYEE A
  JOIN JOB B ON (A.JOB_CODE = B.JOB_CODE);

-- �÷����� ���� ��� USING()�� ����� �� ����(��, �������� ����, ��Ī�� ������ �ϱ� ����)
SELECT
       EMP_ID
     , EMP_NAME
     , JOB_CODE
     , JOB_NAME
  FROM EMPLOYEE 
  JOIN JOB USING (JOB_CODE); 
  
-- �μ� ���̺�� ���� ���̺��� �����Ͽ� ���̺� �ִ� ��� �����͸� ��ȸ�� ����.
SELECT * FROM DEPARTMENT;
SELECT * FROM LOCATION;

-- ANSI ǥ��
SELECT
       *
  FROM DEPARTMENT
  JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE);

-- ����Ŭ ǥ��
SELECT
       *
  FROM DEPARTMENT, LOCATION
 WHERE LOCATION_ID = LOCAL_CODE;

-- ������ �⺻�� EQUAL JOIN�̴�.(EQU JOIN�̶�� ��)
-- ����Ǵ� �÷��� ���� ��ġ�ϴ� ��鸸 ���ε�
 
-- ��ġ�ϴ� ���� ���� ���� ���ο��� ���ܵǴ� ���� INNER JOIN�̶�� �Ѵ�.
 
-- JOIN�� �⺻�� INNER JOIN & EQU JOIN�̴�.

-- EMPLOYEE ���̺�� DEPARTMENT ���̺��� �����ϰ� ��� ���� ������ ���캸��.
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID); -- ���� ������ �������� ���ϴ� ���� ��ȸ�� ���� �ʴ´�.
  
-- OUTER JOIN: �� ���̺��� �����ϴ� �÷� ���� ��ġ���� �ʴ� �൵
--             ���ο� ������ ��Ŵ
--             �ݵ�� OUTER JOIN���� ����ؾ� �Ѵ�.
             
-- 1. LEFT OUTER JOIN: ��ġ�⿡ ����� �� ���̺� �� ���� ��� �� ���̺��� ���� ���� �������� JOIN
-- 2. RIGHT OUTER JOIN: ��ġ�⿡ ����� �� ���̺� �� ������ ��� �� ���̺��� ���� ���� �������� JOIN
-- 3. FULL OUTER JOIN: ��ġ�⿡ ����� �� ���̺��� ���� ��� ���� ����� �����Ͽ� JOIN
 
-- ANSIǥ���� INNER JOIN
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
  
-- ANSI ǥ���� LEFT OUTER JOIN
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A
--  LEFT OUTER JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);

-- ����Ŭ ���� LEFT OUTER JOIN
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A
     , DEPARTMENT B
 WHERE A.DEPT_CODE = B.DEPT_ID(+);
 
-- RIGHT OUTER JOIN
-- ANSI ǥ��
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A 
--  RIGHT OUTER JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
  RIGHT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
 
-- ����Ŭ ����
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A 
     , DEPARTMENT B
 WHERE A.DEPT_CODE(+) = B.DEPT_ID;
 
-- FULL OUTER JOIN
-- ANSI ǥ��
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A 
--  FULL OUTER JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
  FULL JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID);
 
-- ����Ŭ ����
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A 
     , DEPARTMENT B
 WHERE A.DEPT_CODE(+) = B.DEPT_ID(+); -- ���� ��
 
-- CROSS JOIN: ī���̼ǰ��̶�� �Ѵ�.
--             ���εǴ� ���̺���� �� ����� ��� ���εǰ� �����͸� �˻��ϴ� ����̴�.
SELECT
       A.EMP_NAME
     , B.DEPT_TITLE
  FROM EMPLOYEE A 
 CROSS JOIN DEPARTMENT B;

-- �Ϲ������δ� CROSS JOIN�� �ǵ��Ѵٱ� ���� MAPPING ������ �߸� �ۼ� ���� �� CROSS JOIN�� �߻��Ѵ�.
SELECT
       *
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON (1 = 1);
 
-- NON EQUAL JOIN(NON EQU JOIN)
SELECT * FROM EMPLOYEE;
SELECT * FROM SAL_GRADE;

-- ANSI ǥ��
SELECT
       A.EMP_NAME
     , A.SALARY
     , B.SAL_LEVEL
     , B.MIN_SAL
     , B.MAX_SAL
  FROM EMPLOYEE A
--  JOIN SAL_GRADE B ON (A.SALARY BETWEEN B.MIN_SAL AND B.MAX_SAL);
  JOIN SAL_GRADE B ON (A.SALARY >= B.MIN_SAL AND A.SALARY <= B.MAX_SAL);
  
-- ����Ŭ ����
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

-- SELF JOIN: ���� ���̺��� �����ϴ� ���
--            �ڱ� �ڽ� ���̺�� ������ �δ� ���̴�.
-- ANSI ǥ��
SELECT
       A.EMP_ID
     , A.EMP_NAME ����̸�
     , A.DEPT_CODE
     , A.MANAGER_ID
     , B.EMP_NAME �������̸�
  FROM EMPLOYEE A
  LEFT JOIN EMPLOYEE B ON (A.MANAGER_ID = B.EMP_ID);

-- ����Ŭ ����
SELECT
       A.EMP_ID
     , A.EMP_NAME ����̸�
     , A.DEPT_CODE
     , A.MANAGER_ID
     , B.EMP_NAME �������̸�
  FROM EMPLOYEE A
     , EMPLOYEE B 
 WHERE A.MANAGER_ID = B.EMP_ID(+);
 
-- ���� JOIN: N��(3�� �̻�)�� ���̺��� ��ȸ�� �� ����ϴ� JOIN
-- ANSI ǥ��
SELECT
       A.EMP_ID
     , A.EMP_NAME
     , A.DEPT_CODE
     , B.DEPT_TITLE
     , C.LOCAL_NAME
     , C.NATIONAL_CODE
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON (A.DEPT_CODE = B.DEPT_ID)
  LEFT JOIN LOCATION C ON (B.LOCATION_ID = C.LOCAL_CODE)    -- ���� ���ο��� OUTER������ �ߴٸ� OUTER ������ ��� �����ؾ� ���ϴ� ����� ���� �� �ִ�.
 WHERE A.EMP_ID = '210';

-- ����Ŭ ����
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

-- ������ �븮�̸鼭 �ƽþ� ������ �ٹ��ϴ� ���� ��ȸ
-- ���(EMPLOYEE.EMP_ID), �̸�(EMPLOYEE.EMP_NAME), ���޸�(JOB.JOB_NAME), �μ���(DEPARTMENT.DEPT_TITLE),
-- �ٹ�������(LOCATION.LOCAL_NAME), �޿�(EMPLOYEE.SALARY)�� ��ȸ�Ͻÿ�.
-- (�ش� �÷��� ã��, �ش� �÷��� ���� ���̺���� ã��, ���̺���� � ������ ���� �ؾ��ϴ��� ����ϰ� SQL���� �ۼ�����)
-- ANSI ǥ��
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
 WHERE B.JOB_NAME = '�븮'
   AND D.LOCAL_NAME LIKE 'ASIA%';
 
-- ����Ŭ ����
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
   AND B.JOB_NAME = '�븮'
   AND D.LOCAL_NAME LIKE 'ASIA%';

--------------------------------------------------------------------------------------
-- JOIN ��������

-- 1. 2020�� 12�� 25���� ���� �������� ��ȸ�Ͻÿ�.(���� �ƴ�)
SELECT 
       TO_CHAR(TO_DATE('20201225', 'YYYYMMDD'), 'DAY')
  FROM DUAL;

-- 2. �ֹι�ȣ�� 70��� ���̸鼭 ������ �����̰�, 
--    ���� ������ �������� �����, �ֹι�ȣ, �μ���, ���޸��� ��ȸ�Ͻÿ�.
-- ANSI ǥ��
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
   AND A.EMP_NAME LIKE '��%';

-- ����Ŭ ����
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
   AND A.EMP_NAME LIKE '��%';

-- 3. ���� ���̰� ���� ������ ���, �����, 
--    ����, �μ���, ���޸��� ��ȸ�Ͻÿ�.
-- ANSI ǥ��
SELECT 
       A.EMP_ID
     , A.EMP_NAME
     , FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(A.EMP_NO, 1, 2), 'RR')) / 12) + 1 ����
     , B.DEPT_TITLE
     , C.JOB_NAME
  FROM EMPLOYEE A
  LEFT JOIN DEPARTMENT B ON(A.DEPT_CODE = B.DEPT_ID)
  LEFT JOIN JOB C ON(A.JOB_CODE = C.JOB_CODE)
 WHERE FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(A.EMP_NO, 1, 2), 'RR')) / 12) + 1 = (SELECT MIN(FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(D.EMP_NO, 1, 2), 'RR')) / 12) + 1)
                                                                                          FROM EMPLOYEE D
                                                                                        );
              
-- ORACLE ����
SELECT 
       A.EMP_ID
     , A.EMP_NAME
     , FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(A.EMP_NO, 1, 2), 'RR')) / 12) + 1 ����
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

-- 4. �̸��� '��'�ڰ� ���� ��������
-- ���, �����, �μ����� ��ȸ�Ͻÿ�.
-- ANSI ǥ��
SELECT 
       A.EMP_ID
     , A.EMP_NAME
     , B.JOB_NAME
  FROM EMPLOYEE A
  JOIN JOB B ON(A.JOB_CODE = B.JOB_CODE)
 WHERE A.EMP_NAME LIKE '%��%';

-- ����Ŭ ����
SELECT 
       A.EMP_ID
     , A.EMP_NAME
     , B.JOB_NAME
  FROM EMPLOYEE A
     , JOB B
 WHERE A.JOB_CODE = B.JOB_CODE
   AND A.EMP_NAME LIKE '%��%';

-- 5. �ؿܿ������� �ٹ��ϴ� �����, 
--    ���޸�, �μ��ڵ�, �μ����� ��ȸ�Ͻÿ�.
-- ANSIǥ��
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , A.DEPT_CODE
     , C.DEPT_TITLE
  FROM EMPLOYEE A
  JOIN JOB B ON(A.JOB_CODE = B.JOB_CODE)
  JOIN DEPARTMENT C ON(A.DEPT_CODE = C.DEPT_ID)
 WHERE C.DEPT_ID IN('D5', 'D6');

-- ����Ŭ ����
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

-- 6. ���ʽ�����Ʈ�� �޴� �������� �����, 
--    ���ʽ�����Ʈ, �μ���, �ٹ��������� ��ȸ�Ͻÿ�.
-- ANSIǥ��
SELECT 
       A.EMP_NAME
     , A.BONUS
     , B.DEPT_TITLE
     , C.LOCAL_NAME
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON(A.DEPT_CODE = B.DEPT_ID)
  JOIN LOCATION C ON(B.LOCATION_ID = C.LOCAL_CODE)
 WHERE A.BONUS IS NOT NULL;

-- ����Ŭ ����
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

-- 7. �μ��ڵ尡 D2�� �������� �����, 
--    ���޸�, �μ���, �ٹ��������� ��ȸ�Ͻÿ�.
-- ANSI ǥ��
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

-- ����Ŭ ����
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

-- 8. ���� �޿� ����� �ּұ޿�(MIN_SAL)�� �ʰ��Ͽ� �޿��� �޴� ��������
--    �����, ���޸�, �޿�, ���ʽ����� ������ ��ȸ�Ͻÿ�.
--    ������ ���ʽ�����Ʈ�� �����Ͻÿ�.
-- ANSI ǥ��
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , A.SALARY
     , A.SALARY * 12 + A.SALARY * NVL(A.BONUS, 0)
  FROM EMPLOYEE A
  JOIN JOB B ON(A.JOB_CODE = B.JOB_CODE)
  JOIN SAL_GRADE C ON(A.SAL_LEVEL = C.SAL_LEVEL)
 WHERE A.SALARY > C.MIN_SAL;

-- ����Ŭ ����
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

-- 9. �ѱ�(KO)�� �Ϻ�(JP)�� �ٹ��ϴ� �������� 
--    �����, �μ���, ������, �������� ��ȸ�Ͻÿ�.
-- ANSI ǥ��
SELECT 
       A.EMP_NAME
     , B.DEPT_TITLE
     , C.LOCAL_NAME
     , D.NATIONAL_NAME
  FROM EMPLOYEE A
  JOIN DEPARTMENT B ON(A.DEPT_CODE = B.DEPT_ID)
  JOIN LOCATION C ON(B.LOCATION_ID = C.LOCAL_CODE)
  JOIN NATIONAL D ON(C.NATIONAL_CODE = D.NATIONAL_CODE)
 WHERE D.NATIONAL_NAME IN('�ѱ�', '�Ϻ�');

-- ����Ŭ ����
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
   AND D.NATIONAL_NAME IN('�ѱ�', '�Ϻ�');

-- 10. ���� �μ��� �ٹ��ϴ� �������� �����, �μ��ڵ�, 
--     �����̸��� ��ȸ�Ͻÿ�.self join ���
-- ANSI ǥ��
SELECT 
       A.EMP_NAME �����
     , B.DEPT_CODE �μ��ڵ�
     , B.EMP_NAME �����̸�
  FROM EMPLOYEE A
  JOIN EMPLOYEE B ON(A.DEPT_CODE = B.DEPT_CODE)
 WHERE A.EMP_NAME != B.EMP_NAME
 ORDER BY 1;

-- ����Ŭ ����
SELECT 
       A.EMP_NAME
     , B.DEPT_CODE
     , B.EMP_NAME
  FROM EMPLOYEE A
     , EMPLOYEE B
 WHERE A.DEPT_CODE = B.DEPT_CODE
   AND A.EMP_NAME != B.EMP_NAME
 ORDER BY 1;

-- 11. ���ʽ�����Ʈ�� ���� ������ �߿��� �����ڵ尡 
--     J4�� J7�� �������� �����, ���޸�, �޿��� ��ȸ�Ͻÿ�.
--     ��, join�� IN ����� ��
-- ANSI ǥ��
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , A.SALARY
  FROM EMPLOYEE A
  JOIN JOB B ON(A.JOB_CODE = B.JOB_CODE)
 WHERE NVL(A.BONUS, 0)= 0 
   AND B.JOB_CODE IN('J4', 'J7');

-- ����Ŭ ����
SELECT 
       A.EMP_NAME
     , B.JOB_NAME
     , A.SALARY
  FROM EMPLOYEE A
     , JOB B
 WHERE A.JOB_CODE = B.JOB_CODE
   AND NVL(A.BONUS, 0)= 0 
   AND B.JOB_CODE IN('J4', 'J7');

--12. �������� ������ ����� ������ ���� ��ȸ�Ͻÿ�.(���ξƴ�)
SELECT 
       COUNT(*)
  FROM EMPLOYEE 
 GROUP BY ENT_YN;






 