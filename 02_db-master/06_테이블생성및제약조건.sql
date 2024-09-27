-- DDL(TABLE) �� ��������

-- DDL(DATA DEFINITION LANGUAGE): ������ ���� ���
-- ��ü(OBJECT)�� �����(CREATE), ����(ALTER)�ϰ�, ����(DROP)�ϴ� ����
 
-- ����Ŭ������ ��ü
-- : ���̺�(TABLE), ��(VIEW), ������(SEQUENCE)
--   �ε���(INDEX), ��Ű��(PACKAGE), Ʈ����(TRIGGER),
--   ���Ǿ�(SYNONYM), ���ν���(PROCEDURE), �Լ�(FUNCTION),
--   �����(USER)
   
CREATE TABLE MEMBER(
  MEMBER_ID VARCHAR2(20),
  MEMBER_PWD VARCHAR2(20),
  MEMBER_NAME VARCHAR2(20)
);

SELECT
       *
  FROM MEMBER;

DESC MEMBER;

-- �÷��� �ּ�(COMMENT) �ޱ�
-- COMMENT ON COLUMN ���̺��.�÷��� IS '�ּ�����'
COMMENT ON COLUMN MEMBER.MEMBER_ID IS '���̵�';
COMMENT ON COLUMN MEMBER.MEMBER_PWD IS '��й�ȣ';
COMMENT ON COLUMN MEMBER.MEMBER_NAME IS '�̸�';

-- ���̺� �����ϱ�
DROP TABLE MEMBER;
 
DESC MEMBER;    -- ��ü�� �������� �ʽ��ϴ�.
  
-- ��������
-- ���̺� �ۼ� �� �� �÷��� �� ��Ͽ� ���� ���������� ������ �� �ִ�.
-- ������ ���Ἲ ������ �������� ��
-- �Է�/�����ϴ� �����Ϳ� ������ ������ �ڵ����� �˻��� �ְ� �ϱ� ���� ����
-- PRIMARY KEY, NOT NULL, UNIQUE, CHECK, FOREIGN KEY
  
-- NOT NULL: �ش� �÷��� �ݵ�� ���� ��� �Ǿ�� �ϴ� ��� ���
--           ����/���� �� NULL���� ������� �ʵ���
--           �÷� �������� ���ѡڡ�
CREATE TABLE USER_NOCONS(
  USER_NO NUMBER,
  USER_ID VARCHAR2(20),
  USER_PWD VARCHAR2(30),
  USER_NAME VARCHAR2(30),
  GENDER VARCHAR2(10),
  PHONE VARCHAR2(30),
  EMAIL VARCHAR2(50)
);
  
INSERT
  INTO USER_NOCONS
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  1, 'user01', 'pass01'
, 'ȫ�浿', '��', '010-1234-5678'
, 'hong123@greedy.or.kr'
);
  
INSERT
  INTO USER_NOCONS
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  2, NULL, NULL
, NULL, NULL, '010-1234-5678'
, 'hong123@greedy.or.kr'
);
  
SELECT * FROM USER_NOCONS;

ROLLBACK;
COMMIT;

-- �������� ������ ���̺� ����
CREATE TABLE USER_NOTNULL(
  USER_NO NUMBER NOT NULL,      -- NOT NULL�� �ݵ�� �÷� �������� �������� ������ �ؾ� �Ѵ�.
  USER_ID VARCHAR2(20) NOT NULL,
  USER_PWD VARCHAR2(30) NOT NULL,
  USER_NAME VARCHAR2(30) NOT NULL,
  GENDER VARCHAR2(10),
  PHONE VARCHAR2(30),
  EMAIL VARCHAR2(50)
);

INSERT
  INTO USER_NOTNULL
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  1, 'user01', 'pass01'
, 'ȫ�浿', '��', '010-1234-5678'
, 'hong123@greedy.or.kr'
);

INSERT
  INTO USER_NOTNULL
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  2, NULL, NULL
, NULL, NULL, '010-1234-5678'
, 'hong123@greedy.or.kr'
);

SELECT * FROM USER_NOTNULL;

-- UNIQUE ��������: �÷��� �Է� ���� ���� �ߺ��� �����Ѵٴ� ��������
--                 �÷��������� ���� ����, ���̺� �������� ���� ����

DROP TABLE USER_UNIQUE;
CREATE TABLE USER_UNIQUE(
  USER_NO NUMBER,
  USER_ID VARCHAR2(20) UNIQUE NOT NULL,
  USER_PWD VARCHAR2(30) NOT NULL,
  USER_NAME VARCHAR2(30),
  GENDER VARCHAR2(10),
  PHONE VARCHAR2(30),
  EMAIL VARCHAR2(50),
  UNIQUE(USER_PWD)
);

INSERT
  INTO USER_UNIQUE
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  1, 'user01', 'pass01'
, 'ȫ�浿', '��', '010-1234-5678'
, 'hong123@greedy.or.kr'
);

INSERT
  INTO USER_UNIQUE
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  1, 'user02', 'pass02'             -- USER_ID�� USER_PWD�� �ߺ����� �ʰ� ����, NULL���� �ȵ�
, 'ȫ�浿', '��', '010-1234-5678'
, 'hong123@greedy.or.kr'
);

CREATE TABLE USER_UNIQUE2(
  USER_NO NUMBER,
  USER_ID VARCHAR2(20),
  USER_PWD VARCHAR2(30),
  USER_NAME VARCHAR2(30),
  GENDER VARCHAR2(10),
  PHONE VARCHAR2(30),
  EMAIL VARCHAR2(50),
  UNIQUE(USER_ID, USER_PWD)
--  UNIQUE(USER_ID),
--  UNIQUE(USER_PWD)
);

INSERT
  INTO USER_UNIQUE2
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  1, 'user01', 'pass01'
, 'ȫ�浿', '��', '010-1234-5678'
, 'hong123@greedy.or.kr'
);

INSERT
  INTO USER_UNIQUE2
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  2, 'user01', 'pass02'             -- USER_ID�� USER_PWD�� ���ÿ� ������ ��� ��ġ�ϸ� INSERT�� �ȵ����� �ϳ��� �ߺ��Ǵ� ���� ���ȴ�.
, 'ȫ�浿', '��', '010-1234-5678'
, 'hong123@greedy.or.kr'
);

-- CHECK ��������: �÷��� ��ϵǴ� ���� ���� ������ �� �� �ִ�.
--                �÷� �������� ���� ����, ���̺� �������� ���� ����
-- CHECK(�÷��� �񱳿����� �񱳰�)
-- ����: CHECK���������� �÷� �������� �����ϴ��� �÷����� ����Ѵ�.
CREATE TABLE USER_CHECK(
  USER_NO NUMBER,
  USER_ID VARCHAR2(20) UNIQUE,
  USER_PWD VARCHAR2(30) NOT NULL,
  USER_NAME VARCHAR2(30),
  GENDER VARCHAR2(10) CHECK(GENDER IN ('��','��')),
  PHONE VARCHAR2(30),
  EMAIL VARCHAR2(50)
);  

INSERT
  INTO USER_CHECK
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  1, 'user01', 'pass01'
, 'ȫ�浿', '��', '010-1234-5678'
, 'hong123@greedy.or.kr'
);
   
INSERT
  INTO USER_CHECK
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  1, 'user02', 'pass01'             -- UNIQUE �������� �� NOT NULL ���������� ����
, 'ȫ�浿', '��', '010-1234-5678'    -- '��' �Ǵ� '��'�� �ƴ� ���� CHECK �������ǿ� ���ݵȴ�.
, 'hong123@greedy.or.kr'
);   
   
CREATE TABLE TBL_CHECK(
  C_NAME VARCHAR2(10),
  C_PRICE NUMBER CONSTRAINT CK_C_PRICE CHECK(C_PRICE >= 1 AND C_PRICE <= 99999),
  C_LEVEL CHAR(1),
  C_DATE DATE,
  CONSTRAINT CK_C_LEVEL CHECK(C_LEVEL = 'A' OR C_LEVEL = 'B' OR C_LEVEL = 'C'),
  CONSTRAINT CK_C_DATE CHECK(C_DATE >= TO_DATE('2016/01/01', 'YYYY/MM/DD'))
);

-- ȸ�� ���Կ� ���̺� ����(USER_TEST)
-- �÷���: USER_NO(ȸ����ȣ)
--        USER_ID(ȸ�����̵�) -- �ߺ� ����, NULL�� ��� ����
--        USER_PWD(ȸ����й�ȣ) -- NULL�� ��� ����
--        PNO(�ֹε�Ϲ�ȣ) -- �ߺ� ����, NULL�� ��� ����
--        GENDER(����) -- '��' Ȥ�� '��'�� �Է�
--        PHONE(����ó)
--        ADDRESS(�ּ�)
--        STATUS(Ż�𿩺�) -- NOT NULL, 'Y' Ȥ�� 'N'���� �Է�
-- �� �÷��� �������� �̸� �ο�
-- 5�� �̻� ȸ�� ���� INSERT
-- �� �÷����� �ڸ�Ʈ �ۼ�
-- �ڷ����� �緮�� �ۼ�
-- (�������� �̸� ��: NOT NULL -> NN, CHECK -> CK, UNIQUE -> UK)
   
CREATE TABLE USER_TEST(
  USER_NO NUMBER,
  USER_ID VARCHAR2(50) CONSTRAINT NN_USER_ID NOT NULL,
  USER_PWD VARCHAR2(50) CONSTRAINT NN_PNO NOT NULL,
  PNO VARCHAR2(50) CONSTRAINT NN_PNO NOT NULL,
  GENDER VARCHAR2(3),
  PHONE VARCHAR2(50),
  ADDRESS VARCHAR2(100),
  STATUS VARCHAR2(1) CONSTRAINT NN_STATUS NOT NULL,
  CONSTRAINT UK_USER_ID UNIQUE(USER_ID),
  CONSTRAINT UK_PNO UNIQUE(PNO),
  CONSTRAINT CK_GENDER CHECK(GENDER IN('��', '��')),
  CONSTRAINT CK_STATUS CHECK(STATUS IN('Y', 'N'))
);

COMMENT ON COLUMN USER_TEST.USER_NO IS 'ȸ����ȣ';
COMMENT ON COLUMN USER_TEST.USER_ID IS 'ȸ�����̵�';
COMMENT ON COLUMN USER_TEST.USER_PWD IS 'ȸ����й�ȣ';
COMMENT ON COLUMN USER_TEST.PNO IS '�ֹε�Ϲ�ȣ';
COMMENT ON COLUMN USER_TEST.GENDER IS '����';
COMMENT ON COLUMN USER_TEST.PHONE IS '����ó';
COMMENT ON COLUMN USER_TEST.ADDRESS IS '�ּ�';
COMMENT ON COLUMN USER_TEST.STATUS IS 'Ż�𿩺�';

INSERT
  INTO USER_TEST
(
  USER_NO, USER_ID, USER_PWD,
  PNO, GENDER, PHONE,
  ADDRESS, STATUS 
)
VALUES
(
  1, 'user01', 'pass01'
, '881122-1231234', '��', '010-1234-5678'
, '����� ������ ���ﵿ', 'N'
);
   
INSERT
  INTO USER_TEST
(
  USER_NO, USER_ID, USER_PWD,
  PNO, GENDER, PHONE,
  ADDRESS, STATUS 
)
VALUES
(
  1, 'user02', 'pass01'
, '881122-1231235', '��', '010-1234-5678'
, '����� ������ ���ﵿ', 'N'
);   
   
INSERT
  INTO USER_TEST
(
  USER_NO, USER_ID, USER_PWD,
  PNO, GENDER, PHONE,
  ADDRESS, STATUS 
)
VALUES
(
  1, 'user03', 'pass01'
, '881122-1231236', '��', '010-1234-5678'
, '����� ������ ���ﵿ', 'N'
); 
   
INSERT
  INTO USER_TEST
(
  USER_NO, USER_ID, USER_PWD,
  PNO, GENDER, PHONE,
  ADDRESS, STATUS 
)
VALUES
(
  1, 'user04', 'pass01'
, '881122-1231237', '��', '010-1234-5678'
, '����� ������ ���ﵿ', 'N'
);    
   
INSERT
  INTO USER_TEST
(
  USER_NO, USER_ID, USER_PWD,
  PNO, GENDER, PHONE,
  ADDRESS, STATUS 
)
VALUES
(
  1, 'user05', 'pass01'
, '881122-1231238', '��', '010-1234-5678'
, '����� ������ ���ﵿ', 'N'
);   

SELECT * FROM USER_TEST;

-- PRIMARY KEY(�⺻Ű) ��������
-- : ���̺��� �� ���� ������ ã�� ���� ��� �� �÷��� �ǹ��Ѵ�.
--   ���̺� ���� �ĺ��� ������ �Ѵ�.(�� �྿ �����ϴ� ������ �Ѵ�.)
--   NOT NULL + UNIQUE ���������� �ǹ�
--   �� ���̺�� �� ���� ������ �� ����
--   �÷� ����, ���̺� ���� �� �� ���� ������
--   �� �� �÷��� ������ ���� �ְ�, ���� ���� �÷��� ��� ������ ���� ����(����Ű)

DROP TABLE USER_PRIMARYKEY;
CREATE TABLE USER_PRIMARYKEY(
  USER_NO NUMBER CONSTRAINT PK_USER_NO PRIMARY KEY,   -- �÷� �������� �������� ����
  USER_ID VARCHAR2(20) UNIQUE,
  USER_PWD VARCHAR2(30) NOT NULL,
  USER_NAME VARCHAR2(30),
  GENDER VARCHAR2(10),
  PHONE VARCHAR2(30),
  EMAIL VARCHAR2(50)
);

INSERT
  INTO USER_PRIMARYKEY
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  1, 'user01', 'pass01'
, 'ȫ�浿', '��', '010-123-1234'
, 'hong123@greedy.or.kr'
);

INSERT
  INTO USER_PRIMARYKEY
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  1, 'user02', 'pass01'                   -- PK �÷��� ��� �ߺ� ���� �� �� ����.
, 'ȫ�浿', '��', '010-123-1234'
, 'hong123@greedy.or.kr'
);

INSERT
  INTO USER_PRIMARYKEY
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL
)
VALUES
(
  NULL, 'user03', 'pass01'                   -- PK�� ��� NULL�� �� �� ����.
, 'ȫ�浿', '��', '010-123-1234'
, 'hong123@greedy.or.kr'
);

CREATE TABLE USER_PRIMARYKEY2(
  USER_NO NUMBER,
  USER_ID VARCHAR2(20) UNIQUE,
  USER_PWD VARCHAR2(30) NOT NULL,
  USER_NAME VARCHAR2(30),
  GENDER VARCHAR2(10),
  PHONE VARCHAR2(30),
  EMAIL VARCHAR2(50),
  CONSTRAINT PK_USER_NO_USER_ID PRIMARY KEY(USER_NO, USER_ID) -- �� �� �̻��� �÷��� ��� �⺻Ű(����Ű)�� ������ �� �ִ�.(���̺� ����)
);

-- FOREIGN KEY(�ܺ�Ű/�ܷ�Ű) ��������
-- : ����(REFERENCES)�� �ٸ� ���̺��� �����ϴ� ���� ����� �� ����
--   ���� ���Ἲ�� �������� �ʱ� ���� ���
--   FOREIGN KEY �������ǿ� ���ؼ�
--   ���̺� ���� ����(RELATIONSHIP)�� ���� ��
--   �����Ǵ� �� �ܿ��� NULL�� ����� �� ����
   
-- �÷� ������ ���
-- �÷��� �ڷ���(ũ��) [CONSTRAINT �̸�] REFERENCES ���������̺�� [(�������÷�)] [������]
 
-- ���̺� ������ ���
-- [CONSTRAINT �̸�] FOREIGN KEY (������ �÷���) REFERENCES ���������̺�� [(������ �÷�)] [������]

CREATE TABLE USER_GRADE(
  GRADE_CODE NUMBER PRIMARY KEY,
  GRADE_NAME VARCHAR2(30) NOT NULL
);

INSERT
  INTO USER_GRADE
(
  GRADE_CODE
, GRADE_NAME
)
VALUES
(
  10
, '�Ϲ�ȸ��'
);

INSERT
  INTO USER_GRADE
(
  GRADE_CODE
, GRADE_NAME
)
VALUES
(
  20
, '���ȸ��'
);

INSERT
  INTO USER_GRADE
(
  GRADE_CODE
, GRADE_NAME
)
VALUES
(
  30
, 'Ư��ȸ��'
);

SELECT * FROM USER_GRADE;
-- DELETE FROM USER_GRADE WHERE GRADE_CODE = 40; -- �Ǽ� ���� �� �� �������� �ϳ��� ���� ��������

CREATE TABLE USER_FOREIGNKEY(
  USER_NO NUMBER PRIMARY KEY,
  USER_ID VARCHAR2(20) UNIQUE,
  USER_PWD VARCHAR2(30) NOT NULL,
  USER_NAME VARCHAR2(30),
  GENDER VARCHAR2(10),
  PHONE VARCHAR2(30),
  EMAIL VARCHAR2(50),
  GRADE_CODE NUMBER,
  CONSTRAINT FK_GRADE_CODE FOREIGN KEY (GRADE_CODE) REFERENCES USER_GRADE (GRADE_CODE)
);

INSERT
  INTO USER_FOREIGNKEY
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL, GRADE_CODE
)
VALUES
(
  1, 'user01', 'pass01'
, 'ȫ�浿', '��', '010-123-4567'
, 'hong123@greedy.or.kr', 10
);

INSERT
  INTO USER_FOREIGNKEY
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL, GRADE_CODE
)
VALUES
(
  2, 'user02', 'pass01'
, 'ȫ�浿', '��', '010-123-4567'
, 'hong123@greedy.or.kr', NULL
);

INSERT
  INTO USER_FOREIGNKEY
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL, GRADE_CODE
)
VALUES
(
  3, 'user03', 'pass01'
, 'ȫ�浿', '��', '010-123-4567'
, 'hong123@greedy.or.kr', 40    -- USER_GRADE ���̺��� GRADE_CODE�� ���� ���� FK���������� ���� �� �÷��� ���� �� ����.
);

DELETE FROM USER_GRADE WHERE GRADE_CODE = 10;  -- �������� �������� ������ �����ϰ� �ִ� ȸ�� ����� ������ �� ����.
DELETE FROM USER_GRADE WHERE GRADE_CODE = 20;  -- �������� �������� ������ �����ϰ� ���� ���� ȸ�� ����� ������ �� �ִ�.

-- ON DELETE SET NULL
CREATE TABLE USER_FOREIGNKEY2(
  USER_NO NUMBER PRIMARY KEY,
  USER_ID VARCHAR2(20) UNIQUE,
  USER_PWD VARCHAR2(30) NOT NULL,
  USER_NAME VARCHAR2(30),
  GENDER VARCHAR2(10),
  PHONE VARCHAR2(30),
  EMAIL VARCHAR2(50),
  GRADE_CODE NUMBER,
  CONSTRAINT FK_GRADE_CODE2 FOREIGN KEY (GRADE_CODE)        -- �������� �̸��� ���� �Ͱ� �ߺ��ǰ� ����� �� ����.
  REFERENCES USER_GRADE (GRADE_CODE) ON DELETE SET NULL 
);

INSERT
  INTO USER_FOREIGNKEY2
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL, GRADE_CODE
)
VALUES
(
  1, 'user01', 'pass01'
, 'ȫ�浿', '��', '010-123-4567'
, 'hong123@greedy.or.kr', 10
);

INSERT
  INTO USER_FOREIGNKEY2
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL, GRADE_CODE
)
VALUES
(
  2, 'user02', 'pass01'
, 'ȫ�浿', '��', '010-123-4567'
, 'hong123@greedy.or.kr', 20
);

SELECT * FROM USER_GRADE;
SELECT * FROM USER_FOREIGNKEY2;
   
DELETE
  FROM USER_GRADE
 WHERE GRADE_CODE = 20;
   
-- ON DELETE CASCADE: �θ�Ű ���� �� �ڽ� Ű�� ���� �൵ �Բ� ����
CREATE TABLE USER_FOREIGNKEY3(
  USER_NO NUMBER PRIMARY KEY,
  USER_ID VARCHAR2(20) UNIQUE,
  USER_PWD VARCHAR2(30) NOT NULL,
  USER_NAME VARCHAR2(30),
  GENDER VARCHAR2(10),
  PHONE VARCHAR2(30),
  EMAIL VARCHAR2(50),
  GRADE_CODE NUMBER,
  CONSTRAINT FK_GRADE_CODE3 FOREIGN KEY (GRADE_CODE)        -- �������� �̸��� ���� �Ͱ� �ߺ��ǰ� ����� �� ����.
  REFERENCES USER_GRADE (GRADE_CODE) ON DELETE CASCADE
);
   
INSERT
  INTO USER_FOREIGNKEY3
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL, GRADE_CODE
)
VALUES
(
  1, 'user01', 'pass01'
, 'ȫ�浿', '��', '010-123-4567'
, 'hong123@greedy.or.kr', 10
);   
   
INSERT
  INTO USER_FOREIGNKEY3
(
  USER_NO, USER_ID, USER_PWD
, USER_NAME, GENDER, PHONE
, EMAIL, GRADE_CODE
)
VALUES
(
  2, 'user02', 'pass01'
, 'ȫ�浿', '��', '010-123-4567'
, 'hong123@greedy.or.kr', 20
);   

SELECT * FROM USER_GRADE;
SELECT * FROM USER_FOREIGNKEY3;

DELETE
  FROM USER_GRADE
 WHERE GRADE_CODE = 20;
   
-- ALTER
-- �������� �߰�
-- ALTER TABLE ���̺�� ADD PRIMARY KEY(�÷���)
-- ALTER TABLE ���̺�� ADD FOREIGN KEY(�÷���)
--                         REFERENCES ���̺�� (�÷���)
-- ALTER TABLE ���̺�� ADD UNIQUE(�÷���)
-- ALTER TABLE ���̺�� ADD CHECK(�÷��� �񱳿����� �񱳰�)
-- ALTER TABLE ���̺�� MODIFY �÷��� NOT NULL �ڡڡ�
ALTER TABLE EMPLOYEE ADD PRIMARY KEY(EMP_ID);
ALTER TABLE DEPARTMENT ADD PRIMARY KEY(DEPT_ID);
ALTER TABLE EMPLOYEE MODIFY EMAIL NOT NULL;

-- �ǽ�
-- EMPLOYEE ���̺��� DEPT_CODE�� �ܷ�Ű �������� �߰�
-- (���� ���̺��� DEPARTMENT, ���� �÷��� DEPARTMENT�� �⺻Ű�� DEPT_ID)
-- DEPARTMENT ���̺��� LOCATION_ID�� �ܷ�Ű �������� �߰�
-- (���� ���̺��� LOCATION, ���� �÷��� LOCATION�� �⺻Ű�� LOCAL_CODE)
-- EMPLOYEE ���̺��� JOB_CODE�� �ܷ�Ű �������� �߰�
-- (���� ���̺��� JOB, ���� �÷��� JOB�� �⺻Ű�� JOB_CODE)
-- EMPLOYEE ���̺��� SAL_LEVEL�� �ܷ�Ű �������� �߰�
-- (���� ���̺��� SAL_GRADE, ���� �÷��� SAL_GRADE�� �⺻Ű�� SAL_LEVEL)
-- EMPLOYEE ���̺��� ENT_YN�÷��� CHECK�������� �߰�('Y','N')
-- (��, �� �ҹ��ڸ� �����ϱ� ������ �빮�ڷ� ����)
-- EMPLOYEE ���̺��� SALARY �÷��� CHECK�������� �߰�(���)
-- EMPLOYEE ���̺��� EMP_NO �÷��� UNIQUE �������� �߰�

ALTER TABLE EMPLOYEE ADD FOREIGN KEY(DEPT_CODE) REFERENCES DEPARTMENT(DEPT_ID);
ALTER TABLE DEPARTMENT ADD FOREIGN KEY(LOCATION_ID) REFERENCES LOCATION(LOCAL_CODE);
ALTER TABLE EMPLOYEE ADD FOREIGN KEY(JOB_CODE) REFERENCES JOB(JOB_CODE);
ALTER TABLE EMPLOYEE ADD FOREIGN KEY(SAL_LEVEL) REFERENCES SAL_GRADE(SAL_LEVEL);
ALTER TABLE EMPLOYEE ADD CHECK (ENT_YN IN ('Y', 'N'));
ALTER TABLE EMPLOYEE ADD CHECK(SALARY > 0);
ALTER TABLE EMPLOYEE ADD UNIQUE(EMP_NO);




