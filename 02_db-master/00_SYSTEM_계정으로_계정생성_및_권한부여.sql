-- ���� ���� �� ���� �ο�(SYSTEM �������� �� ��)
CREATE USER C##EMPLOYEE IDENTIFIED BY EMPLOYEE;     -- ���� �߰�(ID �� PWD �߰�)
GRANT CONNECT, RESOURCE TO C##EMPLOYEE;             -- ���� �� RESOURCE���� ���� �ο�

-- ������ ������ �ο�
GRANT CONNECT TO C##EMPLOYEE;
GRANT RESOURCE TO C##EMPLOYEE;
GRANT CREATE VIEW TO C##EMPLOYEE;                   -- VIEW�� ����� ���� ���� �ο�

-- �ѹ��� ������ ������ ������ �ο�
GRANT CONNECT, RESOURCE, CREATE VIEW TO C##EMPLOYEE;

-- �ش� �������� DDL �� DML �۾��� �ϱ� ���� �ʿ��� �۾�(SYSTEM �������� �� ��)
ALTER USER C##EMPLOYEE DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;   -- TABLESPACE Ȯ��
ALTER USER C##EMPLOYEE QUOTA 1024M ON SYSTEM;                               -- SYSTEM ���� Ȯ��