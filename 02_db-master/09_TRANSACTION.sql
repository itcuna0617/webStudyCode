-- TCL(Transaction Control Language)
-- Ʈ����� ���� ���
-- COMMIT�� ROLLBACK�� �ִ�.
 
-- Ʈ������̶�?
-- �Ѳ����� ����Ǿ�� �� �ּ��� �۾� ������ ���Ѵ�.
-- ���� �۾� ����(Logical Unit of Work: LUW)
-- �ϳ��� Ʈ��������� �̷���� �۾��� �ݵ�� �Ѳ����� �Ϸ�(COMMIT)
-- �Ǿ�� �ϸ�, �׷��� ���� ��쿡�� �Ѳ����� ���(ROLLBACK) �Ǿ�� ��
-- 
-- COMMIT: Ʈ����� �۾��� ���� �Ϸ�Ǹ� ���� ������ ������ ����
-- ROLLBACK: Ʈ����� �۾��� ����ϰ� �ֱ� COMMIT�� �������� �̵�
--           (UPDATE, INSERT, DELETE ����(DML����)�� ���ư��⵵ ��)
-- SAVEPOINT ���̺�����Ʈ��: ���� Ʈ����� �۾� ������ �̸��� ������
--                         �ϳ��� Ʈ����� �ȿ� ������ ����
-- ROLLBACK TO ���̺�����Ʈ��: Ʈ����� �۾��� ����ϰ� 
--                           SAVEPOINT �������� �̵�

CREATE TABLE USER_TBL(
  USERNO NUMBER UNIQUE
, ID VARCHAR2(20) PRIMARY KEY
, PASSWORD CHAR(20) NOT NULL
);
 
INSERT
  INTO USER_TBL
(
  USERNO
, ID
, PASSWORD
)
VALUES
(
  1
, 'test1'
, 'pass1'
);

INSERT
  INTO USER_TBL
(
  USERNO
, ID
, PASSWORD
)
VALUES
(
  2
, 'test2'
, 'pass2'
);

INSERT
  INTO USER_TBL
(
  USERNO
, ID
, PASSWORD
)
VALUES
(
  3
, 'test3'
, 'pass3'
);

SELECT * FROM USER_TBL;
ROLLBACK;
COMMIT;

INSERT
  INTO USER_TBL
(
  USERNO
, ID
, PASSWORD
)
VALUES
(
  4
, 'test4'
, 'pass4'
);

SELECT * FROM USER_TBL;
ROLLBACK;   -- ���� COMMIT �������� �ǵ����� �ȴ�.

INSERT
  INTO USER_TBL
(
  USERNO
, ID
, PASSWORD
)
VALUES
(
  4
, 'test4'
, 'pass4'
);
 
SAVEPOINT SP1;

INSERT
  INTO USER_TBL
(
  USERNO
, ID
, PASSWORD
)
VALUES
(
  5
, 'test5'
, 'pass5'
);
 
SELECT * FROM USER_TBL;
ROLLBACK;
ROLLBACK TO SP1;
 
 
 