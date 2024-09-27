-- ������(SEQUENCE)
-- �ڵ� ��ȣ �߻��� ������ �ϴ� ��ü
-- ���������� ���� ���� �ڵ����� ������ ��
 
-- CREATE SEQUENCE ��������
-- [START WITH ����] - ó�� �߻� ��ų ���� �� ����, �⺻ �� 1 
--                    (�� �ɼ��� ����(ALTER)�� �ȵǹǷ� ���� ���� �ٲٰ� ������ SEQUENCE�� DROP �� �ٽ� ������(CREATE) �ڡ�)
-- [INCREMENT BY ����] - ���� ���� ���� ����ġ, �⺻ �� 1
-- [MAXVALUE ���� | NOMAXVALUE] - �߻� ��ų �ִ� �� ����, 10�� 27�� -1���� ����
-- [MINVALUE ���� | NOMINVALUE] - �߻� ��ų �ּ� �� ����, -10�� 26��
-- [CYCLE | NOCYCLE] - �������� �ִ� ������ ������ �Ϸ��ϸ� CYCLE�� START WITH ���� �� Ȥ�� MINVALUE ������
--                     ���ư���, NOCYCLE�� ���� �߻�
-- [CACHE | NOCACHE] - CACHE�� �޸� �󿡼� ������ ���� �����Ͽ� ������ ��� ��Ű�� ���� ��, �⺻ �� 20
 
CREATE SEQUENCE SEQ_EMPID;

--SELECT SEQ_EMPID.CURRVAL FROM DUAL; -- ������ ���� �� ó������ CURRVAL�� ����� �� ����.
SELECT SEQ_EMPID.NEXTVAL FROM DUAL;   -- ������ ���� �Ŀ��� �ݵ�� NEXTVAL�� �ѹ� �� �־�� �ʱ� ������ ���� �ȴ�.
SELECT SEQ_EMPID.CURRVAL FROM DUAL;
SELECT SEQ_EMPID.NEXTVAL FROM DUAL;  
SELECT SEQ_EMPID.CURRVAL FROM DUAL;

SELECT SEQ_EMPID.NEXTVAL FROM DUAL;  
SELECT 'P'|| '-' || EXTRACT(MONTH FROM SYSDATE) || '-' || SEQ_EMPID.NEXTVAL FROM DUAL;  -- PK���� Ȱ���� �� ���� �����ڷ� ���ϴ� ���¸� ������.

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

-- �������� �ɼ� ����(START WITH �ɼ��� ������ �ɼǵ��� ������ �����ϴ�.)
ALTER SEQUENCE SEQ_EMPID
INCREMENT BY 10
MAXVALUE 400
NOCYCLE;

SELECT SEQ_EMPID.CURRVAL FROM DUAL;

-- �ٲ� �ɼ����� ��� ������Ű�� MAX������ ���� �� NOCYCLE�̶� ������ �߻��Ѵ�.
SELECT SEQ_EMPID.NEXTVAL FROM DUAL;

-- �������
-- SELECT������ ��� ����
-- INSERT������ SELECT ���� ��� ����(��������)
-- INSERT������ VALUES ������ ��� ���� -- (������ ��ü�� �� ��Ȳ���� �ַ� ���� �ȴ�.)
-- UPDATE������ SET������ ��� ����
 
-- ��, �Ϲ����� ���������� SELECT�������� ��� �Ұ�
-- VIEW�� SELECT������ ��� �Ұ�
-- DISTINCT Ű���尡 �ִ� SELECT������ ��� �Ұ�
-- GROUP BY, HAVING���� �ִ� SELECT�������� ��� �Ұ�
-- ORDER BY ������ ��� �Ұ�
-- CREATE TABLE, ALTER TABLE�� DEFAULT ������ ��� �Ұ�

-- VALUES�� Ȱ���� INSERT�������� SEQUENCE Ȱ��
SELECT 
       MAX(TO_NUMBER(A.EMP_ID))
  FROM EMPLOYEE A;

CREATE SEQUENCE SEQ_EID
 START WITH 902;   -- ������ PK�� + 1�� �ش��ϴ� ������ ����ϴ� �������� �����.

INSERT
  INTO EMPLOYEE
VALUES
(
  SEQ_EID.NEXTVAL, 'ȫ�浿', '555555-5555555', 'hong_gd@greedy.or.kr', '01012345555'
, 'D2', 'J7', 'S1', 50000000, 0.1
, '200', SYSDATE, NULL, DEFAULT
);







