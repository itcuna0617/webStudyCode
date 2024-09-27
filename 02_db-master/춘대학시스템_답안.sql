-- BASIC SELECT

--1. �а� �̸��� �迭 ǥ��
SELECT
       DEPARTMENT_NAME "�а� ��"
     , CATEGORY �迭
  FROM TB_DEPARTMENT;

--2. �а��� �а� ������ ���
SELECT 
       DEPARTMENT_NAME || '�� ������ ' || CAPACITY || '�� �Դϴ�.'
  FROM TB_DEPARTMENT;

--3. ������а� ���л�
SELECT
       STUDENT_NAME
  FROM TB_STUDENT 
  JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
 WHERE DEPARTMENT_NAME = '������а�'
   AND ABSENCE_YN = 'Y'
   AND STUDENT_SSN LIKE '_______2%';

--4. ��⿬ü�� ���
SELECT 
       STUDENT_NAME
  FROM TB_STUDENT
 WHERE STUDENT_NO IN ('A513079', 'A513090', 'A513091', 'A513110', 'A513119')
 ORDER BY STUDENT_NAME DESC;

--5. ���������� 20�� �̻� 30�� ������ �а� �̸��� �迭
SELECT
       DEPARTMENT_NAME
     , CATEGORY
  FROM TB_DEPARTMENT
 WHERE CAPACITY BETWEEN 20 AND 30;

--6. �������̸�
SELECT 
       PROFESSOR_NAME
  FROM TB_PROFESSOR
 WHERE DEPARTMENT_NO IS NULL;

--7.�а��� �������� ���� �л� ���� ���
SELECT 
       *
  FROM TB_STUDENT
 WHERE DEPARTMENT_NO IS NULL;

--8. ���������� �����ϴ� ���� ��ȣ ��ȸ
SELECT 
       CLASS_NO
  FROM TB_CLASS
 WHERE PREATTENDING_CLASS_NO IS NOT NULL;

--9. � �迭�� �����ϴ��� ��ȸ
SELECT 
       DISTINCT CATEGORY
  FROM TB_DEPARTMENT
 ORDER BY CATEGORY;

--10. 02�й� ���� ������ ����
SELECT 
       STUDENT_NO
     , STUDENT_NAME
     , STUDENT_SSN
  FROM TB_STUDENT
 WHERE ABSENCE_YN = 'N'
   AND ENTRANCE_DATE BETWEEN '02/01/01' AND '02/12/31'
   AND STUDENT_ADDRESS LIKE '%����%';


-- SELECT - �Լ�
--1. ������а� �л��� ���г⵵�� ������ : �й�, �̸�, ���г⵵
SELECT 
       STUDENT_NO �й�
     , STUDENT_NAME �̸�
     , TO_CHAR(ENTRANCE_DATE, 'YYYY-MM-DD') ���г⵵
  FROM TB_STUDENT
 WHERE DEPARTMENT_NO = 002
 ORDER BY ENTRANCE_DATE;

--2. �̸��� �� ���ڰ� �ƴ� ������ �̸��� �ֹι�ȣ�� ���
SELECT 
       PROFESSOR_NAME
     , PROFESSOR_SSN
  FROM TB_PROFESSOR
 WHERE LENGTH(PROFESSOR_NAME) <> 3;

--3. ���� ���� �̸� ��� (���� ���������, ���̰� ������ �����ټ�����)
SELECT 
       PROFESSOR_NAME
  FROM TB_PROFESSOR
 WHERE SUBSTR(PROFESSOR_SSN, 8, 1) = 1 
 ORDER BY SUBSTR(PROFESSOR_SSN, 1, 2) DESC
        , PROFESSOR_NAME ASC;

--4. �������� �̸� �� ���� ������ �̸��� ���
SELECT 
       SUBSTR(PROFESSOR_NAME, 2, 2) �̸�
  FROM TB_PROFESSOR;

--5. ����� ������ �й��� �̸� ǥ��
SELECT 
       STUDENT_NO
     , STUDENT_NAME
  FROM TB_STUDENT
 WHERE TO_NUMBER(EXTRACT(YEAR FROM ENTRANCE_DATE)) - TO_NUMBER(EXTRACT(YEAR FROM TO_DATE(SUBSTR(STUDENT_SSN, 1, 6), 'RR/MM/DD'))) > 19
 ORDER BY STUDENT_NO;

--6. 2020�� ũ���������� ��������?
SELECT 
       TO_CHAR(TO_DATE('20/12/25'), 'DAY')
  FROM DUAL;

--7. TO_DATE
SELECT 
       TO_DATE('99/10/11', 'YY/MM/DD')
     , TO_DATE('49/10/11', 'YY/MM/DD')
     , TO_DATE('99/10/11', 'RR/MM/DD')
     , TO_DATE('49/10/11', 'RR/MM/DD')
  FROM DUAL;

--8. 2000�� ���� ������ �й�
SELECT 
       STUDENT_NO
     , STUDENT_NAME
  FROM TB_STUDENT
 WHERE ENTRANCE_DATE < '00/01/01'
 ORDER BY STUDENT_NO;

--9. ���� ���ϱ�
SELECT 
       ROUND(AVG(POINT),1)
  FROM TB_GRADE
 WHERE STUDENT_NO = 'A517178';

--10. �а��� �л���
SELECT 
       DISTINCT DEPARTMENT_NO �а���ȣ
     , COUNT(DEPARTMENT_NO) "�л���(��)"
  FROM TB_STUDENT
 GROUP BY DEPARTMENT_NO
 ORDER BY DEPARTMENT_NO;

--11. �������� �������� ���� �л�
SELECT 
       COUNT(*)
  FROM TB_STUDENT
 WHERE COACH_PROFESSOR_NO IS NULL;

--12. �⵵�� ����
SELECT 
       DISTINCT(SUBSTR(TERM_NO, 1, 4)) �⵵, ROUND(AVG(POINT),1) "�⵵ �� ����"
  FROM TB_GRADE
 WHERE STUDENT_NO = 'A112113'
 GROUP BY SUBSTR(TERM_NO, 1, 4)
 ORDER BY SUBSTR(TERM_NO, 1, 4);

--13. �а��� ���л�
SELECT 
       DEPARTMENT_NO �а��ڵ��
     , COUNT(DECODE(ABSENCE_YN, 'Y', 1)) ���л���
  FROM TB_STUDENT
 GROUP BY DEPARTMENT_NO 
 ORDER BY DEPARTMENT_NO;

--14. ��������
SELECT 
       STUDENT_NAME �����̸�
     , COUNT(*) "������ ��"
  FROM TB_STUDENT
 GROUP BY STUDENT_NAME 
 HAVING COUNT(STUDENT_NAME) > 1
 ORDER BY STUDENT_NAME;

--15. �⵵ �б⺰ ������ �⵵�� ���� ����, ������
SELECT 
       NVL(SUBSTR(TERM_NO, 1, 4),' ') �⵵
     , NVL(SUBSTR(TERM_NO, 5, 2), ' ') �б�
     , ROUND(AVG(POINT), 1) ����
  FROM TB_GRADE
 WHERE STUDENT_NO = 'A112113'
 GROUP BY ROLLUP(SUBSTR(TERM_NO, 1, 4), SUBSTR(TERM_NO, 5, 2))
 ORDER BY SUBSTR(TERM_NO, 1, 4);

-- SELECT - OPTION


--1. �л��̸��� �ּ����� ǥ���Ͻÿ�. ��, ��� ����� "�л� �̸�", "�ּ���"�� �ϰ�,
--������ �̸����� �������� ǥ���ϵ��� �Ѵ�.
SELECT 
       STUDENT_NAME "�л� �̸�"
     , STUDENT_ADDRESS "�ּ���"
  FROM TB_STUDENT
 ORDER BY STUDENT_NAME;


--2. �������� �л����� �̸��� �ֹι�ȣ�� ���̰� ���� ������ ȭ�鿡 ����Ͻÿ�.
SELECT 
       STUDENT_NAME
     , STUDENT_SSN
  FROM TB_STUDENT
 WHERE ABSENCE_YN = 'Y'
 ORDER BY STUDENT_SSN DESC;


--3. �ּ����� �������� ��⵵�� �л��� �� 1900 ��� �й��� ���� �л����� �̸��� �й�,
--�ּҸ� �̸��� ������������ ȭ�鿡 ����Ͻÿ�. ��, ���������� "�л��̸�","�й�",
--"������ �ּ�" �� ��µǵ��� �Ѵ�.
SELECT 
       STUDENT_NAME �л��̸�
     , STUDENT_NO �й�
     , STUDENT_ADDRESS "������ �ּ�"
  FROM TB_STUDENT
 WHERE STUDENT_NO LIKE '9%'
   AND SUBSTR(STUDENT_ADDRESS, 1, 3) = '��⵵'
    OR SUBSTR(STUDENT_ADDRESS, 1, 3) = '������'
 ORDER BY SUBSTR(STUDENT_ADDRESS, 1, 2), STUDENT_NAME;


--4. ���� ���а� ���� �� ���� ���̰� ���� ������� �̸��� Ȯ���� �� �ִ� SQL ������
--�ۼ��Ͻÿ�. (���а��� '�а��ڵ�'�� �а� ���̺�(TB_DEPARTMENT)�� ��ȸ�ؼ� ã��
--������ ����)
SELECT 
       PROFESSOR_NAME
     , PROFESSOR_SSN
  FROM TB_PROFESSOR
 WHERE DEPARTMENT_NO = '005'
 ORDER BY PROFESSOR_SSN;


----5. 2004 �� 2 �б⿡ 'C3118100' ������ ������ �л����� ������ ��ȸ�Ϸ��� �Ѵ�. ������
--���� �л����� ǥ���ϰ�, ������ ������ �й��� ���� �л����� ǥ���ϴ� ������
--�ۼ��غ��ÿ�.
SELECT 
       STUDENT_NO
     , TO_CHAR(ROUND(POINT, 2), '90.00')
  FROM TB_GRADE
 WHERE TERM_NO = '200402'
   AND CLASS_NO = 'C3118100'
 ORDER BY POINT DESC
        , STUDENT_NO;


--6. �л� ��ȣ, �л� �̸�, �а� �̸��� �̸� ������ ������ �����Ͽ� ����ϴ� SQL ����
--�ۼ��Ͻÿ�.
SELECT 
       STUDENT_NO
     , STUDENT_NAME
     , DEPARTMENT_NAME
  FROM TB_STUDENT
  JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
 ORDER BY STUDENT_NAME;


--7. �� ������б��� ���� �̸��� ������ �а� �̸��� �а� �̸� ������ ������ ����ϴ�
--SQL ������ �ۼ��Ͻÿ�.
-- ANSI ǥ��
SELECT 
       CLASS_NAME
     , DEPARTMENT_NAME
  FROM TB_CLASS
  JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
 ORDER BY 2;

--����Ŭ ����
SELECT 
       CLASS_NAME
     , DEPARTMENT_NAME
  FROM TB_CLASS C
     , TB_DEPARTMENT D 
 WHERE C.DEPARTMENT_NO = D.DEPARTMENT_NO
 ORDER BY 2;

--8. ���� ���� �̸��� ã������ �Ѵ�. ���� �̸��� ���� �̸��� ����ϴ� SQL ����
--�ۼ��Ͻÿ�.
-- ANSI ǥ��
SELECT 
       CLASS_NAME
     , PROFESSOR_NAME
  FROM TB_CLASS_PROFESSOR
  JOIN TB_CLASS USING (CLASS_NO)
  JOIN TB_PROFESSOR USING (PROFESSOR_NO);

-- ����Ŭ ����
SELECT 
       CLASS_NAME
     , PROFESSOR_NAME
  FROM TB_CLASS_PROFESSOR CP
     , TB_CLASS C
     , TB_PROFESSOR P
 WHERE CP.CLASS_NO = C.CLASS_NO
   AND CP.PROFESSOR_NO = P.PROFESSOR_NO;

--9. 8 ���� ��� �� ���ι���ȸ�� �迭�� ���� ������ ���� �̸��� ã������ �Ѵ�. �̿�
--�ش��ϴ� ���� �̸��� ���� �̸��� ����ϴ� SQL ���� �ۼ��Ͻÿ�.
--ANSI ǥ��
SELECT 
       CLASS_NAME
     , PROFESSOR_NAME
  FROM TB_CLASS_PROFESSOR CP
  JOIN TB_CLASS C ON (C.CLASS_NO = CP.CLASS_NO)
  JOIN TB_PROFESSOR P ON (P.PROFESSOR_NO = CP.PROFESSOR_NO)
  JOIN TB_DEPARTMENT D ON (D.DEPARTMENT_NO = P.DEPARTMENT_NO)
 WHERE CATEGORY LIKE '�ι�%';

--����Ŭ ����
SELECT 
       CLASS_NAME
     , PROFESSOR_NAME
  FROM TB_CLASS_PROFESSOR CP, TB_CLASS C, TB_PROFESSOR P, TB_DEPARTMENT D
 WHERE C.CLASS_NO = CP.CLASS_NO
  AND P.PROFESSOR_NO = CP.PROFESSOR_NO
  AND D.DEPARTMENT_NO = P.DEPARTMENT_NO
  AND CATEGORY LIKE '�ι�%';

--10. �������а��� �л����� ������ ���Ϸ��� �Ѵ�. �����а� �л����� "�й�", "�л� �̸�",
--"��ü ����"�� ������ ���� ����(������ �����ϸ� �й� ����)�� ����ϴ� SQL ������
--�ۼ��Ͻÿ�. (��, ������ �Ҽ��� 1 �ڸ������� �ݿø��Ͽ� ǥ���Ѵ�.)
-- ANSI ǥ��
SELECT 
       STUDENT_NO
     , STUDENT_NAME
     , ROUND(AVG(POINT), 1)
  FROM TB_STUDENT
  JOIN TB_GRADE USING (STUDENT_NO)
  JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
 WHERE DEPARTMENT_NAME = '�����а�'
 GROUP BY STUDENT_NO
        , STUDENT_NAME
 ORDER BY 3 DESC, 1;

-- ����Ŭ ����
SELECT 
       S.STUDENT_NO
     , S.STUDENT_NAME
     , ROUND(AVG(G.POINT), 1)
  FROM TB_STUDENT S
     , TB_GRADE G
     , TB_DEPARTMENT D
 WHERE S.STUDENT_NO = G.STUDENT_NO
   AND S.DEPARTMENT_NO = D.DEPARTMENT_NO
   AND D.DEPARTMENT_NAME = '�����а�'
 GROUP BY S.STUDENT_NO, S.STUDENT_NAME
 ORDER BY 3 DESC, 1;

--11. �й��� A313047 �� �л��� �б��� ������ ���� �ʴ�. ���� �������� ������ �����ϱ�
--���� �а� �̸�, �л� �̸��� ���� ���� �̸��� �ʿ��ϴ�. �̶� ����� SQL ����
--�ۼ��Ͻÿ�. ��, �������� �а��̸�, �л��̸�, ���������̸�����
--��µǵ��� �Ѵ�.
-- ANSI ǥ��
SELECT 
       DEPARTMENT_NAME �а��̸�
     , STUDENT_NAME �л��̸�
     , PROFESSOR_NAME ���������̸�
  FROM TB_STUDENT S
  JOIN TB_DEPARTMENT D ON (S.DEPARTMENT_NO = D.DEPARTMENT_NO)
  JOIN TB_PROFESSOR P ON (S.COACH_PROFESSOR_NO = P.PROFESSOR_NO)
 WHERE STUDENT_NO = 'A313047';

-- ����Ŭ ����
SELECT 
       DEPARTMENT_NAME �а��̸�
     , STUDENT_NAME �л��̸�
     , PROFESSOR_NAME ���������̸�
  FROM TB_STUDENT S
     , TB_DEPARTMENT D
     , TB_PROFESSOR P
  WHERE S.DEPARTMENT_NO = D.DEPARTMENT_NO
    AND S.COACH_PROFESSOR_NO = P.PROFESSOR_NO
    AND STUDENT_NO = 'A313047';


--12. 2007 �⵵�� '�ΰ������' ������ ������ �л��� ã�� �л��̸��� �����б⸦ �̸�
--������ ������ ǥ���ϴ� SQL ������ �ۼ��Ͻÿ�.
--ANSI ǥ��
SELECT 
       STUDENT_NAME
     , TERM_NO
  FROM TB_STUDENT S
  JOIN TB_GRADE G ON (S.STUDENT_NO = G.STUDENT_NO)
  JOIN TB_CLASS C ON (G.CLASS_NO = C.CLASS_NO)
 WHERE TERM_NO LIKE '2007%'
   AND C.CLASS_NAME LIKE '�ΰ�����%';

--����Ŭ ����
SELECT 
       STUDENT_NAME
     , TERM_NO
  FROM TB_STUDENT S, TB_GRADE G, TB_CLASS C
 WHERE S.STUDENT_NO = G.STUDENT_NO
   AND G.CLASS_NO = C.CLASS_NO
   AND TERM_NO LIKE '2007%'
   AND C.CLASS_NAME LIKE '�ΰ�����%';

--13. ��ü�� �迭 ���� �� ���� ��米���� �� �� �������� ���� ������ ã�� �� ����
--�̸��� �а� �̸��� ����ϴ� SQL ������ �ۼ��Ͻÿ�.
-- ANSI ǥ��
SELECT 
       CLASS_NAME
     , DEPARTMENT_NAME
  FROM TB_CLASS_PROFESSOR CP
  RIGHT JOIN TB_CLASS C ON (CP.CLASS_NO = C.CLASS_NO)
  JOIN TB_DEPARTMENT D ON (C.DEPARTMENT_NO = D.DEPARTMENT_NO)
 WHERE CP.CLASS_NO IS NULL
   AND CATEGORY = '��ü��';

-- ����Ŭ ����
SELECT 
       CLASS_NAME
     , DEPARTMENT_NAME
  FROM TB_CLASS_PROFESSOR CP
     , TB_CLASS C
     , TB_DEPARTMENT D
 WHERE CP.CLASS_NO(+) = C.CLASS_NO
   AND C.DEPARTMENT_NO = D.DEPARTMENT_NO
   AND CP.CLASS_NO IS NULL
   AND CATEGORY = '��ü��';

----14. �� ������б� ���ݾƾ��а� �л����� ���������� �Խ��ϰ��� �Ѵ�. 
--�л��̸��� �������� �̸��� ã�� ���� ���� ������ ���� �л��� ��� "�������� 
--���������� ǥ���ϵ��� �ϴ� SQL ���� �ۼ��Ͻÿ�. ��, �������� �л��̸�, 
--���������� ǥ���ϸ� ���й� �л��� ���� ǥ�õǵ��� �Ѵ�.
-- ANSI ǥ��
SELECT 
       STUDENT_NAME �л��̸�
     , NVL(P.PROFESSOR_NAME,'�������� ������') ��������
  FROM TB_STUDENT S
  LEFT JOIN TB_PROFESSOR P ON (S.COACH_PROFESSOR_NO = P.PROFESSOR_NO)
  JOIN TB_DEPARTMENT D ON (S.DEPARTMENT_NO = D.DEPARTMENT_NO)
 WHERE DEPARTMENT_NAME = '���ݾƾ��а�';

-- ����Ŭ ����
SELECT 
       STUDENT_NAME �л��̸�
     , NVL(P.PROFESSOR_NAME,'�������� ������') ��������
  FROM TB_STUDENT S
     , TB_PROFESSOR P
     , TB_DEPARTMENT D
 WHERE S.COACH_PROFESSOR_NO = P.PROFESSOR_NO(+)
   AND S.DEPARTMENT_NO = D.DEPARTMENT_NO
   AND DEPARTMENT_NAME = '���ݾƾ��а�';

--15. ���л��� �ƴ� �л� �� ������ 4.0 �̻��� �л��� ã�� �� �л��� �й�, 
--�̸�, �а� �̸�, ������ ����ϴ� SQL ���� �ۼ��Ͻÿ�.
--ANSI ǥ��
SELECT 
       S.STUDENT_NO �й�
     , S.STUDENT_NAME �̸�
     , D.DEPARTMENT_NAME "�а� �̸�"
     , AVG(G.POINT) ����
  FROM TB_STUDENT S
  JOIN TB_DEPARTMENT D ON (S.DEPARTMENT_NO = D.DEPARTMENT_NO)
  JOIN TB_GRADE G ON (S.STUDENT_NO = G.STUDENT_NO)
 WHERE S.ABSENCE_YN = 'N'
 GROUP BY S.STUDENT_NO, S.STUDENT_NAME, D.DEPARTMENT_NAME
HAVING AVG(G.POINT) >= 4.0 
 ORDER BY S.STUDENT_NO;

-- ����Ŭ ����
SELECT 
       S.STUDENT_NO �й�
     , S.STUDENT_NAME �̸�
     , D.DEPARTMENT_NAME "�а� �̸�"
     , AVG(G.POINT) ����
  FROM TB_STUDENT S
     , TB_DEPARTMENT D
     , TB_GRADE G
 WHERE S.DEPARTMENT_NO = D.DEPARTMENT_NO
   AND S.STUDENT_NO = G.STUDENT_NO
   AND S.ABSENCE_YN = 'N'
 GROUP BY S.STUDENT_NO, S.STUDENT_NAME, D.DEPARTMENT_NAME
HAVING AVG(G.POINT) >= 4.0 
 ORDER BY S.STUDENT_NO;


-- 16.ȯ�������а� ����������� ���� �� ������ �ľ��� �� �ִ� 
-- SQL ���� �ۼ��Ͻÿ�.
-- ANSIǥ��
SELECT  
       CLASS_NO
     , CLASS_NAME
     , TRUNC(AVG(POINT), 8)AS "AVG(POINT)"
  FROM TB_DEPARTMENT
  JOIN TB_CLASS USING (DEPARTMENT_NO)
  JOIN TB_GRADE USING (CLASS_NO)
 WHERE DEPARTMENT_NAME = 'ȯ�������а�'
   AND CLASS_TYPE LIKE '%����%'
 GROUP BY CLASS_NO, CLASS_NAME
 ORDER BY 1;
-- ����Ŭ����
SELECT  
       C.CLASS_NO
     , C.CLASS_NAME
     , TRUNC(AVG(G.POINT), 8)AS "AVG(POINT)"
  FROM TB_DEPARTMENT D
     , TB_CLASS C
     , TB_GRADE G
 WHERE D.DEPARTMENT_NO = C.DEPARTMENT_NO
   AND C.CLASS_NO = G.CLASS_NO
   AND D.DEPARTMENT_NAME = 'ȯ�������а�'
   AND C.CLASS_TYPE LIKE '%����%'
 GROUP BY C.CLASS_NO, C.CLASS_NAME
 ORDER BY 1;


-- 17. �� ������б��� �ٴϰ� �ִ� �ְ��� �л��� ���� �� �л����� 
-- �̸��� �ּҸ� ����ϴ� SQL ���� �ۼ��Ͻÿ�.

SELECT DEPARTMENT_NO
  FROM TB_STUDENT
 WHERE STUDENT_NAME = '�ְ���'; -- '038'
 
SELECT 
       STUDENT_NAME
     , STUDENT_ADDRESS
  FROM TB_STUDENT
 WHERE DEPARTMENT_NO = '038';

SELECT STUDENT_NAME,
       STUDENT_ADDRESS
  FROM TB_STUDENT
 WHERE DEPARTMENT_NO IN (SELECT DEPARTMENT_NO
                         FROM   TB_STUDENT
                         WHERE  STUDENT_NAME = '�ְ���'
                        )
;                                 

-- 18. ������а����� �� ������ ���� ���� �л��� �̸��� �й��� 
-- ǥ���ϴ� SQL ���� �ۼ��Ͻÿ�.
SELECT 
       STUDENT_NAME
     , STUDENT_NO
     , AVG(POINT)
     , RANK () OVER (ORDER BY AVG(POINT) DESC) AS RANK
  FROM TB_DEPARTMENT
  JOIN TB_CLASS USING (DEPARTMENT_NO)
  JOIN TB_GRADE USING (CLASS_NO)
  JOIN TB_STUDENT USING (STUDENT_NO)
 WHERE DEPARTMENT_NAME = '������а�'
 GROUP BY STUDENT_NAME
        , STUDENT_NO;

-- ANSIǥ��
SELECT  
       STUDENT_NO
     , STUDENT_NAME
  FROM (SELECT 
               STUDENT_NAME
             , STUDENT_NO
             , AVG(POINT)
             , RANK () OVER (ORDER BY AVG(POINT) DESC) AS RANK
          FROM TB_DEPARTMENT
          JOIN TB_CLASS USING (DEPARTMENT_NO)
          JOIN TB_GRADE USING (CLASS_NO)
          JOIN TB_STUDENT USING (STUDENT_NO)
         WHERE DEPARTMENT_NAME = '������а�'
         GROUP BY STUDENT_NAME
                , STUDENT_NO
        )
 WHERE RANK = '1';

-- ����Ŭ ����
SELECT 
       DEPARTMENT_NAME AS "�迭 �а���"
     , ROUND(AVG(POINT), 1) AS "��������"
  FROM TB_DEPARTMENT D
     , TB_CLASS C
     , TB_GRADE G
 WHERE D.DEPARTMENT_NO = C.DEPARTMENT_NO
   AND C.CLASS_NO = G.CLASS_NO
   AND CATEGORY IN (SELECT CATEGORY
                      FROM TB_DEPARTMENT
                     WHERE DEPARTMENT_NAME = 'ȯ�������а�'
                    )
 GROUP BY DEPARTMENT_NAME
 ORDER BY 1;

-- 19.   �� ������б��� "ȯ�������а�"�� ���� ���� �迭 �а����� 
-- �а� �� �������� ������ �ľ��ϱ� ���� ������ SQL ���� ã�Ƴ��ÿ�.
-- ��, �������� "�迭 �а���", "��������"���� ǥ�õǵ��� �ϰ�,
-- ������ �Ҽ��� �� �ڸ������� �ݿø��Ͽ� ǥ�õǵ��� �Ѵ�
-- ANSIǥ��
SELECT CATEGORY
  FROM TB_DEPARTMENT
 WHERE DEPARTMENT_NAME = 'ȯ�������а�'; -- '�ڿ�����'
 
SELECT 
       DEPARTMENT_NAME AS "�迭 �а���"
     , ROUND(AVG(POINT), 1) AS "��������"
  FROM TB_DEPARTMENT 
  JOIN TB_CLASS C USING (DEPARTMENT_NO)
  JOIN TB_GRADE USING (CLASS_NO)
 WHERE CATEGORY = '�ڿ�����'
 GROUP BY DEPARTMENT_NAME
 ORDER BY 1;

SELECT 
       DEPARTMENT_NAME AS "�迭 �а���"
     , ROUND(AVG(POINT), 1) AS "��������"
  FROM TB_DEPARTMENT 
  JOIN TB_CLASS C USING (DEPARTMENT_NO)
  JOIN TB_GRADE USING (CLASS_NO)
 WHERE CATEGORY IN (SELECT CATEGORY
                      FROM TB_DEPARTMENT
                     WHERE DEPARTMENT_NAME = 'ȯ�������а�'
                   )
 GROUP BY DEPARTMENT_NAME
 ORDER BY 1;

-- ����Ŭ����
SELECT
       DEPARTMENT_NAME AS "�迭 �а���"
     , ROUND(AVG(POINT), 1) AS "��������"
  FROM TB_DEPARTMENT D
     , TB_CLASS C
     , TB_GRADE G
 WHERE D.DEPARTMENT_NO = C.DEPARTMENT_NO
   AND C.CLASS_NO = G.CLASS_NO
   AND CATEGORY IN (SELECT CATEGORY
                      FROM TB_DEPARTMENT
                     WHERE DEPARTMENT_NAME = 'ȯ�������а�'
                   )
GROUP BY DEPARTMENT_NAME
ORDER BY 1;

-- DDL ����
-- 1.
CREATE TABLE TB_CATEGORY (
  "NAME," VARCHAR2(10),
  "USE_YN," CHAR(1) DEFAULT 1
);

--DROP TABLE TB_CATEGORY;

--2.
CREATE TABLE TB_CLASS_TYPE (
  "NO," VARCHAR2(5) PRIMARY KEY,
  "NAME ," VARCHAR2(10)
);

--DROP TABLE TB_CLASS_TYPE;

--3.
ALTER TABLE TB_CATEGORY
ADD PRIMARY KEY ("NAME,");

--4.
ALTER TABLE TB_CATEGORY
MODIFY "NAME," NOT NULL;

--5.
ALTER TABLE TB_CATEGORY
MODIFY "NAME," VARCHAR2(20);

ALTER TABLE TB_CLASS_TYPE
MODIFY ("NAME ," VARCHAR2(20), "NO," VARCHAR2(10));


--6. 
ALTER TABLE TB_CATEGORY
RENAME COLUMN "NAME," TO CATEGORY_NAME;

ALTER TABLE TB_CLASS_TYPE
RENAME COLUMN "NAME ," TO CLASS_TYPE_NAME;
ALTER TABLE TB_CLASS_TYPE
RENAME COLUMN "NO," TO CLASS_TYPE_NO;

--7.
SELECT TABLE_NAME
     , CONSTRAINT_NAME 
     , CONSTRAINT_TYPE
     , COLUMN_NAME
  FROM USER_CONSTRAINTS
  JOIN USER_CONS_COLUMNS USING (CONSTRAINT_NAME, TABLE_NAME)
 WHERE TABLE_NAME = 'TB_CATEGORY'
    OR TABLE_NAME = 'TB_CLASS_TYPE';

ALTER TABLE TB_CATEGORY
RENAME CONSTRAINTS SYS_C007359 TO PK_CATEGORY_NAME;

ALTER TABLE TB_CLASS_TYPE
RENAME CONSTRAINTS SYS_C007361 TO PK_CLASS_TYPE_NAME;


--8.
INSERT INTO TB_CATEGORY VALUES ('����','Y');
INSERT INTO TB_CATEGORY VALUES ('�ڿ�����','Y');
INSERT INTO TB_CATEGORY VALUES ('����','Y');
INSERT INTO TB_CATEGORY VALUES ('��ü��','Y');
INSERT INTO TB_CATEGORY VALUES ('�ι���ȸ','Y');
COMMIT; 

--9.
ALTER TABLE TB_DEPARTMENT
ADD CONSTRAINT FK_DEPARTMENT_CATEGORY FOREIGN KEY (CATEGORY) REFERENCES TB_CATEGORY (CATEGORY_NAME);

SELECT 
       *
  FROM USER_CONS_COLUMNS
  JOIN USER_CONSTRAINTS USING (CONSTRAINT_NAME, TABLE_NAME)
 WHERE TABLE_NAME = 'USER_CONS_COLUMNS';

--10.
CREATE OR REPLACE VIEW VW_�л��Ϲ�����
AS SELECT STUDENT_NO, STUDENT_NAME, STUDENT_ADDRESS
     FROM TB_STUDENT;

--11.
CREATE OR REPLACE VIEW VW_�������
AS SELECT STUDENT_NAME, DEPARTMENT_NAME, PROFESSOR_NAME
     FROM TB_STUDENT
     LEFT JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
     LEFT JOIN TB_PROFESSOR ON (COACH_PROFESSOR_NO = PROFESSOR_NO)
    ORDER BY 2;

SELECT * FROM VW_�������;

-- 12. 
CREATE OR REPLACE VIEW VW_�а����л���
AS
SELECT DEPARTMENT_NAME
     , COUNT(*) "STUDENT_COUNT"
  FROM TB_STUDENT
  LEFT JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
 GROUP BY DEPARTMENT_NAME;

SELECT * FROM VW_�а����л���;

-- 13.
CREATE OR REPLACE VIEW VW_�л��Ϲ�����
AS 
SELECT STUDENT_NO
     , STUDENT_NAME
     , STUDENT_ADDRESS
  FROM TB_STUDENT
  WITH CHECK OPTION;
     
UPDATE VW_�л��Ϲ�����
   SET STUDENT_NAME = '����'
 WHERE STUDENT_NO = 'A213046';

SELECT STUDENT_NO, STUDENT_NAME
  FROM TB_STUDENT
 WHERE STUDENT_NO = 'A213046';

-- 14.
CREATE OR REPLACE VIEW VW_�л��Ϲ�����
AS SELECT STUDENT_NO, STUDENT_NAME, STUDENT_ADDRESS
     FROM TB_STUDENT
     WITH READ ONLY;

-- 15.
SELECT �����ȣ, �����̸�, "������������(��)"
  FROM (SELECT CLASS_NO �����ȣ, CLASS_NAME �����̸�, COUNT(STUDENT_NO) "������������(��)"
          FROM TB_CLASS 
          LEFT JOIN TB_GRADE  USING (CLASS_NO)
         WHERE SUBSTR(TERM_NO, 1, 4) IN ((SELECT �⵵
                                            FROM (SELECT DISTINCT SUBSTR(TERM_NO, 1, 4) �⵵
                                                    FROM TB_GRADE
                                                   ORDER BY 1 DESC)
                                           WHERE ROWNUM <= 5))
         GROUP BY CLASS_NO, CLASS_NAME
         ORDER BY 3 DESC)
 WHERE ROWNUM <= 3;



-- DML ����

-- 1. �������� ���̺�(TB_CLASS_TYPE)�� �Ʒ��� ���� �����͸� �Է��Ͻÿ�.
INSERT INTO TB_CLASS_TYPE VALUES ('01', '�����ʼ�');
INSERT INTO TB_CLASS_TYPE VALUES ('02', '��������');
INSERT INTO TB_CLASS_TYPE VALUES ('03', '�����ʼ�');
INSERT INTO TB_CLASS_TYPE VALUES ('04', '���缱��');
INSERT INTO TB_CLASS_TYPE VALUES ('05', '������');

SELECT * FROM TB_CLASS_TYPE;

ROLLBACK;

-- 2. �� ������б� �л����� ������ ���ԵǾ� �ִ� �л��Ϲ����� ���̺��� ������� �Ѵ�.
-- �Ʒ� ������ �����Ͽ� ������ SQL ���� �ۼ��Ͻÿ�. (���������� �̿��Ͻÿ�)
CREATE TABLE TB_�л��Ϲ����� 
AS SELECT STUDENT_NO
        , STUDENT_NAME
        , STUDENT_ADDRESS
     FROM TB_STUDENT;

SELECT 
       *
  FROM TB_�л��Ϲ�����;

ROLLBACK;

--3. ������а� �л����� �������� ���ԵǾ� �ִ� �а����� ���̺��� ������� �Ѵ�.
--�Ʒ� ������ �����Ͽ� ������ SQL ���� �ۼ��Ͻÿ�. (��Ʈ : ����� �پ���, �ҽŲ�
--�ۼ��Ͻÿ�)
CREATE TABLE TB_������а�
AS SELECT S.STUDENT_NO �й�,
                S.STUDENT_NAME �л��̸�, 
                EXTRACT(YEAR FROM TO_DATE(SUBSTR(S.STUDENT_SSN, 1, 2), 'RRRR')) ����⵵, 
                P.PROFESSOR_NAME �����̸�
      FROM TB_STUDENT S
      JOIN TB_PROFESSOR P ON (PROFESSOR_NO = COACH_PROFESSOR_NO)
      JOIN TB_DEPARTMENT D ON (S.DEPARTMENT_NO = D.DEPARTMENT_NO)
      WHERE D.DEPARTMENT_NAME = '������а�';
    
SELECT * FROM TB_������а�;

--4. �� �а����� ������ 10% ������Ű�� �Ǿ���. �̿� ����� SQL ���� �ۼ��Ͻÿ�. (��,
--�ݿø��� ����Ͽ� �Ҽ��� �ڸ����� ������ �ʵ��� �Ѵ�)
UPDATE TB_DEPARTMENT
   SET CAPACITY = ROUND((CAPACITY * 1.1),0);

SELECT
       DEPARTMENT_NAME
     , CAPACITY
  FROM TB_DEPARTMENT;

ROLLBACK;

--5. �й� A413042 �� �ڰǿ� �л��� �ּҰ� "����� ���α� ���ε� 181-21 "�� ����Ǿ��ٰ�
--�Ѵ�. �ּ����� �����ϱ� ���� ����� SQL ���� �ۼ��Ͻÿ�.
UPDATE TB_STUDENT
   SET STUDENT_ADDRESS = '����� ���α� ���ε� 181-21'
 WHERE STUDENT_NO = 'A413042';

SELECT 
       STUDENT_NO
     , STUDENT_NAME
     , STUDENT_ADDRESS
  FROM TB_STUDENT
 WHERE STUDENT_NO = 'A413042';

ROLLBACK;

--6. �ֹε�Ϲ�ȣ ��ȣ���� ���� �л����� ���̺��� �ֹι�ȣ ���ڸ��� �������� �ʱ��
--�����Ͽ���. �� ������ �ݿ��� ������ SQL ������ �ۼ��Ͻÿ�.
UPDATE TB_STUDENT
   SET STUDENT_SSN = SUBSTR(STUDENT_SSN, 1, 6);

SELECT 
       STUDENT_NO
     , STUDENT_NAME
     , STUDENT_SSN
  FROM TB_STUDENT;

ROLLBACK;

--7. ���а� ����� �л��� 2005 �� 1 �б⿡ �ڽ��� ������ '�Ǻλ�����' ������
--�߸��Ǿ��ٴ� ���� �߰��ϰ�� ������ ��û�Ͽ���. ��� ������ Ȯ�� ���� ��� �ش�
--������ ������ 3.5 �� ����Ű�� �����Ǿ���. ������ SQL ���� �ۼ��Ͻÿ�.
UPDATE TB_GRADE
   SET POINT = '3.5'
 WHERE TERM_NO = '200501'
   AND STUDENT_NO = (SELECT STUDENT_NO
                       FROM TB_STUDENT
                       JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
                      WHERE STUDENT_NAME = '�����'
                        AND DEPARTMENT_NAME = '���а�' )
AND CLASS_NO = (SELECT CLASS_NO
                  FROM TB_CLASS
                 WHERE CLASS_NAME = '�Ǻλ�����');

SELECT DEPARTMENT_NAME, STUDENT_NAME, TERM_NO, CLASS_NAME, POINT
  FROM TB_GRADE G
  JOIN TB_STUDENT USING (STUDENT_NO)
  JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
  JOIN TB_CLASS USING (CLASS_NO)
 WHERE DEPARTMENT_NAME = '���а�'
   AND STUDENT_NAME = '�����'
   AND TERM_NO = '200501'
   AND CLASS_NAME = '�Ǻλ�����';

ROLLBACK;

--8. ���� ���̺�(TB_GRADE) ���� ���л����� �����׸��� �����Ͻÿ�.
DELETE FROM TB_GRADE
 WHERE STUDENT_NO IN (SELECT STUDENT_NO
                        FROM TB_STUDENT
                       WHERE ABSENCE_YN = 'Y');

ROLLBACK;